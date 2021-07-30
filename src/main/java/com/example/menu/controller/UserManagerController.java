package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.UserManagerRepository;
import com.example.menu.service.UserManagerService;
import com.example.menu.util.ContextUtil;
import com.example.menu.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userManager")
public class UserManagerController {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private EncoderUtil encoderUtil;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping("/saveUserManager")
    public Object saveUserManager(@RequestParam String userName,UserManager userManager) {
        APIResponse response = new APIResponse();
        UserManager checkUserName = userManagerRepository.findByUserName(userName);
        if (checkUserName == null) {
            userManager.setPassWord(encoderUtil.passwordEncoder().encode(userManager.getPassWord()));
            UserManager user = userManagerService.saveUserManager(userManager);
            response.setStatus(1);
            response.setMessage("Save user manager Success");
            response.setData(user);
        }else {
            response.setStatus(0);
            response.setMessage("Can't Save");
        }
        return response;
    }

    /*@PostMapping("/loginUserManager")
    public Object loginUserManager(@RequestParam String userName,@RequestParam String passWord) {
        APIResponse response = new APIResponse();
        UserManager user = userManagerService.loginByUserNameAndPassWord(userName,passWord);
        if(user != null) {
            response.setStatus(1);
            response.setMessage("Login Success");
            response.setData(user);
        }else {
            response.setStatus(0);
            response.setMessage("Can't Success");
        }
        return response;
    }*/

    @PostMapping("/updateUserManager/{managerId}")
    public Object updateUserManager(@PathVariable int managerId,UserManager userManager) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<UserManager> checkId = userManagerRepository.findById(managerId);
            UserManager checkUserManager = userManagerRepository.findByUserName(userManager.getUserName());
            if(checkId.isPresent() && checkUserManager != null) {
                userManager.setPassWord(encoderUtil.passwordEncoder().encode(userManager.getPassWord()));
                UserManager user = userManagerService.updateUserManager(userManager);
                response.setStatus(1);
                response.setMessage("Update Success");
                response.setData(user);
            }else {
                response.setStatus(0);
                response.setMessage("Can't Update");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping("/listUser")
    public Object listUserByManagerId() {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()) {
            Optional<UserManager> userManagerList = userManagerService.listUserManagerByManagerId(optUserManager.get().getManagerId());
            if (userManagerList.isPresent()) {
                response.setStatus(1);
                response.setMessage("List User Success");
                response.setData(userManagerList);
            } else {
                response.setStatus(0);
                response.setMessage("No Data");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    //-------------------------------------------------------------------------------------------------------------------------------
    // Set of Customer

    @GetMapping("/listUser/{managerId}")
    public Object listUserManagerId(@PathVariable int managerId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> userManager = userManagerService.listUserManagerId(managerId);
        if(userManager.isPresent()) {
            response.setStatus(1);
            response.setMessage("List User Success");
            response.setData(userManager);
        }else {
            response.setStatus(0);
            response.setMessage("No Data");
        }
        return response;
    }
}