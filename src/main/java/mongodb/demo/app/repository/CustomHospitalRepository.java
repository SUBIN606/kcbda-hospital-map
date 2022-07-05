package mongodb.demo.app.repository;

import mongodb.demo.app.domain.Hospital;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;


public interface CustomHospitalRepository {

    GeoResults<Hospital> findByGeoNear(Point point);

}
