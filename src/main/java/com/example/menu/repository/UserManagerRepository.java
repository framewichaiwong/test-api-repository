package com.example.menu.repository;

import com.example.menu.entities.UserManager;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserManagerRepository extends JpaRepository<UserManager, Integer> {

    UserManager findByUserName(String userName);

    UserManager findByUserNameAndPassWord(String userName,String passWord);

}
