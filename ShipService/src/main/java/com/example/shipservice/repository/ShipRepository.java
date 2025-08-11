package com.example.shipservice.repository;

import com.example.shipservice.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    public Ship findByNumber(Long number);
}
