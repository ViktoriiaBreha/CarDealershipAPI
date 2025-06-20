package com.pluralsight.CarDealershipAPI.service;

import com.pluralsight.CarDealershipAPI.DAO.LeaseDaoInter;
import com.pluralsight.CarDealershipAPI.DAO.SalesDaoInter;
import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseContractService {
    private final LeaseDaoInter leaseDaoInter;

    @Autowired
    public LeaseContractService (LeaseDaoInter leaseDaoInter){
        this.leaseDaoInter = leaseDaoInter;
    }

    public List<LeaseContract> getAllLeaseContracts(){
        return leaseDaoInter.getAllLeaseContracts();
    }

    public LeaseContract getLeaseContractById (int id){
        return leaseDaoInter.getLeaseContractById(id);
    }

    public boolean addLeaseContract (LeaseContract leaseContract){
        return  leaseDaoInter.addLeaseContract(leaseContract);
    }
}
