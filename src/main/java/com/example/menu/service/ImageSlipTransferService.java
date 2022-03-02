package com.example.menu.service;

import com.example.menu.entities.ImageSlipTransfer;
import com.example.menu.repository.ImageSlipTransferRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;

@Service
public class ImageSlipTransferService {

    @Value("${app.image_path}")
    String configPath;

    @Autowired
    private ImageSlipTransferRepository imageSlipTransferRepository;

    public ImageSlipTransfer imageSlipTransferSave(MultipartFile fileImg, ImageSlipTransfer imageSlipTransfer) {
        String idImage = new BigInteger(130,new Random()).toString();
        String nameImage = idImage + ".png";
        String newPath = configPath + "/" + nameImage;
        File file = new File(newPath);
        try {
            fileImg.transferTo(file);
            imageSlipTransfer.setImageSlipName(nameImage);
            return imageSlipTransferRepository.save(imageSlipTransfer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] imageSlipTransferList(String imageSlipName) throws IOException{
        try {
            InputStream inImg = new FileInputStream(configPath + "/" + imageSlipName);
            var img = IOUtils.toByteArray(inImg);
            inImg.close();
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean imageSlipTransferRemove(int imageSlipId, String imageSlipName) {
        String newPath = configPath + "/" + imageSlipName;
        try {
            File deleteFileFromPath = new File(newPath);
            if(deleteFileFromPath.delete()){
                imageSlipTransferRepository.deleteById(imageSlipId);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
