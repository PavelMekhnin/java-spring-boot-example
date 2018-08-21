package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.CurriculumVitaeViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.CurriculumVitaeViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.CurriculumVitaeService;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/curriculum-vitaes")
@RestController
public class CurriculumVitaesController {

    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Autowired
    private CurriculumVitaeViewModelMapper curriculumVitaeViewModelMapper;

    @GetMapping
    public @ResponseBody List<CurriculumVitaeViewModel> getCurriculumVitaes(){
        List<CurriculumVitae> cvs = curriculumVitaeService.getAllCurriculumVitaes();

        List<CurriculumVitaeViewModel> result = cvs
                .stream()
                .map(x -> curriculumVitaeViewModelMapper.create(x))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/{id}")
    public @ResponseBody CurriculumVitaeViewModel getCurriculumVitae(@PathVariable Long id){
        CurriculumVitae cv = curriculumVitaeService.getCurriculumVitae(id);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @PostMapping
    public @ResponseBody CurriculumVitaeViewModel addCurriculumVitae(@RequestBody CurriculumVitaeViewModel model){

        CurriculumVitae cv = curriculumVitaeService.saveCurriculumVitae(model);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @PutMapping("/{id}")
    public @ResponseBody CurriculumVitaeViewModel addCurriculumVitae(@PathVariable Long id, @RequestBody CurriculumVitaeViewModel model){

        model.setId(id);
        CurriculumVitae cv = curriculumVitaeService.saveCurriculumVitae(model);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteCurriculumVitae(@PathVariable Long id){
        curriculumVitaeService.deleteCurriculumVitae(id);
    }
}
