package com.pluralsight.CarDealershipAPI.controller;


import com.pluralsight.CarDealershipAPI.models.SalesContract;
import com.pluralsight.CarDealershipAPI.service.SalesContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalesContractsController {

    private final SalesContractService salesContractService;

    @Autowired
    public SalesContractsController(SalesContractService salesContractService) {
        this.salesContractService = salesContractService;
    }

    @RequestMapping(path = "/sales-contracts", method = RequestMethod.GET)
    public List<SalesContract> getAllSalesContracts() {
        return salesContractService.getAllSalesContracts();
    }

    @RequestMapping(path = "/sales-contracts", method = RequestMethod.GET)
    public SalesContract getSalesContractById(@RequestParam int id) {
        return salesContractService.getSalesContractById(id);
    }

    @RequestMapping(path = "/sales-contracts", method = RequestMethod.POST)
    public boolean addSalesContract (@RequestBody SalesContract salesContract) {
        return salesContractService.addSalesContract(salesContract);
    }

}
