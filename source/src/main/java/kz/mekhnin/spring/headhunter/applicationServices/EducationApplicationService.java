package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.EducationAuthorizationHandler;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.Education;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationApplicationService extends BaseApplicationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private CurriculumVitaeService curriculumVitaeService;

    @Autowired
    private EducationAuthorizationHandler educationAuthorizationHandler;

    public List<Education> getEducationsByCurriculumVitae(Long id){
        List<Education> educations = educationRepository.findAllByCurriculumVitae_Id(id);

        return educations;
    }

    public Education getEducationsById(Long id){
        Optional<Education> educations = educationRepository.findById(id);
        if(!educations.isPresent()){
            throw new CustomException("Education not found.", HttpStatus.NOT_FOUND);
        }

        if(!educationAuthorizationHandler.authorize(getCurrentUser().getId(), educations.get(), new ActionType[]{
            ActionType.Read
        })){
            throw new CustomException("Denied access to read education.", HttpStatus.UNAUTHORIZED);
        }

        return educations.get();
    }

    public Education saveOrUpdate(Education education, Long cvId){
        if(education.getId() != null) {
            Optional<Education> existingEducation = educationRepository.findById(education.getId());

            if (existingEducation.isPresent()) {
                if (!educationAuthorizationHandler.authorize(getCurrentUser().getId(), existingEducation.get(), new ActionType[]{ActionType.Update})) {
                    throw new CustomException("Denied access to update education.", HttpStatus.UNAUTHORIZED);
                }
            }
        }else {
            Optional<User> user = userRepository.findById(getCurrentUser().getId());
            if (!user.isPresent()){
                throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
            }
            education.setUser(user.get());
        }
        CurriculumVitae cv = curriculumVitaeService.getCurriculumVitae(cvId);

        cv.addEducation(education);

        Education saved = educationRepository.save(education);

        return getEducationsById(saved.getId());
    }

    public void remove(Long id){
        Optional<Education> existingEducation = educationRepository.findById(id);

        if (existingEducation.isPresent()) {
            if (!educationAuthorizationHandler.authorize(getCurrentUser().getId(), existingEducation.get(), new ActionType[]{ActionType.Delete})) {
                throw new CustomException("Denied access to delete education.", HttpStatus.UNAUTHORIZED);
            }
        }
        educationRepository.deleteById(id);
    }
}
