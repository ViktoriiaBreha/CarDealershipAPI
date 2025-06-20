package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.SalesContract;

import java.util.List;


public interface LeaseDaoInter {

    public LeaseContract getLeaseContractById (int id);
    public List<LeaseContract> getAllLeaseContracts();
    public boolean addLeaseContract (LeaseContract leaseContract);
}
