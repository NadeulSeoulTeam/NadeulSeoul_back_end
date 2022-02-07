package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.Image;
import com.alzal.nadeulseoulbackend.domain.curations.dto.ImageDto;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.ImageRepositoroy;
import com.alzal.nadeulseoulbackend.domain.curations.util.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void insertCuration(CurationDto request) throws IOException {
        List<ImageDto> imageDtoList = request.getFileList();
        Curation curation = Curation.builder()
                .title(request.getTitle())
                .budget(request.getBudget())
                .personnel(request.getPersonnel())
                .description(request.getDescription())
                .good(0)
                .views(0)
                .memberSeq(request.getMemberSeq())
                .photoCount(imageDtoList.size())
                .hidden(Boolean.FALSE)
                .build();

        List<Image> imageList = imageHandler.parseImageInfo(imageDtoList, curation);
        if(!imageList.isEmpty()){
            for(Image image : imageList) {
                curation.addImage(imageRepositoroy.save(image));
            }
        }
        curationRepository.save(curation);
    }

    public void updateCuration(CurationDto request) throws IOException {
        Curation curation = curationRepository.findById(request.getCurationSeq())
                .orElseThrow(()-> new CurationNotFoundException("큐레이션이"));

        List<String> pathList = new ArrayList<>();
        for(Image image : curation.getImageList()) {
            pathList.add(image.getImagePath());
            imageRepositoroy.delete(image);
        }

        imageHandler.deleteImageInfo(pathList);

        List<ImageDto> imageDtoList = request.getFileList();
        List<Image> imageList = imageHandler.parseImageInfo(imageDtoList, curation);

        if(!imageList.isEmpty()){
            for(Image image : imageList) {
                curation.addImage(imageRepositoroy.save(image));
            }
        }

        curationRepository.save(curation);

    }

    public void deleteCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(()-> new CurationNotFoundException("큐레이션이"));
        curation.changeHidden(Boolean.TRUE);
    }
}
