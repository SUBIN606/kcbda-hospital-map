package mongodb.demo.app.repository;

import mongodb.demo.app.domain.Hospital;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;

public class HospitalRepositoryImpl implements CustomHospitalRepository {

    private final MongoTemplate template;

    public HospitalRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public GeoResults<Hospital> findByGeoNear(Point point) {
        return template.query(Hospital.class)
                .near(NearQuery.near(point, Metrics.KILOMETERS)
                        .spherical(true))
                .all();
    }

}
