package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "table_check_bill")
public class TableCheckBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableCheckBillId;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "number_table")
    private int numberTable;
}
