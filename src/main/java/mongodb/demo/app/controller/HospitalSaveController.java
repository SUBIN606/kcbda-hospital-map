package mongodb.demo.app.controller;

import mongodb.demo.app.application.HospitalCommandService;
import mongodb.demo.app.application.HospitalSaveRequest;
import mongodb.demo.app.domain.Hospital;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 신규 동물병원 등록 담당 컨트롤러 */
@RequestMapping("/hospitals")
@RestController
public class HospitalSaveController {

    private final HospitalCommandService service;

    public HospitalSaveController(HospitalCommandService service) {
        this.service = service;
    }

    /** 신규 동물병원 등록 */
    @PostMapping
    public Hospital saveHospital(@RequestBody HospitalSaveDto hospital) {
        return service.saveHospital(hospital);
    }

    /** 신규 동물병원 데이터를 저장 객체 */
    public static class HospitalSaveDto implements HospitalSaveRequest {
        private String name;
        private double x;
        private double y;

        public HospitalSaveDto(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

}
