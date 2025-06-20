package com.pluralsight.models;

import java.time.LocalDate;
import java.util.Date;

public class SalesContract extends com.pluralsight.models.Contract {
    private double sales_tax;
    private double recording_fee;
    private double processing_fee;
    private boolean finance_status;
    private String vin;
    private int contract_id;
    private LocalDate sale_date;
    private Vehicle vehicle_sold;

    public SalesContract (){};

    public SalesContract(int contract_id, String vin, double sales_tax, double recording_fee, double processing_fee,
                         boolean finance_status, LocalDate sale_date, Vehicle vehicle_sold) {
        this.contract_id = contract_id;
        this.vin = vin;
        this.sales_tax = sales_tax;
        this.recording_fee = recording_fee;
        this.processing_fee = processing_fee;
        this.finance_status = finance_status;
        this.sale_date = sale_date;
        this.vehicle_sold = vehicle_sold;
    }

//    public SalesContract(String date_of_contract, Vehicle vehicle_sold, double total_price, double monthly_payment, int contract_id, String vin, double sales_tax, double recording_fee, double processing_fee, boolean finance_status, Date sale_date) {
//        super(date_of_contract, vehicle_sold, total_price, monthly_payment);
//        this.contract_id = contract_id;
//        this.vin = vin;
//        this.sales_tax = sales_tax;
//        this.recording_fee = recording_fee;
//        this.processing_fee = processing_fee;
//        this.finance_status = finance_status;
//        this.sale_date = sale_date;
//    }

    @Override
    public double getTotal_price() {
        double sales_tax = getSalesTaxAmount(), processing_fee = getProcessingFee(), recording_fee =
                getRecording_fee(), car_price = vehicle_sold.getPrice(), total_price = 0;

        total_price += car_price + sales_tax + recording_fee + processing_fee;

        return total_price;

    }

    public void isFinance(boolean answer) {
        if (answer == true) {
            this.finance_status = true;
        } else {
            this.finance_status = false;
        }

    }

    public double getSalesTaxAmount() {
        double car_price = vehicle_sold.getPrice();
        total_price = car_price * 0.05;
        return total_price;
    }

    public double getProcessingFee() {
        double car_price = vehicle_sold.getPrice();

        if (car_price < 10000) {
            processing_fee = 295;
        } else {
            processing_fee = 495;
        }
        return processing_fee;
    }


    @Override
    public double getMonthly_payment() {
        double monthly_pay;
        if (this.finance_status == false) {
            return 0;
        } else {

            double total = getTotal_price();
            double loan;


            if (getTotal_price() >= 10000) {
                loan = 0.0425 / 12;

            } else {
                loan = 0.0525 / 12;

            }
            monthly_pay = total / 12;
            double interest_gain = monthly_pay * loan;
            monthly_pay = interest_gain + monthly_pay;

        }
        return monthly_pay;
    }

    public double getSales_tax() {
        return sales_tax;
    }

    public double getRecording_fee() {
        return 100;
    }

    public boolean isFinance_status() {
        return finance_status;
    }

    public void setSales_tax(double sales_tax) {
        this.sales_tax = sales_tax;
    }

    public void setProcessing_fee(double processing_fee) {
        this.processing_fee = processing_fee;
    }

    public void setRecording_fee(double recording_fee) {
        this.recording_fee = recording_fee;
    }

    public void setFinance_status(boolean finance_status) {
        this.finance_status = finance_status;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public void setSale_date(LocalDate sale_date) {
        this.sale_date = sale_date;
    }

    public double getProcessing_fee() {
        return processing_fee;
    }

    public String getVin() {
        return vin;
    }

    public int getContract_id() {
        return contract_id;
    }

    public LocalDate getSale_date() {
        return sale_date;
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

