package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.EducationModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.EducationViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.response.EducationViewModel;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveEducationRequest;
import kz.mekhnin.spring.headhunter.applicationServices.EducationApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("curriculum-vitaes/{cvId}/educations")
public class EducationsController {

    @Autowired
    private EducationApplicationService educationApplicationService;

    @Autowired
    private EducationViewModelMapper educationViewModelMapper;

    @Autowired
    private EducationModelMapper educationModelMapper;

    @GetMapping
    public List<EducationViewModel> getByCvId(@PathVariable Long cvId){
        List<Education> educations = educationApplicationService.getEducationsByCurriculumVitae(cvId);

        List<EducationViewModel> viewModels = educations
                .stream()
                .map(x -> educationViewModelMapper.create(x))
                .collect(Collectors.toList());

        return viewModels;
    }

    @GetMapping("/{id}")
    public EducationViewModel getById(@PathVariable Long cvId, @PathVariable Long id){
        Education education = educationApplicationService.getEducationsById(id);

        return educationViewModelMapper.create(education);
    }

    @PostMapping
    public EducationViewModel add(@PathVariable Long cvId, @RequestBody SaveEducationRequest educationViewModel){
        Education education = new Education();
        educationModelMapper.Map(educationViewModel, education);

        Education saved = educationApplicationService.saveOrUpdate(education, cvId);

        return educationViewModelMapper.create(saved);
    }

    @PutMapping("/{id}")
    public EducationViewModel update(@PathVariable Long cvId, @PathVariable Long id, @RequestBody SaveEducationRequest educationViewModel){
        Education education = educationApplicationService.getEducationsById(id);
        educationModelMapper.Map(educationViewModel, education);

        Education saved = educationApplicationService.saveOrUpdate(education, cvId);

        return educationViewModelMapper.create(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long cvId, @PathVariable Long id){
        educationApplicationService.remove(id);
    }
}
