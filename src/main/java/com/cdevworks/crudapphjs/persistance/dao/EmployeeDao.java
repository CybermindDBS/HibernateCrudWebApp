package com.cdevworks.crudapphjs.persistance.dao;

import com.cdevworks.crudapphjs.persistance.HibernateUtil;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
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

    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }
}
