package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.repository.HospitalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DisplayName("HospitalCommandService - 등록, 수정, 삭제")
@SpringBootTest
class HospitalCommandServiceTest {

    private final String HOSPITAL_NAME = "24시 동탄 이음동물의료센터";
    private final double HOSPITAL_LOC_X = 127.1019816;
    private final double HOSPITAL_LOC_Y = 37.1678562;

    private HospitalCommandService service;

    @Autowired
    private HospitalRepository repository;

    @BeforeEach
    void setup() {
        this.service = new HospitalCommandService(repository);
        cleanup();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    HospitalSaveRequest getSaveRequest(String name, double x, double y) {
        return  new HospitalSaveRequest() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }
        };
    }

    @DisplayName("병원 정보를 성공적으로 등록한다.")
    @Test
    void saveTest() {
        Hospital savedHospital
                = service.saveHospital(getSaveRequest(HOSPITAL_NAME, HOSPITAL_LOC_X, HOSPITAL_LOC_Y));

        assertThat(repository.findAll().size()).isEqualTo(1);
        assertThat(repository.findById(savedHospital.getId())).isNotEmpty();
    }

}
