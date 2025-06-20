package com.pluralsight.models;

import com.pluralsight.models.Vehicle;

public abstract class Contract {
    protected String date_of_contract;
    protected Vehicle vehicle_sold;
    protected double total_price;
    protected double monthly_payment;

    public Contract(String date_of_contract,  Vehicle vehicle_sold,
                    double total_price, double monthly_payment) {
        this.date_of_contract = date_of_contract;
        this.vehicle_sold = vehicle_sold;
        this.total_price = total_price;
        this.monthly_payment = monthly_payment;
    }
    public Contract (){};

    public String getDate_of_contract() {
        return date_of_contract;
    }


    public Vehicle getVehicle_sold() {
        return vehicle_sold;
    }

    public void setDate_of_contract(String date_of_contract) {
        this.date_of_contract = date_of_contract;
    }

    public void setVehicle_sold(Vehicle vehicle_sold) {
        this.vehicle_sold = vehicle_sold;
    }

    public abstract double getTotal_price();
    public abstract double getMonthly_payment();
}
