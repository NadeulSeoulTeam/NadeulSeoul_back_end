package com.alzal.nadeulseoulbackend.domain.curations.util;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.Image;
import com.alzal.nadeulseoulbackend.domain.curations.dto.ImageDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageHandler {

    @Autowired
    private CurationService curationService;


//    public List<Image> parseImageInfo(List<ImageDto> imageDtoList, Curation curation) throws IOException {

    public List<Image> parseImageInfo(List<MultipartFile> multipartFileList, Curation curation) throws IOException {

//        List<MultipartFile> multipartFileList = new ArrayList<>();
//        for (ImageDto imageDto : imageDtoList) {
//            multipartFileList.add(imageDto.getMultipartFile());
//        }

        List<Image> imageList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(multipartFileList)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            String path = "images" + File.separator + currentDate;
            File file = new File(path);

            if (!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
                if (!wasSuccessful) {
                    System.out.println("디렉토리 생성에 실패했습니다.");
                }
            }

            for (int i = 0; i < multipartFileList.size(); i++) {

                MultipartFile multipartFile = multipartFileList.get(i);
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else {
                        break;
                    }
                }

                String storeName = System.nanoTime() + originalFileExtension;
                Image image = Image.builder()
                        .curation(curation)
                        .originName(multipartFile.getOriginalFilename())
                        .imageOrder(i)
//                        .imageOrder(multipartFileList.get(i).getImageOrder())
                        .imagePath(path + File.separator + storeName)
                        .imageSize(multipartFile.getSize())
                        .build();

                imageList.add(image);
                file = new File(absolutePath + path + File.separator + storeName);
                System.out.println(absolutePath + path + File.separator + storeName);
                multipartFile.transferTo(file);
                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return imageList;
    }

    public void deleteImageInfo(List<String> imageList) throws IOException {
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        for (String path : imageList) {
            File file = new File(absolutePath + path);
            file.delete();
        }
    }

}
