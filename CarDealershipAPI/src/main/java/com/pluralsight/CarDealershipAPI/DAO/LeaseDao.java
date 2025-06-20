package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class LeaseDao {
    private BasicDataSource dataSource;

    public LeaseDao(com.pluralsight.DataManager dataManager) {
        this.dataSource = dataManager.getDataSource();
    }

    public boolean addContract(LeaseContract leaseContract){
        String query = "INSERT INTO lease_contracts (contract_id, ending_value, lease_fee, vin )" +
                "VALUES" +
                "(?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, leaseContract.getContract_id());
            statement.setDouble(2, leaseContract.getExpectedEndingValue());
            statement.setDouble(3, leaseContract.getLeaseFee());
            statement.setString(4, leaseContract.getVin());


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
                try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        leaseContract.setContract_id(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error creating sales contract: " + e.getMessage());
        }

        return false;
    }

    private LeaseContract createLeaseContract (ResultSet results ) throws SQLException {

        LeaseContract leaseContract = new LeaseContract();

        leaseContract.setContract_id(results.getInt("contract_id"));
        leaseContract.setEnding_value(results.getDouble("ending_value"));
        leaseContract.setLease_fee(results.getDouble("lease_fee"));
        leaseContract.setVin(results.getString("vin"));

        return leaseContract;

    }
}
