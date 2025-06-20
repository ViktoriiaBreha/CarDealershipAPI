package com.pluralsight.models;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.inventory = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice (double min, double max){;
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        double inputMin = min, inputMax = max;
        for (Vehicle v : inventory) {
            if (inputMin< v.getPrice() && inputMax>v.getPrice()){
                vehicles.add(v);
            }
        }
        return vehicles;
    }


    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String inputMake = make, inputModel = model;
        for (Vehicle v : inventory){
            if (v.getMake().equalsIgnoreCase(inputMake)&& v.getModel().equalsIgnoreCase(inputModel)){
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByYear (int min, int max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int inputMin = min, inputMax = max;
        for (Vehicle v : inventory) {
            if (inputMin< v.getYear() && inputMax>v.getYear()){
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByColor (String color){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String inputColor = color;
        for (Vehicle v : inventory){
            if (v.getColor().equalsIgnoreCase(inputColor)){
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMileage (int min, int max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int inputMin = min, inputMax = max;
        for (Vehicle v : inventory) {
            if (inputMin< v.getOdometer() && inputMax>v.getOdometer()){
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByType (String vehicleType){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String inputType = vehicleType;
        for (Vehicle v : inventory){
            if (v.getVehicleType().equalsIgnoreCase(inputType)){
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public Vehicle getVehicleByTheVin(String vin){
        Vehicle vehicle = null;
        for (Vehicle v : inventory){
            if (v.getVin() == vin){
                vehicle = v;
            }
        }
        return vehicle;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return this.inventory;
    }

    public void addVehicle (Vehicle vehicle){

        this.inventory.add(vehicle);

    }

    public void removeVehicle (Vehicle vehicle){
        this.inventory.remove(vehicle);
    }

}
