package id.co.bca.spring.helloworld.datasource;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDataSource.class.getPackageName());

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_directory";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "";

    public static final String STM_CREATE = "INSERT INTO employee (first_name, last_name, email) VALUES (?,?,?)";
    public static final String STM_RETRIEVE_ALL = "SELECT * FROM employee";
    public static final String STM_RETRIEVE_BY_ID = "SELECT * FROM employee WHERE id=?";
    public static final String STM_UPDATE = "UPDATE employee SET first_name=?, last_name=?, email=? WHERE id=?";
    public static final String STM_DELETE = "DELETE FROM employee WHERE id=?";

    public MySQLDataSource() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            LOGGER.info("connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("connection unsuccessful");
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }

    public void insertEmployee(EmployeeModel employee) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.execute();
            LOGGER.info("successful insert data");
        } catch (SQLException e) {
            LOGGER.info("unsuccessful insert data");
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (Exception e) {

            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {

            }
        }
    }

    public void updateEmployee(EmployeeModel employee) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_UPDATE);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getId());
            ps.execute();
            LOGGER.info("successful update data");
        } catch (SQLException e) {
            LOGGER.info("unsuccessful update data");
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {

            }
        }
    }

    public void deleteEmployee(EmployeeModel employee) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_DELETE);
            ps.setInt(1, employee.getId());
            ps.execute();
            LOGGER.info("successful delete data");
        } catch (SQLException e) {
            LOGGER.info("unsuccessful delete data");
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {

            }
        }
    }

    public List<EmployeeModel> getAllEmployee() {
        List<EmployeeModel> employees = new ArrayList<>();
        Connection connection = null;
        Statement s = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            s = connection.createStatement();
            set = s.executeQuery(STM_RETRIEVE_ALL);
            while (set.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(set.getInt("id"));
                employee.setFirstName(set.getString("first_name"));
                employee.setLastName(set.getString("last_name"));
                employee.setEmail(set.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (Exception e) {

            }
            try {
                if (s != null)
                    s.close();
            } catch (Exception e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {

            }
        }
        return employees;
    }

    public EmployeeModel getAllEmployeeWithId(EmployeeModel emp) {
        EmployeeModel employee = new EmployeeModel();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_RETRIEVE_BY_ID);
            ps.setInt(1, emp.getId());
            set = ps.executeQuery();

            while(set.next()){
                employee.setId(set.getInt("id"));
                employee.setFirstName(set.getString("first_name"));
                employee.setLastName(set.getString("last_name"));
                employee.setEmail(set.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (Exception e) {

            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {

            }
        }
        return employee;
    }
}
