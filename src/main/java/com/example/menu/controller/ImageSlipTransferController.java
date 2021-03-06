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
    public Object imageSlipTransferSave(@RequestParam(value = "fileImg",required = false) MultipartFile fileImg, ImageSlipTransfer imageSlipTransfer){
        APIResponse response = new APIResponse();
        try {
            Optional<ImageSlipTransfer> imgSlipTransfer = imageSlipTransferService.imageSlipTransferSave(fileImg,imageSlipTransfer);
            if(imgSlipTransfer.isPresent()){
                response.setStatus(1);
                response.setMessage("Save Success");
                response.setData(imgSlipTransfer);
            }else{
                response.setStatus(0);
                response.setMessage("Not Success");
            }
        }catch (Exception e){
            response.setStatus(-1);
            response.setMessage("Error : " + e.toString());
        }
        return response;
    }

    @GetMapping(value = "/list/{tableCheckBillId}")
    public Object imageSlipTransferList(@PathVariable int tableCheckBillId) throws IOException {
        APIResponse response = new APIResponse();
        List<ImageSlipTransfer> imageSlipTransfer = imageSlipTransferRepository.findByTableCheckBillId(tableCheckBillId);
        if(imageSlipTransfer.isEmpty()){
            response.setStatus(0);
            response.setMessage("No Data image_slip_transfer");
        }else {
            List<Object> listImage = new ArrayList<>();
            for(ImageSlipTransfer imgSlip : imageSlipTransfer){
                var img = imageSlipTransferService.imageSlipTransferList(imgSlip.getImageSlipName());
                Map<String,Object> ret = new HashMap<>();
                ret.put("imageSlipId",imgSlip.getImageSlipId());
                ret.put("nameTransfer",imgSlip.getNameTransfer());
                ret.put("telTransfer",imgSlip.getTelTransfer());
                ret.put("tableCheckBillId",imgSlip.getTableCheckBillId());
                ret.put("imageSlip",img);
                listImage.add(ret);
            }
            response.setStatus(1);
            response.setMessage("List Success");
            response.setData(listImage);
        }
        return response;
    }

    @PostMapping(value = "/remove/{imageSlipId}")
    public Object imageSlipTransferDelete(@PathVariable int imageSlipId) {
        APIResponse response = new APIResponse();
        Optional<ImageSlipTransfer> checkImageSlip = imageSlipTransferRepository.findById(imageSlipId);
        if(checkImageSlip.isPresent()){
            try {
                boolean check = imageSlipTransferService.imageSlipTransferRemove(imageSlipId,checkImageSlip.get().getImageSlipName());
                if(check){
                    response.setStatus(1);
                    response.setMessage("Remove Success");
                }else{
                    response.setStatus(0);
                    response.setMessage("can't remove");
                }
            }catch (Exception e){
                response.setStatus(-1);
                response.setMessage("Error : " + e.toString());
            }
        }else{
            response.setStatus(0);
            response.setMessage("Not have data");
        }
        return response;
    }
}
