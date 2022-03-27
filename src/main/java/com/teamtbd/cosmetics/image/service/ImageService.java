package com.teamtbd.cosmetics.image.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjQ4MzY2NjMyLCJleHAiOjE2NDkyMzA2MzJ9.HwTKufsqEZNFCxGuB5_D_sDjCBnpBkhhynadoiDyds_CdwYk4j1QN7m46rJalACHHP9QL15y0ER9xxL7x1z_sg
public class ImageService {
    @Value("${cosmetics.images.path}")
    private String defaultImagePath;

    public Path saveReviewImage(MultipartFile imageFile) throws Exception{
        Path imageDir = Paths.get(defaultImagePath);
        if (!Files.exists(imageDir)) {
            Files.createDirectories(imageDir);
        }

        if(!imageFile.isEmpty()){
            String contentType= imageFile.getContentType();
            String originalFileExtension;
            if(ObjectUtils.isEmpty(contentType)){
                throw new Exception("이미지 파일은 jpg, png만 가능합니다.");
            }

            if(contentType.contains("image/jpeg")) {
                originalFileExtension = ".jpg";
            }else if(contentType.contains("image/png")){
                originalFileExtension=".png";
            }else{
                throw new Exception("이미지 파일은 jpg, png만 가능합니다.");
            }
            Path imagePath = imageDir.resolve(imageFile.getName() + originalFileExtension);
            imageFile.transferTo(imagePath);
            return imagePath;
        }
        throw new Exception("이미지 파일이 비어있습니다.");
    }
}
