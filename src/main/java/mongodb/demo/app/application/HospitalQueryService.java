package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import org.springframework.data.geo.GeoResults;

import java.util.List;

/** 병원 조회 담당 */
public interface HospitalQueryService {

    /** 저장된 모든 병원을 반환합니다. */
    List<Hospital> hospitals();

    GeoResults<Hospital> hospitals(Double x, Double y);
}
