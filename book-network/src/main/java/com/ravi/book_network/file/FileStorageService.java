package com.ravi.book_network.file;

import com.ravi.book_network.entity.User;
import jakarta.annotation.Nonnull;
//import org.springframework.lang.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${file.uploads.photos-output-path}")
    private String fileUploadPath;
    public String savefile(
            @Nonnull Integer userId,
            @Nonnull   Integer bookId,
            @Nonnull  MultipartFile sourceFile
    ) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(sourceFile,fileUploadSubPath);
    }

    private String uploadFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull  String fileUploadSubPath) {

        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(fileUploadSubPath);
        if (!targetFolder.exists()){
            boolean folderCreated = targetFolder.mkdirs();
            if(!folderCreated){
                log.warn("Failed to create a folder ");
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator +fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath,sourceFile.getBytes());
            log.info("file saved to " + targetFilePath);
            return targetFilePath;
        }
        catch (IOException e){
            log.error("Error in uploading.. and file not saved",e);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()){
            return " ";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1){
            return " ";
        }
        return fileName.substring(lastDotIndex+1).toLowerCase();
    }
}
