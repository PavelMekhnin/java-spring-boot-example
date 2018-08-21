package kz.mekhnin.spring.headhunter.applicationServices;

import kz.mekhnin.spring.headhunter.api.exception.CustomException;
import kz.mekhnin.spring.headhunter.api.security.ActionType;
import kz.mekhnin.spring.headhunter.applicationServices.EntityAuthorization.EntityAuthorizationProvider;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import kz.mekhnin.spring.headhunter.usercontext.repositories.CurriculumVitaeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumVitaeService extends BaseApplicationService {

    @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    private EntityAuthorizationProvider<CurriculumVitae> authorizationProvider;

    public List<CurriculumVitae> getAllCurriculumVitaes(){
        List<CurriculumVitae> cvs = curriculumVitaeRepository.findAllByUser_Id(getCurrentUser().getId());

        return cvs;
    }

    public CurriculumVitae getCurriculumVitae(Long id){
        CurriculumVitae cv = curriculumVitaeRepository.getOne(id);

        return cv;
    }

    public CurriculumVitae saveCurriculumVitae(CurriculumVitae cv) {
        CurriculumVitae existingCv = curriculumVitaeRepository.getOne(cv.getId());

        if (existingCv != null) {
            if(!authorizationProvider.authorize(getCurrentUser().getId(), existingCv, new ActionType[]{ActionType.Update})){
                throw new CustomException("Denied access to update Curriculum Vitae.", HttpStatus.UNAUTHORIZED);
            }
        }
        CurriculumVitae savedCv = curriculumVitaeRepository.save(cv);

        return savedCv;
    }

    public void deleteCurriculumVitae(Long id) {
        CurriculumVitae existingCv = curriculumVitaeRepository.getOne(id);

        if (existingCv != null) {
            if(!authorizationProvider.authorize(getCurrentUser().getId(), existingCv, new ActionType[]{ActionType.Delete})){
                throw new CustomException("Denied access to update Curriculum Vitae.", HttpStatus.UNAUTHORIZED);
            }
        }
        curriculumVitaeRepository.delete(existingCv);
    }
}
