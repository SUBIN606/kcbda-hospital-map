package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.domain.HospitalDocument;
import mongodb.demo.app.repository.HospitalMongoRepository;
import mongodb.demo.app.repository.HospitalRepository;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalQueryServiceImpl implements HospitalQueryService {

    private final HospitalMongoRepository mongoRepository;
    private final HospitalRepository repository;

    public HospitalQueryServiceImpl(HospitalMongoRepository mongoRepository, HospitalRepository repository) {
        this.mongoRepository = mongoRepository;
        this.repository = repository;
    }

    @Override
    public List<Hospital> hospitals() {
        return repository.findAll();
    }

    @Override
    public GeoResults<HospitalDocument> hospitals(Double x, Double y) {
//        return repository.findByLocationNear(new Point(x, y), new Distance(20, Metrics.KILOMETERS));
        return mongoRepository.findByGeoNear(new Point(x, y));
    }


}
