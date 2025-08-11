package com.example.shipservice.controller;


import com.example.shipservice.entity.Ship;
import com.example.shipservice.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
@RequiredArgsConstructor
public class ShipController {
    private final ShipRepository shipRepository;

    @Value("${api-key}")
    private  String key;

    public boolean validApiKey(String apiKey) {
        return key.equals(apiKey);
    }

    @PostMapping("/addShip")
    public ResponseEntity<Ship> addShip(@RequestBody Ship ship, @RequestHeader("X-API-KEY") String apiKey) {
        if (!validApiKey(apiKey)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.status(201).body(shipRepository.save(ship));
    }

    @GetMapping("/getShip/{shipNumber}")
    public ResponseEntity<Ship> getShip(@PathVariable Long shipNumber){
        Ship ship = shipRepository.findByNumber(shipNumber);
        return ship != null ? ResponseEntity.ok(ship) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getAllShips")
    public ResponseEntity<List<Ship>> getAllShips(){
        List<Ship> allShips = shipRepository.findAll();
        return allShips != null ? ResponseEntity.ok(allShips) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/updateShip")
    public ResponseEntity<Ship> updateShip(@RequestBody Ship ship){

        Ship shipToUdate = shipRepository.findByNumber(ship.getNumber());
        if (ship.getNumber()!= null){
            shipToUdate.setNumber(ship.getNumber());
        }
        if (ship.getName()!= null){
            shipToUdate.setName(ship.getName());
        }
        if (ship.getOwner()!= null){
            shipToUdate.setOwner(ship.getOwner());
        }
        return ResponseEntity.ok(shipRepository.save(shipToUdate));
    }
    @DeleteMapping("/deleteShip/{shipId}")
    public ResponseEntity<Void> deleteShip(@PathVariable Long shipId){
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null){
            shipRepository.delete(ship);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
