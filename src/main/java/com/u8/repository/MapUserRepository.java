package com.u8.repository;

import com.u8.entity.MapUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapUserRepository extends JpaRepository<MapUser, Long> {
    MapUser findByUsername(String username);
}
