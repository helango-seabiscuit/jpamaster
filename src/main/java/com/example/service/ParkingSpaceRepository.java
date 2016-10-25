package com.example.service;

import com.example.model.ParkingSpace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helangovan on 10/22/16.
 */

@Repository
public interface ParkingSpaceRepository  extends CrudRepository<ParkingSpace,Integer>{
}
