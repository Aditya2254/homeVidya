package com.homevidya.app.homevidya.repository;

import com.homevidya.app.homevidya.entities.TutorProfile;
import com.homevidya.app.homevidya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TutorProfileRepository extends JpaRepository<TutorProfile, Long> {
    Optional<TutorProfile> findByUser(User user);
}
