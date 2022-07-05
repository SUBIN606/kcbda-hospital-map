package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.repository.HospitalRepository;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalQueryServiceImpl implements HospitalQueryService {

    private final HospitalRepository repository;

    public HospitalQueryServiceImpl(HospitalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Hospital> hospitals() {
        return repository.findAll();
    }

    @Override
    public GeoResults<Hospital> hospitals(Double x, Double y) {
//        return repository.findByLocationNear(new Point(x, y), new Distance(20, Metrics.KILOMETERS));
        return repository.findByGeoNear(new Point(x, y));
    }


}
