package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.AwardAuthorizationHandler;
import kz.mekhnin.spring.headhunter.data.entities.Award;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardApplicationService extends BaseApplicationService {

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private CurriculumVitaeService  curriculumVitaeService;

    @Autowired
    private AwardAuthorizationHandler awardAuthorizationHandler;


    public List<Award> getAwardsByCvId(Long id){
        List<Award> awards = awardRepository.findAllByCurriculumVitae_Id(id);

        return awards;
    }

    public Award getAwardsById(Long id){
        Optional<Award> award = awardRepository.findById(id);
        if(!award.isPresent()){
            throw new CustomException("Award not found.", HttpStatus.NOT_FOUND);
        }

        if (!awardAuthorizationHandler.authorize(getCurrentUser().getId(), award.get(), new ActionType[]{ActionType.Read})) {
            throw new CustomException("Denied access to update award.", HttpStatus.UNAUTHORIZED);
        }

        return award.get();
    }

    public Award saveOrUpdate(Award award, Long cvId){
        if(award.getId() != null) {
            Optional<Award> existingAward = awardRepository.findById(award.getId());

            if (existingAward.isPresent()) {
                if (!awardAuthorizationHandler.authorize(getCurrentUser().getId(), existingAward.get(), new ActionType[]{ActionType.Update})) {
                    throw new CustomException("Denied access to update award.", HttpStatus.UNAUTHORIZED);
                }
            }
        }else {
            Optional<User> user = userRepository.findById(getCurrentUser().getId());
            if (!user.isPresent()){
                throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
            }
            award.setUser(user.get());
        }
        CurriculumVitae cv = curriculumVitaeService.getCurriculumVitae(cvId);

        cv.addAward(award);

        return awardRepository.save(award);
    }

    public void delete(Long id){
        Optional<Award> existingAward = awardRepository.findById(id);

        if (existingAward.isPresent()) {
            if (!awardAuthorizationHandler.authorize(getCurrentUser().getId(), existingAward.get(), new ActionType[]{ActionType.Delete})) {
                throw new CustomException("Denied access to delete award.", HttpStatus.UNAUTHORIZED);
            }
        }

        awardRepository.deleteById(id);
    }
}
