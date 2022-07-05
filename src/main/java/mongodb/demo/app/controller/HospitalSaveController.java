package mongodb.demo.app.controller;

import mongodb.demo.app.application.HospitalCommandService;
import mongodb.demo.app.application.HospitalSaveRequest;
import mongodb.demo.app.domain.Hospital;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public static class HospitalSaveDto implements HospitalSaveRequest {
        private String name;
        private double x;
        private double y;

        public HospitalSaveDto() {
        }

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
