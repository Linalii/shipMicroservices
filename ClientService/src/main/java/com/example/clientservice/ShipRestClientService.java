package com.example.clientservice;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ShipRestClientService {


    private RestClient restClient;

    public ShipDto createShip(ShipDto shipDto) {
        return restClient.post()
                .uri("/addShip")
                .body(shipDto)
                .retrieve()
                .toEntity(ShipDto.class)
                .getBody();
    }

    public void deleteShip(Long id) {
        restClient.delete()
                .uri("/deleteShip/{id}", id)
                .retrieve()
                .toBodilessEntity();  //для войда если мы возвращаем void

    }

    public List<ShipDto> getAllShips() {
        ResponseEntity<ShipDto[]> entity = restClient.get()
                .uri("/getAllShips")
                .retrieve()
                .toEntity(ShipDto[].class);

            return Arrays.asList(entity.getBody());
    }
    //update
    public ShipDto getShip (Long shipNumber){
        return restClient.get()
                .uri("/getShip/{shipNumber}", shipNumber)
                .retrieve()
                .toEntity(ShipDto.class)
                .getBody();
    }

    public ShipDto updateShip(ShipDto shipDto) {
        return restClient.patch()
                .uri("/updateShip")
                .body(shipDto)
                .retrieve()
                .toEntity(ShipDto.class)
                .getBody();
    }
}
