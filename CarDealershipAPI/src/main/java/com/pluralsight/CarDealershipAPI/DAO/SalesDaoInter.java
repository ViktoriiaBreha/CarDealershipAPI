package com.pluralsight.CarDealershipAPI.DAO;
import com.pluralsight.CarDealershipAPI.models.SalesContract;

import java.util.List;

public interface SalesDaoInter {

    public SalesContract getSalesContractById (int id);
    public List<SalesContract> getAllSalesContracts();
    public boolean addSalesContract (SalesContract salesContract);
}
