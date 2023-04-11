package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.datasource.MySQLDataSource;
import id.co.bca.spring.helloworld.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepoImplJDBCTemplate implements IEmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void create(EmployeeModel employee) {
        jdbcTemplate.execute(MySQLDataSource.STM_CREATE, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setDate(4, new Date(System.currentTimeMillis()));
                ps.setTime(5, new Time(System.currentTimeMillis()));
                ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                return ps.execute();
            }
        });
    }

    @Override
    public List<EmployeeModel> retrieveAll() {
        return jdbcTemplate.query(MySQLDataSource.STM_RETRIEVE_ALL, new ResultSetExtractor<List<EmployeeModel>>() {
            @Override
            public List<EmployeeModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmployeeModel> employees = new ArrayList<>();
                while (rs.next()) {
                    EmployeeModel employee = new EmployeeModel();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employees.add(employee);
                }
                return employees;
            }
        });
    }

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {
        return null;
    }

    @Override
    public void update(EmployeeModel employee) {

    }

    @Override
    public void deleteUnique(EmployeeModel employee) {

    }
}
