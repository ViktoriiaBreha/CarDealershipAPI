package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.SalesContract;
import com.pluralsight.CarDealershipAPI.service.LeaseContractService;
import com.pluralsight.CarDealershipAPI.service.SalesContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.dgc.Lease;
import java.util.List;

@RestController
public class LeaseContractsController {
    private final LeaseContractService leaseContractService;

    @Autowired
    public LeaseContractsController(LeaseContractService leaseContractService) {
        this.leaseContractService = leaseContractService;
    }

    @RequestMapping(path = "/lease_contracts", method = RequestMethod.GET)
    public List<LeaseContract> getAllLeaseContracts() {
        return leaseContractService.getAllLeaseContracts();
    }

    @RequestMapping(path = "/lease_contracts/{id}", method = RequestMethod.GET)
    public LeaseContract getLeaseContractById(@PathVariable int id) {
        return leaseContractService.getLeaseContractById(id);
    }

    @RequestMapping(path = "/lease_contracts", method = RequestMethod.POST)
    public boolean addLeaseContract (@RequestBody LeaseContract leaseContract) {
        return leaseContractService.addLeaseContract(leaseContract);
    }
}
