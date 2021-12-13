package com.example.menu.service;

import com.example.menu.entities.ImageMenu;
import com.example.menu.repository.ImageMenuRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class ImageMenuService {

    //public static final String path = "C:\\uploadsImage";
    @Value("${app.image_path}")
    String configPath;

    @Autowired
    private ImageMenuRepository imageMenuRepository;

    public String insertImageMenu(MultipartFile multipartFile, ImageMenu imageMenu, int managerId){
        String idImage = new BigInteger(130, new SecureRandom()).toString();
        String nameImage = idImage + ".png";
        String newPath = configPath + "/" + nameImage;
        File file = new File(newPath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageMenu.setNameImage(nameImage);
        imageMenu.setManagerId(managerId);
        imageMenu.setTypeMenu(imageMenu.getTypeMenu());
        imageMenuRepository.save(imageMenu);
        return newPath;
    }
    
    public byte[] getImageFile(String nameImage) throws IOException {
        try {
           InputStream inImg = new FileInputStream(configPath + "/" + nameImage);
            var img = IOUtils.toByteArray(inImg);
            inImg.close();
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteImageFile(int imageId, String nameImage) {
        String newPath = configPath + "/" + nameImage;
        try {
            File deleteFileFormPath = new File(newPath);
            boolean deleteFile = deleteFileFormPath.delete();
            if (deleteFile){
                imageMenuRepository.deleteById(imageId);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /*public boolean updateImageFile(MultipartFile multipartFile, ImageFile imageFile, String nameImage) {
        String newPath = path + "/" + nameImage;
        try {
            File deleteFileFromPath = new File(newPath);
            boolean deleteFile = deleteFileFromPath.delete();
            if(deleteFile){
                System.out.print("----- Delete Image from Folder -----");
                String idImage = new BigInteger(130,new SecureRandom()).toString();
                String nameImg = idImage + ".png";
                String namePath = path + "/" + nameImg;
                File file = new File(namePath);
                try {
                    multipartFile.transferTo(file);
                    System.out.print("---------- Create & Save Image Success ----------");
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.print("---------- Error 2 ----------");
                }
                imageFile.setNameImage(nameImg);
                imageFileRepository.save(imageFile);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---------- Error 1 ----------");
        }
        return false;
    }*/

}