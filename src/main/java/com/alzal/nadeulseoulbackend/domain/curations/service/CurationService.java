package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.*;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.exception.ImageIOException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.ImageRepositoroy;
import com.alzal.nadeulseoulbackend.domain.curations.util.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CurationService {

    @Autowired
    private CurationRepository curationRepository;

    @Autowired
    private ImageRepositoroy imageRepositoroy;

    @Autowired
    private ImageHandler imageHandler;

    public CurationDto getCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));

        CurationDto curationDto = CurationDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .budget(curation.getBudget())
                .personnel(curation.getPersonnel())
                .description(curation.getDescription())
                .good(curation.getGood())
                .views(curation.getViews())
                .memberSeq(curation.getMemberSeq())
                .photoCount(curation.getPhotoCount())
                .build();

        return curationDto;
    }

    public List<CurationHotResponseDto> getHotCurationList() {
        List<Curation> curationList = curationRepository.findTop10ByHiddenFalseOrderByViewsDesc();
        return curationList.stream().map(CurationHotResponseDto::fromEntity).collect(Collectors.toList());

    }

    public void insertCuration(CurationDto curationDto) throws ImageIOException {
        List<MultipartFile> multipartFileList = curationDto.getFileList();
        Curation curation = Curation.builder()
                .title(curationDto.getTitle())
                .budget(curationDto.getBudget())
                .personnel(curationDto.getPersonnel())
                .description(curationDto.getDescription())
                .good(0)
                .views(0)
                .memberSeq(curationDto.getMemberSeq())
                .photoCount(multipartFileList.size())
                .hidden(Boolean.FALSE)
                .build();

        curationRepository.save(curation);
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

    public void updateCuration(CurationDto curationDto) throws ImageIOException {
        Curation curation = curationRepository.findById(curationDto.getCurationSeq())
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));

        List<String> pathList = new ArrayList<>();
        for (Image image : curation.getImageList()) {
            pathList.add(image.getImagePath());
            imageRepositoroy.delete(image);
        }
        imageHandler.deleteImageInfo(pathList);

        List<MultipartFile> multipartFileList = curationDto.getFileList();
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
                curationDto.getTitle(), curationDto.getBudget(), curationDto.getPersonnel(),
                curationDto.getDescription(), imageList.size()
        );
    }

    public void deleteCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));
        curation.changeHidden(Boolean.TRUE);
    }
}
