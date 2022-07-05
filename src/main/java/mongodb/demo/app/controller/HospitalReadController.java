package mongodb.demo.app.controller;

import mongodb.demo.app.application.HospitalQueryService;
import mongodb.demo.app.domain.Hospital;
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

@CrossOrigin
@RequestMapping("/hospitals")
@RestController
public class HospitalReadController {

    private final HospitalQueryService service;

    public HospitalReadController(HospitalQueryService service) {
        this.service = service;
    }

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

        public HospitalResponse(Hospital hospital) {
            this.id = hospital.getId();
            this.name = hospital.getName();
            this.location = hospital.getLocation();
        }

        public HospitalResponse(GeoResult<Hospital> hospitalGeoResult) {
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
