package com.cdevworks.crudapphjs.setup;

import com.cdevworks.crudapphjs.persistance.dao.EmployeeDao;
import com.cdevworks.crudapphjs.persistance.entity.Employee;

import java.io.FileInputStream;
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
                new Employee("Rajesh Kumar", 50000, "Chennai", LocalDate.of(2022, 1, 15), 1),
                new Employee("Sita Devi", 55000, "Bangalore", LocalDate.of(2021, 7, 22), 1),
                new Employee("Amit Sharma", 60000, "Noida", LocalDate.of(2023, 3, 10), 2),
                new Employee("Anita Patel", 65000, "Mumbai", LocalDate.of(2020, 11, 30), 2),
                new Employee("Rajiv Singh", 70000, "Hyderabad", LocalDate.of(2019, 9, 5), 3),
                new Employee("Sunita Rani", 72000, "Kolkata", LocalDate.of(2018, 6, 12), 3),
                new Employee("Vijay Kumar", 48000, "Jaipur", LocalDate.of(2021, 5, 25), 4),
                new Employee("Pooja Joshi", 50000, "Greater Noida", LocalDate.of(2022, 4, 20), 4),
                new Employee("Manish Gupta", 52000, "Pune", LocalDate.of(2020, 10, 16), 5),
                new Employee("Neeta Nair", 54000, "Ahmedabad", LocalDate.of(2022, 2, 8), 5),
                new Employee("Rajendra Rao", 56000, "Bangalore", LocalDate.of(2021, 12, 19), 6),
                new Employee("Priya Menon", 58000, "Chennai", LocalDate.of(2019, 8, 14), 6),
                new Employee("Suresh Reddy", 60000, "Mumbai", LocalDate.of(2020, 7, 11), 7),
                new Employee("Kavita Sharma", 62000, "Delhi", LocalDate.of(2019, 6, 23), 7),
                new Employee("Anil Kumar", 64000, "Jaipur", LocalDate.of(2018, 5, 30), 8),
                new Employee("Rekha Bhardwaj", 66000, "Hyderabad", LocalDate.of(2021, 1, 22), 8),
                new Employee("Ravi Patil", 68000, "Pune", LocalDate.of(2022, 8, 15), 9),
                new Employee("Sheetal Agarwal", 70000, "Kolkata", LocalDate.of(2020, 11, 18), 9),
                new Employee("Ajay Bhat", 72000, "Delhi", LocalDate.of(2019, 2, 14), 10),
                new Employee("Kiran Desai", 74000, "Bangalore", LocalDate.of(2021, 6, 17), 10),
                new Employee("Deepak Jain", 76000, "Chennai", LocalDate.of(2022, 4, 21), 11),
                new Employee("Madhavi Rao", 78000, "Mumbai", LocalDate.of(2018, 3, 19), 11),
                new Employee("Vikram Mehta", 80000, "Hyderabad", LocalDate.of(2021, 5, 22), 12),
                new Employee("Pallavi Sinha", 82000, "Bangalore", LocalDate.of(2019, 7, 13), 12),
                new Employee("Sanjay Singh", 84000, "Delhi", LocalDate.of(2020, 9, 14), 13),
                new Employee("Neelam Gupta", 86000, "Chennai", LocalDate.of(2022, 1, 5), 13),
                new Employee("Arun Kumar", 88000, "Mumbai", LocalDate.of(2021, 3, 30), 14),
                new Employee("Meena Raj", 90000, "Pune", LocalDate.of(2022, 11, 17), 14),
                new Employee("Ramesh Bhardwaj", 92000, "Kolkata", LocalDate.of(2019, 4, 23), 15),
                new Employee("Geeta Verma", 94000, "Delhi", LocalDate.of(2020, 6, 28), 15)
        );

        EmployeeDao employeeDao = new EmployeeDao();
        for (Employee employee : employees) {
            employeeDao.add(employee);
        }
    }
}
