package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.ExperienceAuthorizationHandler;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import kz.mekhnin.spring.headhunter.data.entities.Experience;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceApplicationService extends BaseApplicationService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Autowired
    private ExperienceAuthorizationHandler experienceAuthorizationHandler;

    public List<Experience> getByCvId(Long id){
        List<Experience> experiences = experienceRepository.findAllByCurriculumVitae_Id(id);

        return experiences;
    }

    public Experience getById(Long id){
        Optional<Experience> experience = experienceRepository.findById(id);
        if(!experience.isPresent()){
            throw new CustomException("Experience not found.", HttpStatus.NOT_FOUND);
        }
        if(!experienceAuthorizationHandler.authorize(getCurrentUser().getId(), experience.get(), new ActionType[]{
                ActionType.Read
        })){
            throw new CustomException("Denied access to read experience.", HttpStatus.UNAUTHORIZED);
        }

        return experience.get();
    }

    public Experience saveOrUpdate(Long cvId, Experience experience){
        if(experience.getId() != null) {
            Optional<Experience> existingExperience = experienceRepository.findById(experience.getId());

            if (existingExperience.isPresent()) {
                if (!experienceAuthorizationHandler.authorize(getCurrentUser().getId(), existingExperience.get(), new ActionType[]{ActionType.Update})) {
                    throw new CustomException("Denied access to update experience.", HttpStatus.UNAUTHORIZED);
                }
            }
        }else {
            Optional<User> user = userRepository.findById(getCurrentUser().getId());
            if (!user.isPresent()){
                throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
            }
            experience.setUser(user.get());
        }
        CurriculumVitae cv = curriculumVitaeService.getCurriculumVitae(cvId);

        cv.addExperience(experience);

        Experience saved = experienceRepository.save(experience);

        return getById(saved.getId());
    }

    public void delete(Long id){
        Optional<Experience> existingExperience = experienceRepository.findById(id);

        if (existingExperience.isPresent()) {
            if (!experienceAuthorizationHandler.authorize(getCurrentUser().getId(), existingExperience.get(), new ActionType[]{ActionType.Delete})) {
                throw new CustomException("Denied access to delete experience.", HttpStatus.UNAUTHORIZED);
            }
        }
        experienceRepository.deleteById(id);
    }
}
