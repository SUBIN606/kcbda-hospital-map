package mongodb.demo.app.repository;

import mongodb.demo.app.domain.HospitalDocument;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;

public class HospitalMongoRepositoryImpl implements CustomHospitalMongoRepository {

    private final MongoTemplate template;

    public HospitalMongoRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public GeoResults<HospitalDocument> findByGeoNear(Point point) {
        return template.query(HospitalDocument.class)
                .near(NearQuery.near(point, Metrics.KILOMETERS)
                        .spherical(true))
                .all();
    }

}
