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
            var img = IOUtils.toByteArray(inImg);
            inImg.close();
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteImageFile(int imageId, String nameImage) {
        String newPath = path + "/" + nameImage;
        try {
            File deleteFileFormPath = new File(newPath);
            boolean deleteFile = deleteFileFormPath.delete();
            if (deleteFile){
                imageFileRepository.deleteById(imageId);
                System.out.print("file ===>>> Delete Success...!!!");
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
