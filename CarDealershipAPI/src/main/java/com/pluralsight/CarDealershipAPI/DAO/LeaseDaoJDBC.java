package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseDaoJDBC implements LeaseDaoInter {
    private DataSource dataSource;

    private List<LeaseContract> leaseContracts;
    private LeaseContract leaseContract;

    @Autowired
    public LeaseDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addLeaseContract(LeaseContract leaseContract){
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

    @Override
    public LeaseContract getLeaseContractById(int id) {
        String query = "SELECT * FROM lease_contracts WHERE contract_id = ?";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()){
                leaseContract = new LeaseContract(
                        results.getInt(1),
                        results.getDouble(2),
                        results.getDouble(3),
                        results.getString(4));
            }
            return leaseContract;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LeaseContract> getAllLeaseContracts() {
        ArrayList<LeaseContract> leaseContracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet results = preparedStatement.executeQuery()) {

            while (results.next()){
                this.leaseContracts.add(new LeaseContract(
                        results.getInt(1),
                        results.getDouble(2),
                        results.getDouble(3),
                        results.getString(4)));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return leaseContracts;
    }

//    private LeaseContract createLeaseContract (ResultSet results ) throws SQLException {
//
//        LeaseContract leaseContract = new LeaseContract();
//
//        leaseContract.setContract_id(results.getInt("contract_id"));
//        leaseContract.setEnding_value(results.getDouble("ending_value"));
//        leaseContract.setLease_fee(results.getDouble("lease_fee"));
//        leaseContract.setVin(results.getString("vin"));
//
//        return leaseContract;
//
//    }
}
