package com.example.service;

import com.example.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helangovan on 10/22/16.
 */

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
}
