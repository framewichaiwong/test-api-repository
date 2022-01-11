package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Data
@ToString
@Entity(name = "image_slip_transfer")
public class ImageSlipTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_slip_id")
    private int imageSlipId;

    @Column(name = "image_slip_name")
    private String imageSlipName;

    @Column(name = "name_transfer")
    private String nameTransfer;

    @Column(name = "tel_transfer")
    private String telTransfer;

    @Column(name = "table_check_bill_id")
    private int tableCheckBillId;
}
