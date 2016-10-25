package com.example.service;

import com.example.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helangovan on 10/23/16.
 */

@Repository
public interface ProjectRepository  extends CrudRepository<Project,Integer>{
}
