package com.example.menu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.regex.Pattern;

@Data
@ToString
@Entity(name = "table_check_bill")
public class TableCheckBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_check_bill_id")
    private int tableCheckBillId;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "number_table")
    private int numberTable;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "price_total")
    private int priceTotal;

    @Column(name = "date")
    @CreationTimestamp
//    @UpdateTimestamp
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone= "Asia/Bangkok")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone= "Asia/Bangkok")
    private LocalDate date;

    @Column(name = "time")
    @CreationTimestamp
//    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone= "Asia/Bangkok")
    private LocalTime time;

}
