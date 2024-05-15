package com.ra.btspring_thymleaf.Dao.impl;

import com.ra.btspring_thymleaf.Dao.IEmployeeDao;
import com.ra.btspring_thymleaf.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployees() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from Employee ").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
        return null;

    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getEmployeeById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            if (name == null || name.isEmpty())
                name = "%";
            else
                name = "%" + name + "%";
            List list = session.createQuery("from Employee where name like :name")
                    .setParameter("name", name)
                    .list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
