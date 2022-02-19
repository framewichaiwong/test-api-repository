package com.example.menu.repository;

import com.example.menu.entities.TableCheckBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TableCheckBillRepository extends JpaRepository<TableCheckBill,Integer> {
    TableCheckBill findByManagerIdAndNumberTableAndPaymentStatus(int managerId,int numberTable,String paymentStatus);

    @Query(value = "SELECT d FROM table_check_bill d WHERE d.managerId=:managerId AND (d.paymentStatus=:paymentStatusInProgress OR d.paymentStatus=:paymentStatusAddImage OR d.paymentStatus=:paymentStatusCheckImage OR d.paymentStatus=:paymentStatusEditImage)")
    List<TableCheckBill> findByManagerIdAndPaymentStatus(@Param("managerId") int managerId,@Param("paymentStatusInProgress") String paymentStatusInProgress,@Param("paymentStatusAddImage") String paymentStatusAddImage,@Param("paymentStatusCheckImage") String paymentStatusCheckImage,@Param("paymentStatusEditImage") String paymentStatusEditImage);

//    @Query(value = "SELECT d FROM table_check_bill d WHERE d.date = :firstDate AND d.date = :lastDate") // OK.
//    @Query(value = "SELECT d FROM table_check_bill d WHERE d.date BETWEEN :firstDate AND :lastDate") // OK.
//    List<TableCheckBill> findByDateBetweenAndManagerId(@Param("firstDate") LocalDate firstDate, @Param("lastDate") LocalDate lastDate);

//    @Query(value = "SELECT d FROM table_check_bill d WHERE (d.date BETWEEN :firstDate AND :lastDate) AND d.managerId=:managerId AND d.paymentStatus=:paymentStatus")
//    List<TableCheckBill> findByDateBetweenAndManagerId(@Param("firstDate") LocalDate firstDate, @Param("lastDate") LocalDate lastDate, @Param("managerId") int managerId,@Param("paymentStatus") String paymentStatus);

    @Query(value = "SELECT d FROM table_check_bill d WHERE (d.date BETWEEN :firstDate AND :lastDate) AND d.managerId=:managerId AND (d.paymentStatus=:paymentStatus OR d.paymentStatus=:paymentStatusCancel)")
    List<TableCheckBill> findByDateBetweenAndManagerId(@Param("firstDate") LocalDate firstDate, @Param("lastDate") LocalDate lastDate, @Param("managerId") int managerId,@Param("paymentStatus") String paymentStatus,@Param("paymentStatusCancel") String paymentStatusCancel);

    List<TableCheckBill> findByDateAndManagerIdAndPaymentStatus(LocalDate date,int managerId,String paymentStatus);
}
