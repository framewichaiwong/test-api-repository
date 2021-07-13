package com.example.menu.service;


import com.example.menu.entities.UserManager;
import com.example.menu.oauth2.TokenService;
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

    public Optional<Map<String, Object>> login(LoginBean loginBean) {
        UserManager userProfile = userManagerRepository.findByUserName(loginBean.getUsername());
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
        }else {
            ret.put("data", 0);
        }
        return Optional.of(ret);
    }
}