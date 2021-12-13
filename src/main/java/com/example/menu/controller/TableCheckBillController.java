package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.TableCheckBill;
import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.TableCheckBillRepository;
import com.example.menu.service.TableCheckBillService;
import com.example.menu.util.ContextUtil;
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

    @Autowired
    private ContextUtil contextUtil;


    @PostMapping("/delete/{tableCheckBillId}")
    public Object deleteTableCheckBill(@PathVariable int tableCheckBillId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            Optional<TableCheckBill> checkId = tableCheckBillRepository.findById(tableCheckBillId);
            if (checkId.isPresent()){
                tableCheckBillService.deleteTableCheckBill(tableCheckBillId);
                response.setStatus(1);
                response.setMessage("delete success");
            }else{
                response.setStatus(0);
                response.setMessage("can't delete");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping("/list")
    public Object listTableCheckBill() {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2(); /// use token for pull user data.
        if (optUserManager.isPresent()) {
            List<TableCheckBill> listTableCheckBill = tableCheckBillService.listTableCheckBill(optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setData(listTableCheckBill);
        }else if(optUserManagerMember.isPresent()){
            List<TableCheckBill> listTableCheckBill = tableCheckBillService.listTableCheckBill(optUserManagerMember.get().getManagerId());
            response.setStatus(1);
            response.setData(listTableCheckBill);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
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
