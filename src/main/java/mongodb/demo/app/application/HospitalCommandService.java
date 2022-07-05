package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.repository.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class HospitalCommandService implements HospitalSaveService {

    private HospitalRepository repository;

    public HospitalCommandService(HospitalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Hospital saveHospital(HospitalSaveRequest hospital) {
        return repository.save(hospital.toHospital());
    }

}
