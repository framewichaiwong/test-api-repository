package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.ImageMenu;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.ImageMenuRepository;
import com.example.menu.service.ImageMenuService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/image")
public class ImageMenuController {

    @Autowired
    private ImageMenuRepository imageMenuRepository;

    @Autowired
    private ImageMenuService imageMenuService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping("/save")
    public Object insertImage(@RequestParam("fileIMG") MultipartFile multipartFile, ImageMenu imageMenu) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            String img = imageMenuService.insertImageMenu(multipartFile, imageMenu, optUserManager.get().getManagerId());
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
            List<ImageMenu> checkManagerIdAndTypeMenu = imageMenuRepository.findByManagerIdAndTypeMenuAndMenuId(optUserManager.get().getManagerId(), typeMenu,menuId);
            for (ImageMenu managerIdAndTypeMenu : checkManagerIdAndTypeMenu) {
                byte[] imgFile = imageMenuService.getImageFile(managerIdAndTypeMenu.getNameImage());
                Map<String,Object> ret = new HashMap<>();
                ret.put("imageId",managerIdAndTypeMenu.getImageId());
                ret.put("dataImage",imgFile);
                imgList.add(ret);
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

    @PostMapping("/delete/{menuId}") /// delete by menuId.
    public Object deleteImageByMenuId(@PathVariable int menuId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            List<ImageMenu> optImageMenu = imageMenuRepository.findByMenuId(menuId);
            for (ImageMenu imageMenu : optImageMenu){
                try {
                    boolean deleteImg = imageMenuService.deleteImageFile(imageMenu.getImageId(), imageMenu.getNameImage());
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

    @PostMapping("remove/{imageId}")   /// delete by image_id.
    public Object deleteImageByImageId(@PathVariable int imageId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<ImageMenu> checkImageId = imageMenuRepository.findById(imageId);
            if(checkImageId.isPresent()){
                boolean deleteSuccess = imageMenuService.deleteImageFile(checkImageId.get().getImageId(),checkImageId.get().getNameImage());
                if(deleteSuccess){
                    response.setStatus(1);
                    response.setMessage("Delete Success");
                }else{
                    response.setStatus(0);
                    response.setMessage("Can't Delete");
                }
            }else{
                response.setStatus(0);
                response.setMessage("No Image");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    /*@PostMapping("/update/{imageId}")
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
    }*/

   // ----------------------------------------------------------------------------------------------------------------------------------------
   @GetMapping("/list/{managerId}/{menuId}/{typeMenu}")
   public Object getImageForCustomer(@PathVariable int managerId,@PathVariable String typeMenu,@PathVariable int menuId) throws IOException {
       APIResponse response = new APIResponse();
       List<byte[]> imgList = new ArrayList<>();
       List<ImageMenu> checkManagerIdAndTypeMenu = imageMenuRepository.findByManagerIdAndTypeMenuAndMenuId(managerId, typeMenu,menuId);
       for (ImageMenu managerIdAndTypeMenu : checkManagerIdAndTypeMenu) {
           byte[] imgFile = imageMenuService.getImageFile(managerIdAndTypeMenu.getNameImage());
           imgList.add(imgFile);
       }
       response.setStatus(1);
       response.setMessage("List Image Success");
       response.setData(imgList);
       return response;
   }
}