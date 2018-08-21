package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.data.entities.SkillTag;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CityRepository;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CountryRepository;
import kz.mekhnin.spring.headhunter.usercontext.repositories.SkillTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferencesApplicationService extends BaseApplicationService{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private SkillTagRepository skillTagRepository;

    public List<City> getCitiesByCountryId(Long countryId){
        List<City> cities = cityRepository.findAllByCountry_Id(countryId);

        return cities;
    }

    public List<Country> getCountries(){
        List<Country> countries = countryRepository.findAll();

        return countries;
    }

    public List<SkillTag> getSkillTagsLikeTitle(String title){
        List<SkillTag> skillTags = skillTagRepository.findAllByTitleLike(title);

        return skillTags;
    }

    public SkillTag addSkillTag(SkillTag skillTag){
        SkillTag existing = skillTagRepository.findByTitle(skillTag.getTitle());

        if(existing != null){
            return existing;
        }

        SkillTag newTag = skillTagRepository.save(skillTag);

        return newTag;
    }
}
