package com.rserver.miniblog.post.application.service;

import com.rserver.miniblog.exception.ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.rserver.miniblog.post.domain.PostErrorMessage.IMAGE_UPLOAD_FAILED;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    @Value("${image.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file) {
        try {
            String resolvedPath = System.getProperty("user.home") + "/Desktop/uploads/";

            File uploadFolder = new File(resolvedPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadFolder, fileName);
            file.transferTo(dest);

            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new ImageUploadException(IMAGE_UPLOAD_FAILED);
        }
    }
}
