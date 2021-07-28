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
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            String img = imageFileService.insertImage(multipartFile, fileImageSave, optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setMessage("save Image Success");
            response.setData(img);
        }else {
            response.setStatus(0);
            response.setMessage("Not Data");
        }
        return response;
    }

    @GetMapping(value = "/list/{menuId}/{typeMenu}")
    public Object getImage(@PathVariable String typeMenu,@PathVariable int menuId) throws IOException {
        APIResponse response = new APIResponse();
        //List<String> imgList = new ArrayList<>();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if (optUserManager.isPresent()) {
            Optional<ImageFile> checkManagerIdAndTypeMenu = imageFileRepository.findByManagerIdAndTypeMenuAndMenuId(optUserManager.get().getManagerId(), typeMenu,menuId);
            /*for(ImageFile name : checkManagerIdAndTypeMenu){
                byte[] imgFile = imageFileService.getImageFile(name.getNameImage());
                String imgEncode = Base64.getEncoder().encodeToString(imgFile);
                imgList.add(imgEncode);
            }*/
            byte[] imgFile = imageFileService.getImageFile(checkManagerIdAndTypeMenu.get().getNameImage());
            //String imgEncode = Base64.getEncoder().encodeToString(imgFile);
            response.setStatus(1);
            response.setMessage("List Image Success");
            response.setData(imgFile);
        }else{
            response.setStatus(0);
            response.setMessage("Not Data");
        }
        return response;
    }
}