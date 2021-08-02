package com.example.menu.repository;

import com.example.menu.entities.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ImageFileRepository extends JpaRepository<ImageFile,Integer> {

    List<ImageFile> findByManagerIdAndTypeMenuAndMenuId(int managerId, String typeMenu, int menuId);
    Optional<ImageFile> findByMenuId(int menuId);
}
