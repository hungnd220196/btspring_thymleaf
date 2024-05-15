package com.ra.btspring_thymleaf.Dao;

import com.ra.btspring_thymleaf.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    public List<Employee> getEmployees();

    public Employee getEmployeeById(Integer id);

    public boolean insertEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public boolean deleteEmployee(Integer id);

    public List<Employee> getEmployeeByName(String name);
}
