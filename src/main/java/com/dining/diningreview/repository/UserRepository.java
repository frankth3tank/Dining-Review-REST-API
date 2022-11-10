package com.dining.diningreview.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dining.diningreview.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findUserByDisplayName(String displayName);
}