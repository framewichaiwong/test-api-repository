package com.example.menu.repository;

import com.example.menu.entities.UserManagerMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserManagerMemberRepository extends JpaRepository<UserManagerMember,Integer> {
    UserManagerMember findByMemberName(String memberName);
    List<UserManagerMember> findByManagerId(int managerId);
}
