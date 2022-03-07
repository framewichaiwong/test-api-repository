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
import java.util.Optional;

@Service
public class ImageMenuService {

    //public static final String path = "C:\\uploadsImage";
    @Value("${app.image_path}")
    String configPath;

    @Autowired
    private ImageMenuRepository imageMenuRepository;

    public Optional<ImageMenu> insertImageMenu(MultipartFile multipartFile, ImageMenu imageMenu, int managerId) {
        try {
            String idImage = new BigInteger(130, new SecureRandom()).toString();
            String nameImage = idImage + ".png";
            String newPath = configPath + "/" + nameImage;
            File file = new File(newPath);

            multipartFile.transferTo(file);

            imageMenu.setNameImage(nameImage);
            imageMenu.setManagerId(managerId);
            imageMenu.setTypeMenu(imageMenu.getTypeMenu());
            return Optional.of(imageMenuRepository.save(imageMenu));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
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
        try {
            String newPath = configPath + "/" + nameImage;
            File deleteFileFormPath = new File(newPath);
            if (deleteFileFormPath.delete()){
                imageMenuRepository.deleteById(imageId);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
