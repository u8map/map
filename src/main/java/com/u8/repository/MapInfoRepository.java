package com.u8.repository;

import com.u8.entity.MapInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapInfoRepository extends JpaRepository<MapInfo, Long> {
}
