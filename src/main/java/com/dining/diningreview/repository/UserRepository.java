package com.dining.diningreview.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dining.diningreview.model.Users;

public interface UserRepository extends CrudRepository<Users, Long>{
    Optional<Users> findByDisplayName(String displayName);
}