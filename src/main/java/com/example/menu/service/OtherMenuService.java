package com.example.menu.service;

import com.example.menu.entities.OtherMenu;
import com.example.menu.repository.OtherMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OtherMenuService {

    @Autowired
    private OtherMenuRepository otherMenuRepository;

    public OtherMenu otherMenuSave(OtherMenu otherMenu){
        return otherMenuRepository.save(otherMenu);
    }

    public List<OtherMenu> otherMenuList(int managerId){
        return otherMenuRepository.findByManagerId(managerId);
    }

    public void OtherMenuDelete(int otherMenuId){
       otherMenuRepository.deleteById(otherMenuId);
    }

    public OtherMenu otherMenuUpdate(OtherMenu otherMenu){
        return otherMenuRepository.save(otherMenu);
    }
}
