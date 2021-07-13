package com.example.menu.service;

import com.example.menu.entities.TableCheckBill;
import com.example.menu.repository.TableCheckBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TableCheckBillService {

    @Autowired
    private TableCheckBillRepository tableCheckBillRepository;

    public TableCheckBill saveNumberTable(TableCheckBill tableCheckBill) {
        return tableCheckBillRepository.save(tableCheckBill);
    }

    public void deleteTableCheckBill(int tableCheckBillId) {
        tableCheckBillRepository.deleteById(tableCheckBillId);
    }

    public List<TableCheckBill> listTableCheckBill() {
        return tableCheckBillRepository.findAll();
    }
}