package com.example.menu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalTime;
import java.util.Date;

@Data
@ToString
@Entity(name = "order_menu")
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "number_Menu")
    private int numberMenu;

    @Column(name = "number_table")
    private int numberTable;

    @Column(name = "name_menu")
    private String nameMenu;

    @Column(name = "price_menu")
    private int priceMenu;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "make_status")
    private String makeStatus;

    /// Column of dateTime,date,time save by form same. But will get data by pattern of @JsonFormat().
    // Time and Date
    /*@Column(name = "date_time")
    @UpdateTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone= "Asia/Bangkok")
    private Date dateTime;*/

    // Date
    /*@Column(name = "date")
    @CreationTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone= "Asia/Bangkok")
    private Date date;*/

    // Time
    /*@Column(name = "time")
    @CreationTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone= "Asia/Bangkok")
    private Date time;*/

}