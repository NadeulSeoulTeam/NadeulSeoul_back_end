package com.alzal.nadeulseoulbackend.domain.curations.util;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.entity.Image;
import com.alzal.nadeulseoulbackend.domain.curations.exception.ImageIOException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageHandler {

    public List<Image> parseImageInfo(List<MultipartFile> multipartFileList, Curation curation) throws ImageIOException {
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
                    throw new ImageIOException("폴더 생성 실패");
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
                        .imagePath(path + File.separator + storeName)
                        .imageSize(multipartFile.getSize())
                        .build();

                imageList.add(image);
                file = new File(absolutePath + path + File.separator + storeName);
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    throw new ImageIOException("파일 변경 실패");
                }
                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return imageList;
    }

    public void deleteImageInfo(List<String> imageList) {
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        for (String path : imageList) {
            File file = new File(absolutePath + path);
            file.delete();
        }
    }

}
