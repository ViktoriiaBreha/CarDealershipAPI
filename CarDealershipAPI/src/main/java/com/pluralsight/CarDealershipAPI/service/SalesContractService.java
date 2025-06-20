package com.pluralsight.CarDealershipAPI.service;

import com.pluralsight.CarDealershipAPI.DAO.SalesDaoInter;
import com.pluralsight.CarDealershipAPI.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesContractService {

    private final SalesDaoInter salesDaoInter;

    @Autowired
    public SalesContractService (SalesDaoInter salesDaoInter){
        this.salesDaoInter = salesDaoInter;
    }

    public List<SalesContract> getAllSalesContracts(){
        return salesDaoInter.getAllSalesContracts();
    }

    public SalesContract getSalesContractById (int id){
        return salesDaoInter.getSalesContractById(id);
    }

    public boolean addSalesContract (SalesContract salesContract){
        return  salesDaoInter.addSalesContract(salesContract);
    }
}
