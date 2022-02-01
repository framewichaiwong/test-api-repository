package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.TableCheckBill;
import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.TableCheckBillRepository;
import com.example.menu.service.TableCheckBillService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


//    @PostMapping("/delete/{tableCheckBillId}")
//    public Object deleteTableCheckBill(@PathVariable int tableCheckBillId) {
//        APIResponse response = new APIResponse();
//        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
//        if(optUserManager.isPresent()){
//            Optional<TableCheckBill> checkId = tableCheckBillRepository.findById(tableCheckBillId);
//            if (checkId.isPresent()){
//                tableCheckBillService.deleteTableCheckBill(tableCheckBillId);
//                response.setStatus(1);
//                response.setMessage("delete success");
//            }else{
//                response.setStatus(0);
//                response.setMessage("can't delete");
//            }
//        }else {
//            response.setStatus(0);
//            response.setMessage("No UserManager");
//        }
//        return response;
//    }

    @GetMapping("/list/{paymentStatus}")
    public Object listTableCheckBill(@PathVariable String paymentStatus) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2(); /// use token for pull user data.
        if (optUserManager.isPresent()) {
            List<TableCheckBill> listTableCheckBill = tableCheckBillService.listByManagerIdAndPaymentStatus(optUserManager.get().getManagerId(),paymentStatus);
            response.setStatus(1);
            response.setData(listTableCheckBill);
        }else if(optUserManagerMember.isPresent()){
            List<TableCheckBill> listTableCheckBill = tableCheckBillService.listByManagerIdAndPaymentStatus(optUserManagerMember.get().getManagerId(),paymentStatus);
            response.setStatus(1);
            response.setData(listTableCheckBill);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping(value = "/update")
    public Object tableCheckBillUpdate(TableCheckBill tableCheckBill){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2();
        Optional<TableCheckBill> checkId = tableCheckBillRepository.findById(tableCheckBill.getTableCheckBillId());
        if(optUserManager.isPresent()){
            if(checkId.isPresent()){
                tableCheckBill.setDate(checkId.get().getDate());
                tableCheckBill.setTime(checkId.get().getTime());
                TableCheckBill table = tableCheckBillService.tableCheckBillUpdate(tableCheckBill);
                response.setStatus(1);
                response.setMessage("Update Success by user_manager");
                response.setData(table);
            }else{
                response.setStatus(0);
                response.setMessage("Can't update by user_manager");
            }
        }else if(optUserManagerMember.isPresent()){
            if(checkId.isPresent()){
                tableCheckBill.setDate(checkId.get().getDate());
                tableCheckBill.setTime(checkId.get().getTime());
                TableCheckBill table = tableCheckBillService.tableCheckBillUpdate(tableCheckBill);
                response.setStatus(1);
                response.setMessage("Update Success by user_manager_member");
                response.setData(table);
            }else{
                response.setStatus(0);
                response.setMessage("Can't update by user_manager_member");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping(value = "/getList/{firstYearMonthDay}/{lastYearMonthDay}/{paymentStatus}")
    public Object listByYear(@PathVariable String firstYearMonthDay, @PathVariable String lastYearMonthDay,@PathVariable String paymentStatus) {
        APIResponse response = new APIResponse();
        LocalDate firstDate = LocalDate.parse(firstYearMonthDay/*,DateTimeFormatter.BASIC_ISO_DATE*/);
        LocalDate lastDate = LocalDate.parse(lastYearMonthDay/*,DateTimeFormatter.BASIC_ISO_DATE*/);
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            List<TableCheckBill> data = tableCheckBillService.listByYearMonthDayAndManagerId(firstDate,lastDate,optUserManager.get().getManagerId(),paymentStatus);
            response.setStatus(1);
            response.setMessage("List by user_manager");
            response.setData(data);
        }else{
            response.setStatus(0);
            response.setMessage("Not user");
        }
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------
    // Set of customer

    @PostMapping(value = "/save")
    public Object saveNumberTable(TableCheckBill tableCheckBill) {
        APIResponse response = new APIResponse();
        TableCheckBill checkDb = tableCheckBillRepository.findByManagerIdAndNumberTableAndPaymentStatus(tableCheckBill.getManagerId(), tableCheckBill.getNumberTable(),tableCheckBill.getPaymentStatus());
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

    @PostMapping("/check/{paymentStatus}") /// For Customer(เช็กว่ามีข้อมูลหรือไม่).
    public Object checkNumberTable(@PathVariable String paymentStatus, TableCheckBill tableCheckBill) {
        APIResponse response = new APIResponse();
        TableCheckBill checkDb = tableCheckBillRepository.findByManagerIdAndNumberTableAndPaymentStatus(tableCheckBill.getManagerId(), tableCheckBill.getNumberTable(), paymentStatus);
        if(checkDb != null){
            response.setStatus(1);
            response.setMessage("Have Data");
        }else{
            response.setStatus(0);
            response.setMessage("Not Have Data");
        }
        return response;
    }
}
