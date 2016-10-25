package com.example.service;

import com.example.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by helangovan on 10/13/16.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
}
