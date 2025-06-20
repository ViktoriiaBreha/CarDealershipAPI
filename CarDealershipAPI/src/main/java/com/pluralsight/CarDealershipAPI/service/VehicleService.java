package com.pluralsight.CarDealershipAPI.service;

import com.pluralsight.CarDealershipAPI.DAO.VehicleDaoInter;
import com.pluralsight.CarDealershipAPI.DAO.VehicleJDBCDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleDaoInter vehicleDaoInter;

    @Autowired
    public VehicleService (VehicleDaoInter vehicleDaoInter){
        this.vehicleDaoInter = vehicleDaoInter;
    }
    
}
