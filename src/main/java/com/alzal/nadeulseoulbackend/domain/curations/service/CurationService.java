package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.*;
import com.alzal.nadeulseoulbackend.domain.curations.entity.*;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.ImageIOException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.*;
import com.alzal.nadeulseoulbackend.domain.curations.util.ImageHandler;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreInfoRepository;
import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeRequestDto;
import com.alzal.nadeulseoulbackend.domain.tag.exception.TagNotFoundException;
import com.alzal.nadeulseoulbackend.domain.tag.repository.CodeRepository;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO :
//      멤버 seq 수정 및 값 불러오기 새로 작성 필요
@Service
@RequiredArgsConstructor
@Transactional
public class CurationService {

    @Autowired
    private CurationTagRepository curationRepository;

    @Autowired
    private StoreInfoRepository storeInfoRepository;

    @Autowired
    private StoreInCurationRepository storeInCurationRepository;

    @Autowired
    private ImageRepositoroy imageRepositoroy;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageHandler imageHandler;

    public CurationResponseDto getCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));

        curation.addViews(); // 조회수 추가
        
        List<CodeDto> localDtoList = curation.getLocalCuration().stream().map(LocalCuration::getCode).collect(Collectors.toList())
                                            .stream().map(CodeDto::fromEntity).collect(Collectors.toList());
        List<CodeDto> themeDtoList = curation.getThemeCuration().stream().map(ThemeCuration::getCode).collect(Collectors.toList())
                                            .stream().map(CodeDto::fromEntity).collect(Collectors.toList());

        CurationResponseDto curationResponseDto = CurationResponseDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .budget(curation.getBudget())
                .personnel(curation.getPersonnel())
                .description(curation.getDescription())
                .good(curation.getGood())
                .views(curation.getViews())
                .photoCount(curation.getPhotoCount())
                .local(localDtoList)
                .theme(themeDtoList)
                .date(curation.getDate())
                .build();

        return curationResponseDto;
    }


    public List<CurationSearchResponseDto> getHotCurationList() {
        List<Curation> curationList = curationRepository.findTop10ByHiddenFalseOrderByViewsDesc();
        return curationList.stream().map(CurationSearchResponseDto::fromEntity).collect(Collectors.toList());

    }

    public Page<CurationSearchResponseDto> getCurationListByPage(Long userSeq, Pageable pageable) {
        Page<Curation> curationPage = curationRepository.findByUserSeq(userSeq, pageable);
        return curationPage.map(CurationSearchResponseDto::fromEntity);

    }

    public void insertCuration(CurationRequestDto curationRequestDto) throws ImageIOException {
        List<MultipartFile> multipartFileList = curationRequestDto.getFileList();
//        List<StoreInfo> storeInfos = curationRequestDto.getCourseRoute();

        User user = userRepository.findById(3L) // 멤버 변수 토큰으로 받아오기
                .orElseThrow(()->new UserNotFoundException("사용자가 "));

        Curation curation = Curation.builder()
                .title(curationRequestDto.getTitle())
                .budget(curationRequestDto.getBudget())
                .personnel(curationRequestDto.getPersonnel())
                .description(curationRequestDto.getDescription())
                .good(0)
                .views(0)
                .photoCount(multipartFileList.size())
                .hidden(Boolean.FALSE)
                .user(user)
                .build();

        curationRepository.save(curation);

//        storeInfos.stream().forEach((store) ->
//                storeInCurationRepository.save(
//                        StoreInCuration.builder()
//                                .storeOrder(storeInfos.indexOf(store))
//                                .storeInfo(storeInfoRepository.findById(store.getStoreSeq()).orElse(storeInfoRepository.save(store)))
//                                .curation(curation)
//                                .build()
//                )
//        );


        for(Long localSeq : curationRequestDto.getLocal()) {
            Code localTag = codeRepository.findById(localSeq)
                    .orElseThrow(()-> new TagNotFoundException("지역 태그가"));

            localRepository.save(
                    LocalCuration.builder()
                            .curation(curation)
                            .code(localTag)
                            .build()
            );
        }

        for(Long themeSeq : curationRequestDto.getTheme()) {
            Code themeTag = codeRepository.findById(themeSeq)
                    .orElseThrow(()->new TagNotFoundException("테마 태그가 "));

            themeRepository.save(
                    ThemeCuration.builder()
                            .curation(curation)
                            .code(themeTag)
                            .build()
            );
        }

        List<Image> imageList = imageHandler.parseImageInfo(multipartFileList, curation);
        if (!imageList.isEmpty()) {
            for (Image image : imageList) {
                curation.addImage(imageRepositoroy.save(image));
            }
            curation.changeThumnail(imageList.get(0).getImageSeq());
        } else {
            curation.changeThumnail(0L);
        }
    }



    public void updateCuration(CurationRequestDto curationRequestDto) throws ImageIOException {
        Curation curation = curationRepository.findById(curationRequestDto.getCurationSeq())
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));

        List<String> pathList = new ArrayList<>();

        for (Image image : curation.getImageList()) {
            pathList.add(image.getImagePath());
            imageRepositoroy.delete(image);
        }

        imageHandler.deleteImageInfo(pathList);

        List<MultipartFile> multipartFileList = curationRequestDto.getFileList();
        List<Image> imageList = imageHandler.parseImageInfo(multipartFileList, curation);

        if (!imageList.isEmpty()) {
            for (Image image : imageList) {
                curation.addImage(imageRepositoroy.save(image));
            }
            curation.changeThumnail(imageList.get(0).getImageSeq());
        } else {
            curation.changeThumnail(0L);
        }

        curation.changeCuration(
                curationRequestDto.getTitle(), curationRequestDto.getBudget(), curationRequestDto.getPersonnel(),
                curationRequestDto.getDescription(), imageList.size()
        );

        localRepository.deleteByCuration(curation);
        themeRepository.deleteByCuration(curation);

        for(Long localSeq : curationRequestDto.getLocal()) {
            Code localTag = codeRepository.findById(localSeq)
                    .orElseThrow(()-> new TagNotFoundException("지역 태그가"));

            localRepository.save(
                    LocalCuration.builder()
                            .curation(curation)
                            .code(localTag)
                            .build()
            );
        }

        for(Long themeSeq : curationRequestDto.getTheme()) {
            Code themeTag = codeRepository.findById(themeSeq)
                    .orElseThrow(()->new TagNotFoundException("테마 태그가 "));

            themeRepository.save(
                    ThemeCuration.builder()
                            .curation(curation)
                            .code(themeTag)
                            .build()
            );
        }
    }

    public void deleteCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));
        curation.changeHidden(Boolean.TRUE);
    }


    public Page<CurationSearchResponseDto> getCurationListByPageWithCode(CodeRequestDto codeRequestDto, Pageable pageable){
        Page<Curation> curationPage = curationRepository.searchByTag(codeRequestDto, pageable);
        return curationPage.map(CurationSearchResponseDto::fromEntity);
    }

}
