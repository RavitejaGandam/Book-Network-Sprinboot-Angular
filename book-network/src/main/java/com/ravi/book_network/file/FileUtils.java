package com.ravi.book_network.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
public class FileUtils {
    public static byte[] readFromLocation(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)){
            return null;
        }
        try{
            Path filePath = new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        }
        catch (Exception e){
            log.warn("File not found or nothing to read in that loc",fileUrl);
        }
        return null;
    }
}
