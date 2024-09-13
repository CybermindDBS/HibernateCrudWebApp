package com.cdevworks.crudapphjs.persistance.dao;

import com.cdevworks.crudapphjs.persistance.HibernateUtil;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
import com.cdevworks.crudapphjs.persistance.entity.FullTimeEmployee;
import com.cdevworks.crudapphjs.persistance.entity.PartTimeEmployee;
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
            Employee persistentEmployee;
            if (employee instanceof FullTimeEmployee) {
                persistentEmployee = session.get(FullTimeEmployee.class, employee.getId());
                ((FullTimeEmployee) persistentEmployee).setSalary(((FullTimeEmployee) employee).getSalary());
            } else {
                persistentEmployee = session.get(PartTimeEmployee.class, employee.getId());
                ((PartTimeEmployee) persistentEmployee).setHourly_wage(((PartTimeEmployee) employee).getHourly_wage());
            }

            persistentEmployee.setName(employee.getName());
            persistentEmployee.setDoj(employee.getDoj());
            persistentEmployee.setAddress(employee.getAddress());


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

    public List<Employee> getFirstNEmployeesFrom(Integer index) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("from Employee order by id", Employee.class);
        int start = (index - 1) * 10;
        query.setFirstResult(start);
        query.setMaxResults(10);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    public Integer getTotalRecords() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) from Employee", Long.class);
        Integer count = query.getSingleResult().intValue();
        return count;
    }
}
