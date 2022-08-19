package mongodb.demo.app.controller;

import mongodb.demo.app.application.HospitalQueryService;
import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.domain.HospitalDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/** 동물병원 조회 담당 컨트롤러 */
@CrossOrigin
@RequestMapping("/hospitals")
@RestController
public class HospitalReadController {
    
    private final HospitalQueryService service;

    public HospitalReadController(HospitalQueryService service) {
        this.service = service;
    }

    /**
     * 동물병원 목록을 조회 후 반환합니다.
     * 좌표 값이 주어지면 거리에 상관 없이 모든 동물병원을 반환하고,
     * 좌표 값이 주어지면 거리를 계산해 가까운 순서대로 반환합니다.
     * @param x x좌표 (필수 x)
     * @param y y좌표 (필수 x)
     * @return 동물병원 목록
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<HospitalResponse> hospitals(@RequestParam(name = "x", required = false) Double x,
                                            @RequestParam(name = "y", required = false) Double y) {

        if (x == null || y == null) {
            return service.hospitals()
                    .stream()
                    .map(HospitalResponse::new)
                    .collect(Collectors.toList());
        }
        return service.hospitals(x, y)
                .getContent()
                .stream()
                .map(HospitalResponse::new)
                .collect(Collectors.toList());
    }

    /** 동물병원 응답 객체 */
    public static class HospitalResponse {
        private String id;
        private String name;
        private GeoJsonPoint location;
        private Distance distance;

        public HospitalResponse() {
        }

        public HospitalResponse(Hospital hospital) {
            this.id = String.valueOf(hospital.getId());
            this.name = hospital.getName();
            this.location = new GeoJsonPoint(hospital.getLocation().getX(), hospital.getLocation().getY());
        }

        public HospitalResponse(GeoResult<HospitalDocument> hospitalGeoResult) {
            this.id = hospitalGeoResult.getContent().getId();
            this.name = hospitalGeoResult.getContent().getName();
            this.location = hospitalGeoResult.getContent().getLocation();
            this.distance = hospitalGeoResult.getDistance();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public GeoJsonPoint getLocation() {
            return location;
        }

        public Distance getDistance() {
            return distance;
        }
    }

}
