package mongodb.demo.app.repository;

import mongodb.demo.app.domain.HospitalDocument;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;


public interface CustomHospitalMongoRepository {

    GeoResults<HospitalDocument> findByGeoNear(Point point);

}
