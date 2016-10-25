package com.example.service;

import com.example.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helangovan on 10/11/16.
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
