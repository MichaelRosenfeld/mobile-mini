package michaelrosenfeld.mobile.mobilemini.util;

import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.domain.dto.CarDto;

public class CarMapper {

    public static CarDto toDto(Car car){

        return new CarDto(car.getId(),
                car.getMake(), car.getModel(),
                car.getConstructionYear(),
                car.getDescription(),
                car.getPrice(),
                car.getEmail());
    }

    public static Car fromDto(CarDto carDto){

        return new Car(carDto.getId(),
                carDto.getMake(), carDto.getModel(),
                carDto.getConstructionYear(),
                carDto.getDescription(),
                carDto.getPrice(),
                carDto.getEmail());
    }
}
