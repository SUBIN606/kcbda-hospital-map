package mongodb.demo.app.repository;

import mongodb.demo.app.domain.HospitalDocument;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/** 병원 리포지토리 */
public interface HospitalMongoRepository extends MongoRepository<HospitalDocument, String>, CustomHospitalMongoRepository {
    HospitalDocument findByName(String name);
    GeoResults<HospitalDocument> findByLocationNear(Point location, Distance distance);
}
