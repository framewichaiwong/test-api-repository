package com.example.menu.service;

import com.example.menu.entities.TableCheckBill;
import com.example.menu.repository.TableCheckBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TableCheckBillService {

    @Autowired
    private TableCheckBillRepository tableCheckBillRepository;

    public TableCheckBill saveNumberTable(TableCheckBill tableCheckBill) {
        return tableCheckBillRepository.save(tableCheckBill);
    }

//    public void deleteTableCheckBill(int tableCheckBillId) {
//        tableCheckBillRepository.deleteById(tableCheckBillId);
//    }

    public List<TableCheckBill> listByManagerIdAndPaymentStatus(int managerId,String paymentStatus) {
        return tableCheckBillRepository.findByManagerIdAndPaymentStatus(managerId,paymentStatus);
    }

    public TableCheckBill tableCheckBillUpdate(TableCheckBill tableCheckBill) {
        return tableCheckBillRepository.save(tableCheckBill);
    }

    public List<TableCheckBill> listByYearMonthDayAndManagerId(LocalDate firstDate, LocalDate lastDate, int managerId,String paymentStatus,String paymentStatusCancel){
        return tableCheckBillRepository.findByDateBetweenAndManagerId(firstDate,lastDate,managerId,paymentStatus,paymentStatusCancel);
    }

    public List<TableCheckBill> listByYearMonthDayAndManagerIdForCheckBill(LocalDate date, int managerId,String paymentStatus) {
        return tableCheckBillRepository.findByDateAndManagerIdAndPaymentStatus(date,managerId,paymentStatus);
    }
}
