package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.CarDealershipAPI.models.SalesContract;
import com.pluralsight.CarDealershipAPI.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesDaoJDBC implements SalesDaoInter {

    private final DataSource dataSource;


    private SalesContract salesContract;
    @Autowired
    public SalesDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<SalesContract> getAllSalesContracts() {
        ArrayList<SalesContract> salesContracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet results = preparedStatement.executeQuery()) {

            while (results.next()){
                SalesContract salesContract = createSalesContract(results);
                salesContracts.add(salesContract);
//                salesContracts.add(new SalesContract(
//                        results.getInt(1),
//                        results.getString(2),
//                        results.getDouble(3),
//                        results.getDouble(4),
//                        results.getDouble(5),
//                        results.getBoolean(6),
//                        results.getDate(7)));

//                Vehicle vehicle = createVehicleFromResultSet(results);
//                vehicles.add(vehicle);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return salesContracts;
    }

    @Override
    public SalesContract getSalesContractById(int id) {

        SalesContract salesContract =new SalesContract();
        String query = "SELECT * FROM sales_contracts WHERE contract_id = ?";
                try (Connection connection = dataSource.getConnection()){
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1,id);
                    ResultSet results = preparedStatement.executeQuery();

                    if (results.next()){
                        salesContract = new SalesContract(
                                results.getInt(1),
                                results.getString(2),
                                results.getDouble(3),
                                results.getDouble(4),
                                results.getDouble(5),
                                results.getBoolean(6),
                                results.getDate(7)
                        );
                    }
                    //return this.salesContract;
                } catch (SQLException e){
                    e.printStackTrace();
                }
        return salesContract;
    }

    @Override
    public boolean addSalesContract(SalesContract salesContract){
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
            statement.setDate(7, (Date) salesContract.getSale_date());


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
        salesContract.setSale_date(results.getDate("sale_date"));

        return salesContract;

    }


}
