package com.example.menu.repository;

import com.example.menu.entities.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageFileRepository extends JpaRepository<ImageFile,Integer> {

    List<ImageFile> findByManagerIdAndTypeMenuAndMenuId(int managerId, String typeMenu, int menuId);
    List<ImageFile> findByMenuId(int menuId);
}
