package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.CityModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.CityViewModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.CountryViewModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.SkillTagViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CityViewModel;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CountryViewModel;
import kz.mekhnin.spring.headhunter.api.viewModels.response.SkillTagVewModel;
import kz.mekhnin.spring.headhunter.applicationServices.ReferencesApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.SkillTag;
import kz.mekhnin.spring.headhunter.data.entities.addressing.City;
import kz.mekhnin.spring.headhunter.data.entities.addressing.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/references")
@RestController
public class ReferencesController {

    @Autowired
    private ReferencesApplicationService referencesApplicationService;

    @Autowired
    private CityViewModelMapper cityViewModelMapper;

    @Autowired
    private CityModelMapper cityModelMapper;

    @Autowired
    private CountryViewModelMapper countryViewModelMapper;

    @Autowired
    private SkillTagViewModelMapper skillTagViewModelMapper;

    @GetMapping("/countries/{id}/cities")
    public @ResponseBody List<CityViewModel> getCitiesByCountry(@PathVariable Long id){
        List<City> model = referencesApplicationService.getCitiesByCountryId(id);

        return model.parallelStream().map(x -> cityViewModelMapper.create(x)).collect(Collectors.toList());
    }

    @GetMapping("/countries")
    public @ResponseBody List<CountryViewModel> getCountries(){
        List<Country> model = referencesApplicationService.getCountries();

        return model.parallelStream().map(x -> countryViewModelMapper.create(x)).collect(Collectors.toList());
    }

    @GetMapping("/tags")
    public @ResponseBody List<SkillTagVewModel> getTagByName(@RequestParam String tagName){
        List<SkillTag> model = referencesApplicationService.getSkillTagsLikeTitle(tagName);

        return model.parallelStream().map(x -> skillTagViewModelMapper.create(x)).collect(Collectors.toList());
    }
}
