package com.cdevworks.crudapphjs.setup;

import com.cdevworks.crudapphjs.persistance.dao.EmployeeDao;
import com.cdevworks.crudapphjs.persistance.entity.Address;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
import com.cdevworks.crudapphjs.persistance.entity.FullTimeEmployee;
import com.cdevworks.crudapphjs.persistance.entity.PartTimeEmployee;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class DBUtil {

    public static void run() {

        try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            Connection connection;
            String url = properties.getProperty("db.url");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.getConnection(url, (String) properties.get("db.user"), properties.getProperty("db.pass"));
            } catch (SQLException e) {
                if (e.getErrorCode() == 1049) {
                    connection = DriverManager.getConnection(url.substring(0, url.lastIndexOf("/")), (String) properties.get("db.user"), properties.getProperty("db.pass"));
                    connection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + url.substring(url.lastIndexOf("/") + 1));
                    populateEmployeeTable();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void populateEmployeeTable() {
        List<Employee> employees = List.of(
                new FullTimeEmployee("Rajesh Kumar", new Address("Tamil Nadu", "Chennai"), LocalDate.of(2022, 1, 15), 60000),
                new PartTimeEmployee("Sita Devi", new Address("Karnataka", "Bangalore"), LocalDate.of(2023, 3, 22), 400),
                new FullTimeEmployee("Ravi Menon", new Address("Kerala", "Kochi"), LocalDate.of(2021, 7, 10), 75000),
                new PartTimeEmployee("Anil Sharma", new Address("Delhi", "New Delhi"), LocalDate.of(2024, 5, 8), 450),
                new FullTimeEmployee("Suresh Nair", new Address("Andhra Pradesh", "Hyderabad"), LocalDate.of(2020, 11, 25), 70000),
                new PartTimeEmployee("Meera Patel", new Address("Gujarat", "Ahmedabad"), LocalDate.of(2023, 6, 30), 500),
                new FullTimeEmployee("Kumar Reddy", new Address("Telangana", "Hyderabad"), LocalDate.of(2022, 2, 14), 65000),
                new PartTimeEmployee("Pooja Gupta", new Address("Uttar Pradesh", "Lucknow"), LocalDate.of(2024, 7, 19), 480),
                new FullTimeEmployee("Ajay Babu", new Address("Tamil Nadu", "Madurai"), LocalDate.of(2019, 4, 12), 68000),
                new PartTimeEmployee("Nisha Rao", new Address("Karnataka", "Mysore"), LocalDate.of(2024, 8, 15), 490),
                new FullTimeEmployee("Vikram Singh", new Address("Kerala", "Thrissur"), LocalDate.of(2018, 10, 30), 72000),
                new PartTimeEmployee("Sanjay Patel", new Address("Gujarat", "Surat"), LocalDate.of(2024, 9, 1), 450),
                new FullTimeEmployee("Anand Kumar", new Address("Andhra Pradesh", "Vijayawada"), LocalDate.of(2021, 6, 5), 73000),
                new PartTimeEmployee("Radha Iyer", new Address("Maharashtra", "Pune"), LocalDate.of(2023, 10, 10), 500),
                new FullTimeEmployee("Deepak Reddy", new Address("Telangana", "Secunderabad"), LocalDate.of(2022, 12, 20), 69000),
                new PartTimeEmployee("Aarti Sharma", new Address("Delhi", "Noida"), LocalDate.of(2024, 1, 12), 470),
                new FullTimeEmployee("Mohan Raj", new Address("Tamil Nadu", "Coimbatore"), LocalDate.of(2020, 9, 8), 67000),
                new PartTimeEmployee("Sonal Desai", new Address("Karnataka", "Hubli"), LocalDate.of(2024, 2, 14), 480),
                new FullTimeEmployee("Ramesh Kumar", new Address("Kerala", "Kollam"), LocalDate.of(2019, 5, 18), 74000),
                new PartTimeEmployee("Sonia Singh", new Address("Uttar Pradesh", "Agra"), LocalDate.of(2023, 11, 25), 500),
                new FullTimeEmployee("Narayana Rao", new Address("Andhra Pradesh", "Tirupati"), LocalDate.of(2022, 8, 4), 75000),
                new PartTimeEmployee("Deepika Gupta", new Address("Gujarat", "Vadodara"), LocalDate.of(2024, 3, 20), 460),
                new FullTimeEmployee("Krishna Reddy", new Address("Telangana", "Karimnagar"), LocalDate.of(2019, 12, 5), 76000),
                new PartTimeEmployee("Manisha Patel", new Address("Maharashtra", "Nagpur"), LocalDate.of(2024, 4, 14), 490),
                new FullTimeEmployee("Sanjay Kumar", new Address("Tamil Nadu", "Tirunelveli"), LocalDate.of(2021, 11, 30), 68000),
                new PartTimeEmployee("Priya Menon", new Address("Karnataka", "Bellary"), LocalDate.of(2023, 7, 15), 470),
                new FullTimeEmployee("Srinivas Reddy", new Address("Kerala", "Palakkad"), LocalDate.of(2020, 3, 5), 70000),
                new PartTimeEmployee("Rakhi Rao", new Address("Delhi", "Faridabad"), LocalDate.of(2024, 5, 10), 480),
                new FullTimeEmployee("Ajit Singh", new Address("Andhra Pradesh", "Guntur"), LocalDate.of(2018, 10, 12), 69000),
                new PartTimeEmployee("Jaya Desai", new Address("Gujarat", "Rajkot"), LocalDate.of(2024, 6, 22), 500)
        );
        EmployeeDao employeeDao = new EmployeeDao();
        for (Employee employee : employees) {
            employeeDao.add(employee);
        }
    }
}
