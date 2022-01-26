package michaelrosenfeld.mobile.mobilemini.controllers;

import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.domain.dto.CarDto;
import michaelrosenfeld.mobile.mobilemini.repositories.CarRepository;
import michaelrosenfeld.mobile.mobilemini.util.CarMapper;
import org.hibernate.annotations.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carRepository.findAll().stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(CarMapper.toDto(carRepository.findById(id).orElseThrow(RuntimeException::new)));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) throws URISyntaxException {
        CarDto savedCar = CarMapper.toDto(carRepository.save(CarMapper.fromDto(car)));
        return ResponseEntity.created(new URI("/cars/" + savedCar.getId())).body(savedCar);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto car) {
        carRepository.findById(car.getId()).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(CarMapper.toDto(carRepository.save(CarMapper.fromDto(car))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDto> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
