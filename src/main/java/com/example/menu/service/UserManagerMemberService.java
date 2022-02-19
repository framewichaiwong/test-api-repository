package com.example.menu.service;

import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.UserManagerMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerMemberService {

    @Autowired
    private UserManagerMemberRepository userManagerMemberRepository;

    public UserManagerMember saveMember(UserManagerMember userManagerMember) {
        return userManagerMemberRepository.save(userManagerMember);
    }

    public List<UserManagerMember> listByManagerId(int managerId) {
        return userManagerMemberRepository.findByManagerId(managerId);
    }

    public void deleteUserMemberId(int memberId) {
        userManagerMemberRepository.deleteById(memberId);
    }

    public UserManagerMember updateMember(UserManagerMember userManagerMember) {
        return  userManagerMemberRepository.save(userManagerMember);
    }
}
