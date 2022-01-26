package michaelrosenfeld.mobile.mobilemini.controllers;

import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.repositories.CarRepository;
import org.hibernate.annotations.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return ResponseEntity.ok(carRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) throws URISyntaxException {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.created(new URI("/cars/" + savedCar.getId())).body(savedCar);
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        carRepository.findById(car.getId()).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
