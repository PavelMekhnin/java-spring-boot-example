package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.AwardModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.AwardViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveAwardRequest;
import kz.mekhnin.spring.headhunter.api.viewModels.response.AwardViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.AwardApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curriculum-vitaes/{cvId}/awards")
public class AwardsController {

    @Autowired
    private AwardApplicationService awardApplicationService;

    @Autowired
    private AwardViewModelMapper awardViewModelMapper;

    @Autowired
    private AwardModelMapper awardModelMapper;

    @GetMapping
    public @ResponseBody List<AwardViewModel> getByCvId(@PathVariable Long cvId){
        List<Award> awards = awardApplicationService.getAwardsByCvId(cvId);

        return awards
                .stream()
                .map(x -> awardViewModelMapper.create(x))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public @ResponseBody AwardViewModel getById(@PathVariable Long cvId, @PathVariable Long id){
        Award award = awardApplicationService.getAwardsById(id);

        return awardViewModelMapper.create(award);
    }

    @PostMapping
    public @ResponseBody AwardViewModel add(@PathVariable Long cvId, @RequestBody SaveAwardRequest model){
        Award award = new Award();

        awardModelMapper.Map(model, award);

        Award saved = awardApplicationService.saveOrUpdate(award, cvId);

        return awardViewModelMapper.create(saved);
    }

    @PutMapping("/{id}")
    public @ResponseBody AwardViewModel update(@PathVariable Long cvId, @PathVariable Long id, @RequestBody SaveAwardRequest model){
        Award award = awardApplicationService.getAwardsById(id);

        awardModelMapper.Map(model, award);

        Award saved = awardApplicationService.saveOrUpdate(award, cvId);

        return awardViewModelMapper.create(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long cvId, @PathVariable Long id){
        awardApplicationService.delete(id);
    }
}
