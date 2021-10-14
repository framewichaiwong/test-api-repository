package com.example.menu.repository;

import com.example.menu.entities.TableCheckBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableCheckBillRepository extends JpaRepository<TableCheckBill,Integer> {
    TableCheckBill findByManagerIdAndNumberTable(int managerId,int numberTable);
    List<TableCheckBill> findByManagerId(int managerId);
}
