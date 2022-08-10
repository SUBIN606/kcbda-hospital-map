package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.repository.HospitalMongoRepository;
import mongodb.demo.app.repository.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class HospitalCommandService implements HospitalSaveService {

    private final HospitalMongoRepository mongoRepository;
    private final HospitalRepository repository;

    public HospitalCommandService(HospitalMongoRepository mongoRepository,
                                  HospitalRepository repository) {
        this.mongoRepository = mongoRepository;
        this.repository = repository;
    }

    @Override
    public Hospital saveHospital(HospitalSaveRequest hospital) {
        mongoRepository.save(hospital.toHospitalDocument());
        return repository.save(hospital.toHospital());
    }

}
