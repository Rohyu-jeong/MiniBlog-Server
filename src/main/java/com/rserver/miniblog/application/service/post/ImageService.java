package com.rserver.miniblog.application.service.post;

import com.rserver.miniblog.exception.ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    @Value("${image.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file) {
        try {
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                System.out.println("📂 업로드 폴더가 없어 새로 생성합니다: " + uploadDir);
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadFolder, fileName);
            file.transferTo(dest);

            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new ImageUploadException("이미지 저장 실패: " + e.getMessage());
        }
    }
}
