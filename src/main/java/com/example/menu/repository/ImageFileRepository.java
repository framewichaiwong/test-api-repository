package com.example.menu.repository;

import com.example.menu.entities.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageFileRepository extends JpaRepository<ImageFile,Integer> {

    Optional<ImageFile> findByManagerIdAndTypeMenuAndMenuId(int managerId, String typeMenu, int menuId);
}
