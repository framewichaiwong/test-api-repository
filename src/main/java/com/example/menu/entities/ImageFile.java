package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "image_file")
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "name_image")
    private String nameImage;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "type_menu")
    private String typeMenu;
}
