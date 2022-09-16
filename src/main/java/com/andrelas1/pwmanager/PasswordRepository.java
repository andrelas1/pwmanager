package com.andrelas1.pwmanager;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface PasswordRepository extends CrudRepository<Password, Long>{
    List<Password> findByApplicationName(String applicationName);

    Password findById(long id);
}