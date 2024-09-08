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
            session.merge(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void delete(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Employee getEmployeeWithId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = new Employee();
        employee.setId(id);
        employee = session.get(Employee.class, id, LockMode.UPGRADE_NOWAIT);
        session.close();
        return employee;
    }

    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }
}
