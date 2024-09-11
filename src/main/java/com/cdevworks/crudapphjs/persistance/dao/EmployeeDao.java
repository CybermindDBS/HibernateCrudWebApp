package com.cdevworks.crudapphjs.persistance.dao;

import com.cdevworks.crudapphjs.persistance.HibernateUtil;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    public void add(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("TRANSIENT >> " + employee);
            Employee persistentEmployee = session.get(Employee.class, employee.getId());
            System.out.println("PERSISTENT >> " + persistentEmployee);
            persistentEmployee.setName(employee.getName());
            persistentEmployee.setSalary(employee.getSalary());
            persistentEmployee.setAddress(employee.getAddress());
            persistentEmployee.setDoj(employee.getDoj());
            persistentEmployee.setSupervisor_id(employee.getSupervisor_id());
            session.merge(persistentEmployee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void deleteEmployeeWithId(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(getEmployeeWithId(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public Employee getEmployeeWithId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = new Employee();
        employee.setId(id);
        employee = session.get(Employee.class, id, LockMode.UPGRADE_NOWAIT); // Pessimistic Lock.
        session.close();
        return employee;
    }

    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }
}
