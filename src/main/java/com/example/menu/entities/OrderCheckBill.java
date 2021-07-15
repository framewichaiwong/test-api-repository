package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "order_check_bill")
public class OrderCheckBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCheckBillId;

    @Column(name = "number_Menu")
    private int numberMenu;

    @Column(name = "number_table")
    private int numberTable;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id",referencedColumnName = "menu_id")
    private Menu menu;*/

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
