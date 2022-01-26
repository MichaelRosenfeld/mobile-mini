package michaelrosenfeld.mobile.mobilemini.services;

import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.domain.dto.CarDto;
import michaelrosenfeld.mobile.mobilemini.repositories.CarRepository;
import michaelrosenfeld.mobile.mobilemini.util.CarMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getCars() {

        return carRepository.findAll().stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(RuntimeException::new);

        return CarMapper.toDto(car);
    }

    public CarDto createCar(CarDto carDto) {
        Car carToCreate = CarMapper.fromDto(carDto);
        Car createdCar = carRepository.save(carToCreate);

        return CarMapper.toDto(createdCar);
    }

    public CarDto updateCar(CarDto carDto) {
        carRepository.findById(carDto.getId()).orElseThrow(RuntimeException::new);
        Car carToUpdate = CarMapper.fromDto(carDto);
        Car updatedCar = carRepository.save(carToUpdate);

        return CarMapper.toDto(updatedCar);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
