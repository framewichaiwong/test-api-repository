package com.example.menu.service;


import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.oauth2.TokenService;
import com.example.menu.repository.UserManagerMemberRepository;
import com.example.menu.repository.UserManagerRepository;
import com.example.menu.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Autowired
    private EncoderUtil encoderUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserManagerMemberRepository userManagerMemberRepository;  // TEST

    public Optional<Map<String, Object>> login(LoginBean loginBean) {
        UserManager userProfile = userManagerRepository.findByUserName(loginBean.getUsername());
        UserManagerMember userManagerMember = userManagerMemberRepository.findByMemberName(loginBean.getUsername()); // TEST
        Map<String, Object> ret = new HashMap<>();
        if (userProfile != null) {
            String userPassWord = userProfile.getPassWord();

            if (encoderUtil.passwordEncoder().matches(loginBean.getPassword(), userPassWord)) {

                ret.put("data", 1);
                ret.put("token", tokenService.createToken(userProfile));
                ret.put("userId", userProfile.getManagerId());
                return Optional.of(ret);
            } else {
                ret.put("data", 0);
                return Optional.of(ret);
            }
        }else if (userManagerMember != null){ /// TEST
            String userPassWord = userManagerMember.getMemberPassword(); /// TEST

            if (encoderUtil.passwordEncoder().matches(loginBean.getPassword(), userPassWord)) { /// TEST

                ret.put("data", 2); /// TEST
                ret.put("token", tokenService.createToken2(userManagerMember)); /// TEST
                ret.put("memberId", userManagerMember.getMemberId()); /// TEST
                ret.put("roleStatus",userManagerMember.getRoleStatus()); /// TEST
                return Optional.of(ret); /// TEST
            } else { /// TEST
                ret.put("data", 0); /// TEST
                return Optional.of(ret); /// TEST
            } /// TEST
        }else {
            ret.put("data", 0);
        }
        return Optional.of(ret);
    }
}