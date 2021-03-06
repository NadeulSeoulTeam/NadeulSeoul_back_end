package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.ImageDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Api(value = "ImageController")
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "사진 불러오기", notes = "사진 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사진 불러오기가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> getImage(@PathVariable("id") final Long imageSeq) throws IOException {
        try {
            ImageDto imageDto = imageService.getImageById(imageSeq);
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            InputStream imageStream = new FileInputStream(absolutePath + imageDto.getImagePath());
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
        } catch (Exception e) {
            throw new IOException("이미지 가져오기 실패");
        }
    }
}
