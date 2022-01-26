package michaelrosenfeld.mobile.mobilemini.controllers;

import michaelrosenfeld.mobile.mobilemini.domain.dto.CarDto;
import michaelrosenfeld.mobile.mobilemini.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) throws URISyntaxException {
        CarDto savedCar = carService.createCar(car);
        return ResponseEntity.created(new URI("/cars/" + savedCar.getId())).body(savedCar);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto car) {

        return ResponseEntity.ok(carService.updateCar(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
