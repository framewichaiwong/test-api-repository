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
import java.util.Optional;

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
           InputStream in = new FileInputStream(path + "/" + nameImage);
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteImageFile(int imageId, String nameImage) {
        String newPath = path + "/" + nameImage;
        Path fileToDeletePath = Paths.get(newPath);
        System.out.print("file ===>>> " + fileToDeletePath);
        try {
            Files.delete(fileToDeletePath);
            imageFileRepository.deleteById(imageId);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateImageFile(MultipartFile multipartFile, ImageFile imageFile, int menuId) {
        Optional<ImageFile> optImageFile = imageFileRepository.findByMenuId(menuId);
        ImageFile oldSaveImageFile = optImageFile.get();  /// Set for old_data.

        if(multipartFile != null && !multipartFile.isEmpty()){
            /// Delete Data_Image in Database.
            try {
                boolean deleteImg = deleteImageFile(optImageFile.get().getImageId(),optImageFile.get().getNameImage());
                if (deleteImg){
                    var insertImg = insertImageFile(multipartFile, imageFile, oldSaveImageFile.getManagerId());
                    if(insertImg != null){
                        System.out.print("----- Delete IMG & Create IMG Success.... -----");
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("----------Error----------");
            }
        }else{
            imageFile.setNameImage(oldSaveImageFile.getNameImage());
            imageFile.setManagerId(oldSaveImageFile.getManagerId());
            imageFile.setMenuId(oldSaveImageFile.getMenuId());
            imageFile.setTypeMenu(imageFile.getTypeMenu());
            imageFileRepository.save(oldSaveImageFile);
            System.out.print("----- Save old ImageFile -----");
        }
    }

}
