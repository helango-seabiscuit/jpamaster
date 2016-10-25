package com.example.service;

import com.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by helangovan on 9/27/16.
 */

public class EmployeeServiceImpl  implements  EmployeeService{

    @PersistenceContext(unitName = "EmployeeService")
    protected EntityManager em;

    @Autowired
    protected EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EntityManager em){
        this.em = em;
    }

    public EmployeeServiceImpl(){
        this.em = em;
    }

    public Employee createEmployee(int id,String email,String fname,String lname){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setEmail(email);
        emp.setFirstName(fname);
        emp.setLastName(lname);
        em.persist(emp);
        return emp;
    }

    public Employee findEmployee(int id){
        return employeeRepository.findOne(id);
        //return em.find(Employee.class,id);
    }

    public List<Employee> findAllEmployees(){
      TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e",Employee.class);
        return query.getResultList();
    }

    public void removeEmployee(int id){
        Employee emp = findEmployee(id);
        if(emp!=null){
            em.remove(emp);
        }
    }

    public Employee updateEmployeeEmail(int id,String email){
        Employee emp = findEmployee(id);
        if(emp!=null){
            emp.setEmail(email);
        }
        return emp;
    }

}
