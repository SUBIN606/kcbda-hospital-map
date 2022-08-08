package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.domain.HospitalDocument;
import org.springframework.data.geo.GeoResults;

import java.util.List;

/** 동물병원 조회 담당 */
public interface HospitalQueryService {

    /** 저장된 모든 동물병원을 반환합니다. */
    List<Hospital> hospitals();

    /**
     * 주어진 좌표에서 가까운 순서대로 반환합니다.
     * @param x x좌표
     * @param y y좌표
     * @return 주어진 좌표에서 가까운 순서대로 정렬된 동물병원 목록
     */
    GeoResults<HospitalDocument> hospitals(Double x, Double y);

}
