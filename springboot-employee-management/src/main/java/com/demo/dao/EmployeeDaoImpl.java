package com.demo.dao;

import com.demo.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void saveData(Employee employee) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee);
        transaction.commit();
    }

    @Override
    public List<Employee> getAllData() {
        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public Employee getDataById(int empId) {
        Session session = factory.openSession();
        return (Employee) session.get(Employee.class, empId);
    }

    @Override
    public void updateData(int empId, Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1 = getDataById(empId);
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());

        session.update(employee1);
        transaction.commit();

    }

    @Override
    public List<Employee> getDataByDob(String empDOB) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date empDobDate = dateFormat.parse(empDOB);

        for (Employee employee : getAllData()) {
            if (employee.getEmpDOB().equals(empDobDate)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public void deleteDataById(int empId) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = getDataById(empId);
        session.delete(employee);
        transaction.commit();

    }
}
