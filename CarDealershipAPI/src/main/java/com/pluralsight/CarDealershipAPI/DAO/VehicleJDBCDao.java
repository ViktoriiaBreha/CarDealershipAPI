package com.pluralsight.CarDealershipAPI.DAO;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 @Component
public class VehicleJDBCDao implements VehicleDaoInter {
     private DataSource dataSource;

    @Autowired
    public VehicleJDBCDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> getAllVehicles (){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet results = preparedStatement.executeQuery()) {

            while (results.next()){
                Vehicle vehicle = createVehicleFromResultSet(results);
                vehicles.add(vehicle);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByPriceRange(double min, double max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1,min);
            preparedStatement.setDouble(2,max);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make LIKE ? OR model LIKE ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,make);
            preparedStatement.setString(2,model);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByYearRange(int min, int max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,min);
            preparedStatement.setInt(2,max);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByColor(String color){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE color LIKE ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,color);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByMileageRange(int min, int max){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,min);
            preparedStatement.setInt(2,max);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public List<Vehicle> getAllVehiclesByType(String type){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE type LIKE ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,type);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()){
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

     @Override
    public Vehicle getVehicleByVin(String vin){
        Vehicle vehicle = new Vehicle();
        String query = "SELECT * FROM vehicles WHERE vin LIKE ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,vin);

            try (ResultSet results = preparedStatement.executeQuery()) {

                if(results.next()){
                    vehicle = createVehicleFromResultSet(results);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicle;
    }

     @Override
    public boolean addVehicle (Vehicle vehicle){
        String query = "INSERT INTO vehicles (vin, year, make, model, type, color, mileage, price, sold) VALUES" +
                " " +
                "(?,?,?,?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5,vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());
            statement.setBoolean(9, vehicle.isSold());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error creating vehicle: " + e.getMessage());
        }

        return false;
    }

     @Override
    public boolean removeVehicle (String vin){
        String query = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, vin);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

     @Override
     public boolean updateVehicle(String vin, Vehicle vehicle) {

         return false;
     }

     private Vehicle createVehicleFromResultSet (ResultSet results ) throws SQLException{
        Vehicle vehicle = new Vehicle();

        vehicle.setVin(results.getString("vin"));
        vehicle.setYear(results.getInt("year"));
        vehicle.setMake(results.getString("make"));
        vehicle.setModel(results.getString("model"));
        vehicle.setVehicleType(results.getString("type"));
        vehicle.setColor(results.getString("color"));
        vehicle.setOdometer(results.getInt("mileage"));
        vehicle.setPrice(results.getDouble("price"));
        vehicle.setSold(results.getBoolean("sold"));

        return vehicle;
    }
}
