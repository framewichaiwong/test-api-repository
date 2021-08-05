package com.example.menu.service;

import com.example.menu.entities.ImageFile;
import com.example.menu.repository.ImageFileRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;

@Service
public class ImageFileService {

    public static final String path = "C:\\uploadsImage";

    @Autowired
    private ImageFileRepository imageFileRepository;

    public String insertImageFile(MultipartFile multipartFile, ImageFile imageFile, int managerId){
        String idImage = new BigInteger(130, new SecureRandom()).toString();
        String nameImage = idImage + ".png";
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
           InputStream inImg = new FileInputStream(path + "/" + nameImage);
            return IOUtils.toByteArray(inImg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteImageFile(int imageId, String nameImage) {
        try {
            String newPath = path + "/" + nameImage;
            Path fileToDeletePath = Paths.get(newPath);
            Files.deleteIfExists(fileToDeletePath);
            imageFileRepository.deleteById(imageId);
            System.out.print("file ===>>> Delete Success...!!!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateImageFile(MultipartFile multipartFile, ImageFile imageFile, int menuId) {
        List<ImageFile> optImageFile = imageFileRepository.findByMenuId(menuId);
        for (ImageFile file : optImageFile) {
            try {
                //ImageFile oldSaveImageFile = file;  /// Set for old_data.
                /// Delete Data_Image in Database.
                boolean deleteImg = deleteImageFile(file.getImageId(), file.getNameImage());
                if (deleteImg) {
                    /// Create Data_Image in Database.
                    var insertImg = insertImageFile(multipartFile, imageFile, file.getManagerId()/*oldSaveImageFile.getManagerId()*/);
                    if (insertImg != null) {
                        System.out.print("----- Delete IMG & Create IMG Success.... -----");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("----------Error----------");
            }
        }
    }

}
