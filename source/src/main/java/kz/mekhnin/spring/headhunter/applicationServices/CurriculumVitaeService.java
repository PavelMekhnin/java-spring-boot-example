package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.CurriculumVitaeAuthorizationHandler;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.data.entities.User;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CurriculumVitaeRepository;
import kz.mekhnin.spring.headhunter.usercontext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumVitaeService extends BaseApplicationService {

    @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    private CurriculumVitaeAuthorizationHandler authorizationHandler;

    public List<CurriculumVitae> getAllCurriculumVitaes(){
        List<CurriculumVitae> cvs = curriculumVitaeRepository.findAll();

        return cvs;
    }

    public List<CurriculumVitae> getAllMyCurriculumVitaes(){
        List<CurriculumVitae> cvs = curriculumVitaeRepository.findAllByUser_Id(getCurrentUser().getId());

        return cvs;
    }

    public CurriculumVitae getCurriculumVitae(Long id){
        Optional<CurriculumVitae> cv = curriculumVitaeRepository.findById(id);

        if (!cv.isPresent()) {
            throw new CustomException("CV not found.", HttpStatus.NOT_FOUND);
        }

        if(!authorizationHandler.authorize(getCurrentUser().getId(), cv.get(), new ActionType[]{ActionType.Read})){
            throw new CustomException("Denied access to update Curriculum Vitae.", HttpStatus.UNAUTHORIZED);
        }

        return cv.get();
    }

    public CurriculumVitae saveCurriculumVitae(CurriculumVitae cv) {
        if(cv.getId() != null) {
            Optional<CurriculumVitae> existingCv = curriculumVitaeRepository.findById(cv.getId());

            if (existingCv.isPresent()) {
                if (!authorizationHandler.authorize(getCurrentUser().getId(), existingCv.get(), new ActionType[]{ActionType.Update})) {
                    throw new CustomException("Denied access to update Curriculum Vitae.", HttpStatus.UNAUTHORIZED);
                }
            }
        }else {
            Optional<User> user = userRepository.findById(getCurrentUser().getId());
            if (!user.isPresent()){
                throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
            }
            cv.setUser(user.get());
        }
        CurriculumVitae savedCv = curriculumVitaeRepository.save(cv);

        return getCurriculumVitae(savedCv.getId());
    }

    public void deleteCurriculumVitae(Long id) {
        Optional<CurriculumVitae> existingCv = curriculumVitaeRepository.findById(id);

        if (!existingCv.isPresent()) {
            throw new CustomException("CV not found.", HttpStatus.NOT_FOUND);
        }

        if(!authorizationHandler.authorize(getCurrentUser().getId(), existingCv.get(), new ActionType[]{ActionType.Delete})){
            throw new CustomException("Denied access to update Curriculum Vitae.", HttpStatus.UNAUTHORIZED);
        }

        curriculumVitaeRepository.delete(existingCv.get());
    }
}
