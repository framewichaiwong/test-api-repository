package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.TableCheckBill;
import com.example.menu.repository.TableCheckBillRepository;
import com.example.menu.service.TableCheckBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tableCheckBill")
public class TableCheckBillController {

    @Autowired
    private TableCheckBillRepository tableCheckBillRepository;

    @Autowired
    private TableCheckBillService tableCheckBillService;


    @PostMapping("/delete/{tableCheckBillId}")
    public Object deleteTableCheckBill(@PathVariable int tableCheckBillId) {
        APIResponse response = new APIResponse();
        Optional<TableCheckBill> checkId = tableCheckBillRepository.findById(tableCheckBillId);
        if (checkId.isPresent()){
            tableCheckBillService.deleteTableCheckBill(tableCheckBillId);
            response.setStatus(1);
            response.setMessage("delete success");
        }else{
            response.setStatus(0);
            response.setMessage("can't delete");
        }
        return response;
    }

    @GetMapping("/list")
    public Object listTableCheckBill() {
        APIResponse response = new APIResponse();
        List<TableCheckBill> listTableCheckBill = tableCheckBillService.listTableCheckBill();
        response.setData(listTableCheckBill);
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------
    // Set of customer

    @PostMapping("/save")
    public Object saveNumberTable(@RequestParam int managerId, @RequestParam int numberTable, TableCheckBill tableCheckBill) {
        APIResponse response = new APIResponse();
        TableCheckBill checkDb = tableCheckBillRepository.findByManagerIdAndNumberTable(managerId,numberTable);
        if(checkDb==null){
            TableCheckBill saveTableCheckBill = tableCheckBillService.saveNumberTable(tableCheckBill);
            response.setStatus(1);
            response.setMessage("save success");
            response.setData(saveTableCheckBill);
        }else{
            response.setStatus(0);
            response.setMessage("can't save");
        }
        return response;
    }
}
