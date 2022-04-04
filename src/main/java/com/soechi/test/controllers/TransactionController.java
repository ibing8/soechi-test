package com.soechi.test.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.soechi.test.dto.DataTransactional;
import com.soechi.test.models.entities.Units;
import com.soechi.test.services.UnitsServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private UnitsServices unitsServices;
    
    @PostMapping(produces = "application/json")
	public Map<String, Object> create(HttpServletResponse rsp, /* 
        @RequestParam("itemDesc") String itemDesc, @RequestParam("uom") String uom,
        @RequestParam("qty") Integer qty, @RequestParam("unitPrice") Integer unitPrice,
        @RequestParam("discount") String discount,  */@RequestBody DataTransactional data) {

		Map<String, Object> retVal = new HashMap<>();
        ArrayList<Double> list = new ArrayList<Double>();
        Units unit = unitsServices.findByNameUnit(data.getUom());
        Integer qt = (unit.getRate()*data.getQty());

        Double discAmount = (double) 0;
        String[] data2 = {""};

        if (data.getDiscount().contains("%")){
            String data1 = data.getDiscount();
            if (data1.contains("+")) {
                data2 = data1.split("\\+");
            }
            for(Integer i=0;i<data2.length;i++) list.add((double) Integer.parseInt(data2[i].replace("%", "")));
            for(Integer j=0;j<list.size();j++) {
                discAmount += (qt*data.getUnitPrice())*(list.get(j)/100);
            }
        }else {
            discAmount = (double) Integer.parseInt(data.getDiscount());
        }

        Integer total = (int) ((qt*data.getUnitPrice())-discAmount);
        
        retVal.put("itemDesc", data.getItemDesc());
        retVal.put("uom", data.getUom());
        retVal.put("qty", data.getQty());
        retVal.put("unitPrice", data.getUnitPrice());
        retVal.put("discount", data.getDiscount());
        retVal.put("discountAmount", discAmount);
        retVal.put("total", total);
		return retVal;
	}
}
