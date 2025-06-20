package com.pluralsight.CarDealershipAPI.models;


public class LeaseContract extends Contract {

    private double ending_value;
    private double lease_fee;
    private int contract_id;
    private String vin;
    private Vehicle vehicle_sold;

    public LeaseContract(){};
    public LeaseContract(int contract_id, double ending_value, double lease_fee, String vin, Vehicle vehicle_sold) {
        this.contract_id = contract_id;
        this.ending_value = ending_value;
        this.lease_fee = lease_fee;
        this.vin = vin;
        this.vehicle_sold = vehicle_sold;
    }

    public LeaseContract(int contract_id, double ending_value, double lease_fee, String vin) {
        this.contract_id = contract_id;
        this.ending_value = ending_value;
        this.lease_fee = lease_fee;
        this.vin = vin;
    }

//    public LeaseContract(String date_of_contract, String customer_name, String customer_email, Vehicle vehicle_sold, double total_price, double monthly_payment, int contract_id, double ending_value, double lease_fee, String vin) {
//        super(date_of_contract, vehicle_sold, total_price, monthly_payment);
//        this.contract_id = contract_id;
//        this.ending_value = ending_value;
//        this.lease_fee = lease_fee;
//        this.vin = vin;
//    }


    @Override
    public double getTotal_price() {
        return getExpectedEndingValue()+getLeaseFee();
    }

    public double getExpectedEndingValue(){
        double ending_value = vehicle_sold.getPrice();
        ending_value *=0.5;
        return ending_value;
    }

    public  double getLeaseFee(){
        double fee = vehicle_sold.getPrice() * 0.07;

        return fee;
    }

    @Override
    public double getMonthly_payment() {
        double reduced_price = getTotal_price();

        double interest_over_year = (reduced_price)*(0.04 / 12);
        reduced_price = reduced_price/12;


        return reduced_price + interest_over_year ;
    }

    public double getEnding_value() {
        return ending_value;
    }

    public double getLease_fee() {
        return lease_fee;
    }

    public int getContract_id() {
        return contract_id;
    }

    public String getVin() {
        return vin;
    }

    public void setEnding_value(double ending_value) {
        this.ending_value = ending_value;
    }

    public void setLease_fee(double lease_fee) {
        this.lease_fee = lease_fee;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public Vehicle getVehicle_sold() {
        return vehicle_sold;
    }

    @Override
    public void setVehicle_sold(Vehicle vehicle_sold) {
        this.vehicle_sold = vehicle_sold;
    }
}
