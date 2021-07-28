package com.example.menu.service;

import com.example.menu.entities.ImageFile;
import com.example.menu.repository.ImageFileRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class ImageFileService {
    public static final String path = "C:\\uploadsImage";

    @Autowired
    private ImageFileRepository imageFileRepository;

    public String insertImage(MultipartFile multipartFile, ImageFile imageFile, int managerId){
        String idImage = new BigInteger(130, new SecureRandom()).toString();
        String nameImage = idImage + multipartFile.getName() + ".png";
        String newPath = path + "/" + nameImage;
        File file = new File(newPath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageFile.setNameImage(nameImage);
        imageFile.setManagerId(managerId);
        imageFile.setTypeMenu(imageFile.getTypeMenu());
        imageFileRepository.save(imageFile);
        return newPath;
    }
    
    public byte[] getImageFile(String nameImage) throws IOException {
        try {
           InputStream in = new FileInputStream(path + "/" + nameImage);
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
