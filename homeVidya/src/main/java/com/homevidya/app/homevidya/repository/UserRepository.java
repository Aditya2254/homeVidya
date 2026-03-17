package com.homevidya.app.homevidya.repository;

import com.homevidya.app.homevidya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
