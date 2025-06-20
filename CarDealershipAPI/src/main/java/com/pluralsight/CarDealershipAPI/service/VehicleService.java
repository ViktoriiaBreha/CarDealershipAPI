package com.pluralsight.CarDealershipAPI.service;

import com.pluralsight.CarDealershipAPI.DAO.VehicleDaoInter;
import com.pluralsight.CarDealershipAPI.DAO.VehicleJDBCDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pluralsight.models.Vehicle;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleDaoInter vehicleDaoInter;

    @Autowired
    public VehicleService (VehicleDaoInter vehicleDaoInter){
        this.vehicleDaoInter = vehicleDaoInter;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleDaoInter.getAllVehicles();
    }

    public List<Vehicle> getAllVehiclesByPriceRange (double min, double max){
        return vehicleDaoInter.getAllVehiclesByPriceRange(min, max);
    }

    public List<Vehicle> getAllVehiclesByMakeModel(String make, String model){
        return vehicleDaoInter.getAllVehiclesByMakeModel(make, model);
    }

    public List<Vehicle> getAllVehiclesByYearRange(int min, int max){
        return vehicleDaoInter.getAllVehiclesByYearRange(min, max);
    }

    public List<Vehicle> getAllVehiclesByColor(String color){
        return vehicleDaoInter.getAllVehiclesByColor(color);
    }

    public List<Vehicle> getAllVehiclesByMileageRange(int min, int max) {
        return vehicleDaoInter.getAllVehiclesByMileageRange(min, max);
    }

    public List<Vehicle> getAllVehiclesByType(String type){
        return vehicleDaoInter.getAllVehiclesByType(type);
    }

    public Vehicle getVehicleByVin(String vin){
        return vehicleDaoInter.getVehicleByVin(vin);
    }

    public boolean addVehicle(Vehicle vehicle){
        return vehicleDaoInter.addVehicle(vehicle);
    }

    public boolean removeVehicle(String vin){
        return vehicleDaoInter.removeVehicle(vin);
    }

    public boolean updateVehicle(String vin, Vehicle vehicle){
        return vehicleDaoInter.updateVehicle(vin, vehicle);
    }

}

