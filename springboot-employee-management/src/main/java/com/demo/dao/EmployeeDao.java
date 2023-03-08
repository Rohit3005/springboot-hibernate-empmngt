package com.demo.dao;

import com.demo.model.Employee;
import jdk.dynalink.linker.LinkerServices;

import java.text.ParseException;
import java.util.List;

public interface EmployeeDao {

    public void saveData(Employee employee);

    public List<Employee> getAllData();

    public Employee getDataById(int empId);

    public void updateData(int empId, Employee employee);

    public List<Employee> getDataByDob(String empDOB) throws ParseException;

    public void deleteDataById(int empId);
}
