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
import java.util.*;

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
    public Object getImage(@PathVariable String typeMenu, @PathVariable int menuId) throws IOException {
        APIResponse response = new APIResponse();
        List<Object> imgList = new ArrayList<>();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if (optUserManager.isPresent()) {
            List<ImageFile> checkManagerIdAndTypeMenu = imageFileRepository.findByManagerIdAndTypeMenuAndMenuId(optUserManager.get().getManagerId(), typeMenu,menuId);
            for (ImageFile managerIdAndTypeMenu : checkManagerIdAndTypeMenu) {
                byte[] imgFile = imageFileService.getImageFile(managerIdAndTypeMenu.getNameImage());
                Map<String,Object> ret = new HashMap<>();
                ret.put("imageId",managerIdAndTypeMenu.getImageId());
                ret.put("dataImage",imgFile);
                imgList.add(ret);
                System.out.print("----- imgList ===>>>" + imgList + "-----");
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
            List<ImageFile> optImageFile = imageFileRepository.findByMenuId(menuId);
            for (ImageFile imageFile : optImageFile){
                try {
                    boolean deleteImg = imageFileService.deleteImageFile(imageFile.getImageId(), imageFile.getNameImage());
                    if (deleteImg) {
                        response.setStatus(1);
                        response.setMessage("Delete Image Success");
                    }else {
                        response.setStatus(0);
                        response.setMessage("Can't Delete");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping("/update/{imageId}")
    public Object updateImage(@RequestParam("fileIMG") MultipartFile multipartFile,@PathVariable int imageId,ImageFile imageFile) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<ImageFile> checkImageId = imageFileRepository.findById(imageId);
            if(checkImageId.isPresent()){
                var updateSuccess = imageFileService.updateImageFile(multipartFile, imageFile, checkImageId.get().getNameImage());
                if(updateSuccess) {
                    response.setStatus(1);
                    response.setMessage("Update Image Success");
                }else{
                    response.setStatus(0);
                    response.setMessage("Can't update");
                }
            }else{
                /// อาจจะได้ทำเพื่อ insert_Image
                response.setStatus(0);
                response.setMessage("No Image");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

   // ----------------------------------------------------------------------------------------------------------------------------------------
   @GetMapping("/list/{managerId}/{menuId}/{typeMenu}")
   public Object getImageForCustomer(@PathVariable int managerId,@PathVariable String typeMenu,@PathVariable int menuId) throws IOException {
       APIResponse response = new APIResponse();
       List<byte[]> imgList = new ArrayList<>();
       List<ImageFile> checkManagerIdAndTypeMenu = imageFileRepository.findByManagerIdAndTypeMenuAndMenuId(managerId, typeMenu,menuId);
       for (ImageFile managerIdAndTypeMenu : checkManagerIdAndTypeMenu) {
           byte[] imgFile = imageFileService.getImageFile(managerIdAndTypeMenu.getNameImage());
           imgList.add(imgFile);
       }
       response.setStatus(1);
       response.setMessage("List Image Success");
       response.setData(imgList);
       return response;
   }
}