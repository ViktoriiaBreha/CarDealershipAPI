package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class SalesDao {

    private BasicDataSource dataSource;

    public SalesDao(com.pluralsight.DataManager dataManager) {
        this.dataSource = dataManager.getDataSource();
    }

    public boolean addContract(SalesContract salesContract){
        String query = "INSERT INTO sales_contracts (contract_id, vin, sales_tax, recording_fee, processing_fee, " +
                "finance_status," +
                "sale_date )" +
                "VALUES" +
                "(?,?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, salesContract.getContract_id());
            statement.setString(2, salesContract.getVin());
            statement.setDouble(3, salesContract.getSalesTaxAmount());
            statement.setDouble(4, salesContract.getRecording_fee());
            statement.setDouble(5,salesContract.getProcessingFee());
            statement.setBoolean(6, salesContract.isFinance_status());
            statement.setDate(7, Date.valueOf(salesContract.getSale_date()));


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
                try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        salesContract.setContract_id(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error creating sales contract: " + e.getMessage());
        }

        return false;
    }

    private SalesContract createSalesContract (ResultSet results ) throws SQLException {

        SalesContract salesContract = new SalesContract();

        salesContract.setContract_id(results.getInt("contract_id"));
        salesContract.setVin(results.getString("vin"));
        salesContract.setSales_tax(results.getDouble("sales_tax"));
        salesContract.setRecording_fee(results.getDouble("recording_fee"));
        salesContract.setProcessing_fee(results.getDouble("processing_fee"));
        salesContract.setFinance_status(results.getBoolean("finance_status"));
        salesContract.setSale_date(results.getDate("sale_date").toLocalDate());

        return salesContract;

    }
}
