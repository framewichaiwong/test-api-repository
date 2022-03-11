package com.example.menu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate date = LocalDate.now(ZoneId.of("Asia/Bangkok"));

    @Column(name = "time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime time = LocalTime.now(ZoneId.of("Asia/Bangkok"));

}
