package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.ImageSlipTransfer;
import com.example.menu.repository.ImageSlipTransferRepository;
import com.example.menu.service.ImageSlipTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping(value = "/imageSlipTransfer")
public class ImageSlipTransferController {

    @Autowired
    private ImageSlipTransferRepository imageSlipTransferRepository;

    @Autowired
    private ImageSlipTransferService imageSlipTransferService;

    //----------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------
    // Set of Customer.

    @PostMapping(value = "/save")
    public Object imageSlipTransferSave(@RequestParam("fileImg") MultipartFile fileImg, ImageSlipTransfer imageSlipTransfer){
        APIResponse response = new APIResponse();
        ImageSlipTransfer imgSlipTransfer = imageSlipTransferService.imageSlipTransferSave(fileImg,imageSlipTransfer);
        if(imgSlipTransfer == null){
            response.setStatus(0);
            response.setMessage("Not Success");
        }else{
            response.setStatus(1);
            response.setMessage("Save Success");
            response.setData(imgSlipTransfer);
        }
        return response;
    }

    @GetMapping(value = "/list/{tableCheckBillId}")
    public Object imageSlipTransferList(@PathVariable int tableCheckBillId) throws IOException {
        APIResponse response = new APIResponse();
        ImageSlipTransfer imageSlipTransfer = imageSlipTransferRepository.findByTableCheckBillId(tableCheckBillId);
        if(imageSlipTransfer != null){
            var img = imageSlipTransferService.imageSlipTransferList(imageSlipTransfer.getImageSlipName());
            Map<String,Object> ret = new HashMap<>();
            ret.put("imageSlipId",imageSlipTransfer.getImageSlipId());
            ret.put("nameTransfer",imageSlipTransfer.getNameTransfer());
            ret.put("telTransfer",imageSlipTransfer.getTelTransfer());
            ret.put("tableCheckBillId",imageSlipTransfer.getTableCheckBillId());
            ret.put("imageSlip",img);

            response.setStatus(1);
            response.setMessage("List Success");
            response.setData(ret);
        }else {
            response.setStatus(0);
            response.setMessage("No Data image_slip_transfer");
        }
        return response;
    }
}
