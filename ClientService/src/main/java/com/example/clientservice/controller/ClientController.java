package com.example.clientservice.controller;

import com.example.clientservice.ShipDto;
import com.example.clientservice.ShipRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientApi")
public class ClientController {

    @Autowired
    private ShipRestClientService shipRestClientService;

    @PostMapping
    public ResponseEntity<ShipDto> create(@RequestBody ShipDto shipDto) {
        return ResponseEntity.ok(shipRestClientService.createShip(shipDto));
    }
    @GetMapping("/getShip/{shipNumber}")
    public ResponseEntity<ShipDto> getShip(@PathVariable Long shipNumber) {
        return ResponseEntity.ok(shipRestClientService.getShip(shipNumber));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShipDto>> getAllShips() {
        return ResponseEntity.ok(shipRestClientService.getAllShips());
    }

    @PatchMapping()
    public ResponseEntity<ShipDto> update(@RequestBody ShipDto shipDto) {
        return ResponseEntity.ok(shipRestClientService.updateShip(shipDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shipRestClientService.deleteShip(id);
        return ResponseEntity.ok().build();
    }
}
