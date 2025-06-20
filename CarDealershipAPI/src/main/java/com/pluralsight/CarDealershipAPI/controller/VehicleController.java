package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.util.List;

@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController (VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @RequestMapping(path = "/car_dealership", method = RequestMethod.GET)
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @RequestMapping(path = "/car_dealership/priceRange", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByPriceRange(@RequestParam double min,@RequestParam double max) {
        return vehicleService.getAllVehiclesByPriceRange(min, max);
    }

    @RequestMapping(path = "/car_dealership/makeModel", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByMakeModel(@RequestParam String make, @RequestParam String model) {
        return vehicleService.getAllVehiclesByMakeModel(make, model);
    }

    @RequestMapping (path = "/car_dealership/yearRange", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByYearRange(@RequestParam int min, @RequestParam int max ) {
        return vehicleService.getAllVehiclesByYearRange(min, max);
    }

    @RequestMapping (path = "/car_dealership/color", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByColor(@RequestParam String color) {
        return vehicleService.getAllVehiclesByColor(color);
    }

    @RequestMapping (path = "/car_dealership/mileageRange", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByMileageRange(@RequestParam int min, @RequestParam int max ) {
        return vehicleService.getAllVehiclesByMileageRange(min, max);
    }

    @RequestMapping (path = "/car_dealership/type", method = RequestMethod.GET)
    public List<Vehicle> getAllVehiclesByType(@RequestParam String type ) {
        return vehicleService.getAllVehiclesByType(type);
    }

    @RequestMapping (path = "/car_dealership", method = RequestMethod.POST)
    public boolean addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @RequestMapping (path = "/car_dealership", method = RequestMethod.PUT)
    public boolean updateVehicle(@RequestBody String vin,Vehicle vehicle) {
        return vehicleService.updateVehicle(vin, vehicle);
    }

    @RequestMapping (path = "/car_dealership", method = RequestMethod.DELETE)
    public boolean removeVehicle(@RequestBody String vin) {
        return vehicleService.removeVehicle(vin);
    }

}
