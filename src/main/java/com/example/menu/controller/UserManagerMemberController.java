package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.UserManagerMemberRepository;
import com.example.menu.repository.UserManagerRepository;
import com.example.menu.service.UserManagerMemberService;
import com.example.menu.util.ContextUtil;
import com.example.menu.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/userMember")
public class UserManagerMemberController {

    @Autowired
    private UserManagerMemberService userManagerMemberService;

    @Autowired
    private UserManagerMemberRepository userManagerMemberRepository;

    @Autowired
    private EncoderUtil encoderUtil;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping(value = "/saveUserMember")
    public Object saveUserMember(UserManagerMember userManagerMember) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        UserManagerMember memberName = userManagerMemberRepository.findByMemberName(userManagerMember.getMemberName());
        if(optUserManager.isPresent()){
            if(memberName == null){
                userManagerMember.setMemberPassword(encoderUtil.passwordEncoder().encode(userManagerMember.getMemberPassword()));
                UserManagerMember input = userManagerMemberService.saveMember(userManagerMember);
                response.setStatus(1);
                response.setMessage("Save Success by user_manager");
                response.setData(input);
            }else {
                response.setStatus(0);
                response.setMessage("Can't Save");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No user_manager");
        }
        return response;
    }

    @GetMapping(value = "/listByManagerId")
    public Object listUserMember() {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// Token from user_manager.
        if(optUserManager.isPresent()){
            List<UserManagerMember> listUserManagerMember = userManagerMemberService.listByManagerId(optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setMessage("List by managerId");
            response.setData(listUserManagerMember);
        }else {
            response.setStatus(0);
        }
        return response;
    }

    @PostMapping(value = "/delete/{memberId}")
    public Object deleteUserMember(@PathVariable int memberId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManagerMember = contextUtil.getUserDataFromContext();
        if(optUserManagerMember.isPresent()){
            Optional<UserManagerMember> userManagerMember = userManagerMemberRepository.findById(memberId);
            if(userManagerMember.isPresent()){
                userManagerMemberService.deleteUserMemberId(memberId);
                response.setStatus(1);
                response.setMessage("Delete user_member Success");
                response.setData(userManagerMember);
            }else{
                response.setStatus(0);
                response.setMessage("No have user_member");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No have user_manager");
        }
        return response;
    }

    @PostMapping(value = "/update/{memberId}")
    public Object updateUserMember(@PathVariable int memberId,UserManagerMember userManagerMember) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<UserManagerMember> checkMemberId = userManagerMemberRepository.findById(memberId);
            if(checkMemberId.isPresent()){
                if(userManagerMember.getMemberPassword().isEmpty()){
                    userManagerMember.setMemberPassword(checkMemberId.get().getMemberPassword());
                    userManagerMemberService.updateMember(userManagerMember);
                    response.setStatus(1);
                    response.setMessage("update success by old password");
                }else{
                    userManagerMember.setMemberPassword(encoderUtil.passwordEncoder().encode(userManagerMember.getMemberPassword()));
                    userManagerMemberService.updateMember(userManagerMember);
                    response.setStatus(1);
                    response.setMessage("update success by new password");
                }
            }else{
                response.setStatus(0);
                response.setMessage("No data user_member");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No user_manager");
        }
        return response;
    }
}
