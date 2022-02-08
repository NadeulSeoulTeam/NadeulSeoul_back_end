package com.alzal.nadeulseoulbackend.domain.curations.service;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.Image;
import com.alzal.nadeulseoulbackend.domain.curations.dto.ImageDto;
import com.alzal.nadeulseoulbackend.domain.curations.exception.CurationNotFoundException;
import com.alzal.nadeulseoulbackend.domain.curations.repository.CurationRepository;
import com.alzal.nadeulseoulbackend.domain.curations.repository.ImageRepositoroy;
import com.alzal.nadeulseoulbackend.domain.curations.util.ImageHandler;
import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    @Autowired
    private ImageRepositoroy imageRepositoroy;

    @Autowired
    private CurationRepository curationRepository;

    // 큐레이션 이미지 전체 조회
    public List<Long> getImageByCuration(Long curationSeq) {
        Curation curation = curationRepository.findById(curationSeq)
                .orElseThrow(() -> new CurationNotFoundException("큐레이션이"));
        List<Image> imageList = imageRepositoroy.findAllByCuration(curation);
        List<Long> imageSeqList = new ArrayList<>();
        for(Image image : imageList) {
            imageSeqList.add(image.getImageSeq());
        }
        return imageSeqList;
    }

    // 이미지 개별 조회
    public ImageDto getImageById(Long imageSeq){
        Image image = imageRepositoroy.findById(imageSeq)
                .orElseThrow(()-> new EntityNotFoundException("이미지가"));
        return ImageDto.builder()
                .origFileName(image.getOriginName())
                .imagePath(image.getImagePath())
                .imageSize(image.getImageSize())
                .build();
    }
}
