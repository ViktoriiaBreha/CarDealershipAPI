package com.pluralsight;

import com.pluralsight.CarDealershipAPI.DAO.LeaseDao;
import com.pluralsight.CarDealershipAPI.DAO.SalesDao;
import com.pluralsight.models.Dealership;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;


    public UserInterface() {
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        String url = System.getenv("url");
        String user = System.getenv("user");
        String password = System.getenv("password");
        if (url == null) {
            url = "jdbc:mysql://127.0.0.1:3306/sakila";
        }

        com.pluralsight.DataManager dataManager = new com.pluralsight.DataManager(url, user, password);
        boolean run = true;
        while (run) {
            try {
                VehicleDAO vehicleDAO = new VehicleDAO(dataManager);
                SalesDao salesDao = new SalesDao(dataManager);
                LeaseDao leaseDao = new LeaseDao(dataManager);

                displayMenuOptions();

                System.out.print("Enter your choice: ");
                int choice1 = scanner.nextInt();
                scanner.nextLine();

                switch (choice1) {
                    case 1:
                        processGetByPriceRequest(vehicleDAO, scanner);
                        break;
                    case 2:
                        processGetByMakeModelRequest(vehicleDAO, scanner);
                        break;
                    case 3:
                        processGetByYearRequest(vehicleDAO, scanner);
                        break;
                    case 4:
                        processGetByColorRequest(vehicleDAO, scanner);
                        break;
                    case 5:
                        processGetByMileageRequest(vehicleDAO, scanner);
                        break;
                    case 6:
                        processGetByVehicleTypeRequest(vehicleDAO, scanner);
                        break;
                    case 7:
                        processGetAllVehicleRequest(vehicleDAO);
                        break;
                    case 8:
                        processAddVehicleRequest(vehicleDAO, scanner);
                        break;
                    case 9:
                        processRemoveVehicleRequest(vehicleDAO, scanner);
                        break;
                    case 10:
                        processContractRequest(vehicleDAO,salesDao,leaseDao,scanner );
                        break;
                    case 0:
                        run = false;
                        System.exit(0);
                        break;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //    private void init(){
//        DealershipFileManager fileManager = new DealershipFileManager();
//        this.dealership=fileManager.getDealership();
//    }
    public void displayMenuOptions() {
        System.out.println("*****Welcome to the Dealership!!!*****");
        System.out.println(" ");
        System.out.println("***Our Options***");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("10 - Sell/Lease a vehicle");
        System.out.println("0 - Quit");
    }

    public void processGetByPriceRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter minimum value: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum value: ");
        double maxPrice = scanner.nextDouble();
        displayVehicles(vehicleDAO.getAllVehiclesByPriceRange(minPrice, maxPrice));


    }

    public void processGetByMakeModelRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(vehicleDAO.getAllVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        displayVehicles(vehicleDAO.getAllVehiclesByYearRange(minYear, maxYear));
    }

    public void processGetByColorRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(vehicleDAO.getAllVehiclesByColor(color));
    }

    public void processGetByMileageRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter minimum mileage: ");
        int minMil = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMil = scanner.nextInt();
        displayVehicles(vehicleDAO.getAllVehiclesByMileageRange(minMil, maxMil));
    }

    public void processGetByVehicleTypeRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        displayVehicles(vehicleDAO.getAllVehiclesByType(type));
    }

    public void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public void processGetAllVehicleRequest(VehicleDAO vehicleDAO) {
        displayVehicles(vehicleDAO.getAllVehicles());
    }

    public void processAddVehicleRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        System.out.print("Enter vin: ");
        String vin = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter sold (1(yes)/0(no)): ");
        int isSold = scanner.nextInt();
        scanner.nextLine();
        boolean soldStatus = false;
        if (isSold == 1) {
            soldStatus = true;
        }

        Vehicle v = new Vehicle(vin, year, make, model, type, color, mileage, price, soldStatus);
        vehicleDAO.addVehicle(v);

    }

    public void processRemoveVehicleRequest(VehicleDAO vehicleDAO, Scanner scanner) {
        displayVehicles(vehicleDAO.getAllVehicles());

        System.out.print("Enter vin that you want to remove: ");
        String removedVin = scanner.nextLine();

        vehicleDAO.removeVehicle(removedVin);
    }

    public void processContractRequest(VehicleDAO vehicleDAO, SalesDao salesDao, LeaseDao leaseDao, Scanner scanner) {
        System.out.println("Choose from next option:");
        System.out.println("1. Sale Contract");
        System.out.println("2. Lease Contract");
        System.out.println(" ");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        LocalDate localDate = LocalDate.now();
        String date_time = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        switch (choice) {

            case 1:
                System.out.print("Enter VIN of the vehicle: ");
                String vin_veh = scanner.nextLine();

                scanner.nextLine();


                System.out.println("Would you like to have finance? (yes/no)");
                System.out.print("Your answer: ");
                String yes_no = scanner.nextLine();
                boolean answer = true;

                if (yes_no.equalsIgnoreCase("yes")) {
                    answer = true;

                } else {
                    answer = false;

                }

                SalesContract salesContract = new SalesContract(0, vin_veh, 0.00, 0.00, 100, answer,
                        LocalDate.now(), vehicleDAO.getVehicleByVin(vin_veh));
                salesDao.addContract(salesContract);

                break;
            case 2:
                System.out.print("Enter VIN of the vehicle: ");
                String vin_veh2 = scanner.nextLine();
                scanner.nextLine();

                LeaseContract leaseContract = new LeaseContract(0,0.00,0.00, vin_veh2, vehicleDAO.getVehicleByVin(vin_veh2));

                leaseDao.addContract(leaseContract);
                break;
            default:
                System.out.println("Invalid input. Try again!");
                break;
        }


    }
}
