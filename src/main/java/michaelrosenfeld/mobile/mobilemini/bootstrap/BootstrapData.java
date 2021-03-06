package michaelrosenfeld.mobile.mobilemini.bootstrap;

import michaelrosenfeld.mobile.mobilemini.domain.Car;
import michaelrosenfeld.mobile.mobilemini.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CarRepository carRepository;

    public BootstrapData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Injecting sample cars...");

        Car firstCar = new Car(
                "Mercedes",
                "C220",
                "2020",
                "Cool Car, much fast",
                43000,
                "this@mail.com");

        Car secondcar = new Car(
                "BMW",
                "M8",
                "2016",
                "Cool Car, too much fast",
                230500,
                "that@mail.com");

        Car thirdCar = new Car(
                "Hyundai",
                "No idea",
                "2005",
                "Not so Cool Car, not so much fast",
                9000,
                "another@mail.com");

        Car fourthCar = new Car(
                "McLaren",
                "MP4-12C",
                "2016",
                "Cool Car,also very much fast",
                243000,
                "aaandanotherone@mail.com");

        carRepository.save(firstCar);
        carRepository.save(secondcar);
        carRepository.save(thirdCar);
        carRepository.save(fourthCar);
    }
}
