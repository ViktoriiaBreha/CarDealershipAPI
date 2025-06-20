package com.pluralsight.CarDealershipAPI.DAO;

import java.util.List;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

public interface VehicleDaoInter {

    public List<Vehicle> getAllVehicles();
    public List<Vehicle> getAllVehiclesByPriceRange(double min, double max);
    public List<Vehicle> getAllVehiclesByMakeModel(String make, String model);
    public List<Vehicle> getAllVehiclesByYearRange(int min, int max);
    public List<Vehicle> getAllVehiclesByColor(String color);
    public List<Vehicle> getAllVehiclesByMileageRange(int min, int max);
    public List<Vehicle> getAllVehiclesByType(String type);
    public Vehicle getVehicleByVin(String vin);
    public boolean addVehicle(Vehicle vehicle);
    public boolean removeVehicle(String vin);
    public boolean updateVehicle(String vin, Vehicle vehicle);

}
