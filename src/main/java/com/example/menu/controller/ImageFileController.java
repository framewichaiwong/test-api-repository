package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.ImageFile;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.ImageFileRepository;
import com.example.menu.service.ImageFileService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageFileController {

    @Autowired
    private ImageFileRepository imageFileRepository;

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping("/save")
    public Object insertImage(@RequestParam("fileIMG") MultipartFile multipartFile, ImageFile fileImageSave) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            String img = imageFileService.insertImageFile(multipartFile, fileImageSave, optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setMessage("save Image Success");
            response.setData(img);
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping("/list/{menuId}/{typeMenu}")
    public Object getImage(@PathVariable String typeMenu,@PathVariable int menuId) throws IOException {
        APIResponse response = new APIResponse();
        List<byte[]> imgList = new ArrayList<>();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if (optUserManager.isPresent()) {
            List<ImageFile> checkManagerIdAndTypeMenu = imageFileRepository.findByManagerIdAndTypeMenuAndMenuId(optUserManager.get().getManagerId(), typeMenu,menuId);
            for(int i=0; i<checkManagerIdAndTypeMenu.size(); i++){
                byte[] imgFile = imageFileService.getImageFile(checkManagerIdAndTypeMenu.get(i).getNameImage());
                imgList.add(imgFile);
            }
            response.setStatus(1);
            response.setMessage("List Image Success");
            response.setData(imgList);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping("/delete/{menuId}")
    public Object deleteImage(@PathVariable int menuId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            Optional<ImageFile> optImageFile = imageFileRepository.findByMenuId(menuId);
            if(optImageFile.isPresent()){
                imageFileService.deleteImageFile(optImageFile.get().getImageId(),optImageFile.get().getNameImage());
                response.setStatus(1);
                response.setMessage("Delete Image Success");
            }else{
                response.setData(0);
                response.setMessage("No Image");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping("/update/{menuId}")
    public Object updateImage(@RequestParam("fileIMG") MultipartFile multipartFile,@PathVariable int menuId,ImageFile imageFile) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            imageFileService.updateImageFile(multipartFile, imageFile, menuId);
            response.setStatus(1);
            response.setMessage("Update Image Success");
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }
}