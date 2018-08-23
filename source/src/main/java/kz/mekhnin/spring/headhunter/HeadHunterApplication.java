package kz.mekhnin.spring.headhunter;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CityRepository;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HeadHunterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeadHunterApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

@Component
class DataLoader implements ApplicationRunner {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // just temporary for test
        Country country1 = new Country();
        country1.setName("Kazakhstan");
        Country country2 = new Country();
        country2.setName("Germany");

        country1 = countryRepository.save(country1);
        country2 = countryRepository.save(country2);

        City city1 = new City();
        city1.setName("Almaty");
        city1.setCountry(country1);

        City city2 = new City();
        city2.setName("Oskemen");
        city2.setCountry(country1);

        City city3 = new City();
        city3.setName("Berlin");
        city3.setCountry(country2);

        City city4 = new City();
        city4.setName("Munich");
        city4.setCountry(country2);

        cityRepository.save(city1);
        cityRepository.save(city2);
        cityRepository.save(city3);
        cityRepository.save(city4);
    }
}
