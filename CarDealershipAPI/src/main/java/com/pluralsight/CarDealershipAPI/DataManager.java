//package com.pluralsight;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.commons.dbcp2.SQLExceptionList;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//public class DataManager {
//    private BasicDataSource dataSource;
//
//    public DataManager(DataSource dataSource) {
//        this.dataSource = (BasicDataSource) dataSource;
//    }
//
//    public DataManager(String url, String username, String password) {
//        this.dataSource = new BasicDataSource();
//        this.dataSource.setUrl(url);
//        this.dataSource.setUsername(username);
//        this.dataSource.setPassword(password);
//    }
//
//    public BasicDataSource getDataSource() {
//        return dataSource;
//    }
//
//    public void close () throws SQLException {
//        if (dataSource != null){
//            dataSource.close();
//        }
//    }
//}
