package mongodb.demo.app.repository;

import mongodb.demo.app.domain.Hospital;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/** 병원 리포지토리 */
public interface HospitalRepository extends MongoRepository<Hospital, String>, CustomHospitalRepository {
    GeoResults<Hospital> findByLocationNear(Point location, Distance distance);
}
