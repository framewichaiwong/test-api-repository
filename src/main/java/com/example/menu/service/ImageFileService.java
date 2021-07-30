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

    public void deleteImageFile(int imageId, String nameImage) {
        try {
            String newPath = path + "/" + nameImage;
            File file = new File(newPath);
            boolean checkImage = file.exists();
            if(checkImage){
                boolean deleteImg = file.delete();
                if(deleteImg){
                    imageFileRepository.deleteById(imageId);
                    System.out.println("...........Delete Image ===>>> " + deleteImg);
                }else{
                    System.out.println("...........Can't Delete Image ===>>> " + deleteImg);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error...");
        }
    }

    public void updateImageFile(MultipartFile multipartFile, ImageFile imageFile, int menuId) {
        Optional<ImageFile> optImageFile = imageFileRepository.findByMenuId(menuId);
        if(multipartFile.isEmpty()){
            imageFileRepository.save(imageFile);
        }else{
            //ImageFile newSaveImageFile = optImageFile.get();

            /// Delete Data_Image in Database.
            deleteImageFile(optImageFile.get().getImageId(),optImageFile.get().getNameImage());

            /// New Create Data_Image in Database.
            insertImageFile(multipartFile, imageFile, imageFile.getManagerId());
        }
    }

}
