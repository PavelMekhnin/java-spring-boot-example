package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.CurriculumVitaeModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.CurriculumVitaeViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CurriculumVitaeViewModel;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveCurriculumVitaeRequest;
import kz.mekhnin.spring.headhunter.api.viewModels.response.EducationViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.CurriculumVitaeService;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestMapping("/curriculum-vitaes")
@RestController
public class CurriculumVitaesController {

    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Autowired
    private CurriculumVitaeModelMapper curriculumVitaeModelMapper;

    @Autowired
    private CurriculumVitaeViewModelMapper curriculumVitaeViewModelMapper;

    @GetMapping
    public @ResponseBody List<CurriculumVitaeViewModel> getAll(){
        List<CurriculumVitae> cvs = curriculumVitaeService.getAllCurriculumVitaes();

        List<CurriculumVitaeViewModel> result = cvs
                .stream()
                .map(x -> curriculumVitaeViewModelMapper.create(x))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/my")
    public @ResponseBody List<CurriculumVitaeViewModel> getAllMy(){
        List<CurriculumVitae> cvs = curriculumVitaeService.getAllMyCurriculumVitaes();

        List<CurriculumVitaeViewModel> result = cvs
                .stream()
                .map(x -> curriculumVitaeViewModelMapper.create(x))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/{id}")
    public @ResponseBody CurriculumVitaeViewModel getById(@PathVariable Long id){
        CurriculumVitae cv = curriculumVitaeService.getCurriculumVitae(id);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @PostMapping
    public @ResponseBody CurriculumVitaeViewModel add(@RequestBody SaveCurriculumVitaeRequest model){
        CurriculumVitae newCv = new CurriculumVitae();

        curriculumVitaeModelMapper.Map(model, newCv);

        CurriculumVitae cv = curriculumVitaeService.saveCurriculumVitae(newCv);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @PutMapping("/{id}")
    public @ResponseBody CurriculumVitaeViewModel update(@PathVariable Long id, @RequestBody SaveCurriculumVitaeRequest model){
        CurriculumVitae existCv = curriculumVitaeService.getCurriculumVitae(id);
        if(existCv == null){
            throw new CustomException("CV does not found.", HttpStatus.NOT_FOUND);
        }
        model.setId(id);

        curriculumVitaeModelMapper.Map(model, existCv);
        CurriculumVitae cv = curriculumVitaeService.saveCurriculumVitae(existCv);

        CurriculumVitaeViewModel result = curriculumVitaeViewModelMapper.create(cv);

        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        curriculumVitaeService.deleteCurriculumVitae(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        Logger.getLogger("http").warning(e.getMessage());
    }
}
