package michaelrosenfeld.mobile.mobilemini.controllers;

import lombok.AllArgsConstructor;
import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.repositories.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createCar(@RequestBody Car car) throws URISyntaxException {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.created(new URI("/cars/" + savedCar.getId())).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody Car car) {
        Car currentCar = carRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCar.setMake(car.getMake());
        currentCar.setModel(car.getModel());
        currentCar.setConstructionYear(car.getConstructionYear());
        currentCar.setDescription(car.getDescription());
        currentCar.setPrice(car.getPrice());
        currentCar.setEmail(car.getEmail());
        currentCar = carRepository.save(car);

        return ResponseEntity.ok(currentCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
