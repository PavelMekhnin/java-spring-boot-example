package kz.mekhnin.spring.headhunter.usercontext.repositories;

import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByCountry_Id(Long countryId);
}
