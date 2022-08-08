package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.repository.HospitalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DisplayName("HospitalQueryService - 조회")
@SpringBootTest
class HospitalQueryServiceImplTest {

    private HospitalQueryService service;
    @Autowired
    private HospitalRepository repository;

    @BeforeEach
    void setup() {
        this.service = new HospitalQueryServiceImpl(repository);
    }

    @DisplayName("hospitals 메서드는")
    @Nested
    class Describe_hospitals {

        @DisplayName("좌표가 주어지지 않으면")
        @Nested
        class Context_without_point {
            @BeforeEach
            void setUp() {
                cleanUp();
                repository.save(Hospital.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562));
            }

            @AfterEach
            void cleanUp() {
                repository.deleteAll();
            }

            @DisplayName("등록된 병원을 모두 조회한다.")
            @Test
            void findAllTest() {
                List<Hospital> hospitals = service.hospitals();

                assertThat(hospitals).isNotEmpty();
                assertThat(hospitals.size()).isEqualTo(1);
            }
        }

        @DisplayName("좌표가 주어지면")
        @Nested
        class Context_with_point {

            private double x = 127.1087781, y = 37.1740255;

            @BeforeEach
            void setUp() {
                cleanUp();
                repository.saveAll(List.of(
                        Hospital.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562),
                        Hospital.of("나인동물의료센터", 127.113226, 37.172993),
                        Hospital.of("이로운 동물병원", 127.1185382, 37.1702334)));
            }

            @AfterEach
            void cleanUp() {
                repository.deleteAll();
            }

            @DisplayName("가까운 순서대로 조회한다.")
            @Test
            void it_returns_sorted_list() {
                GeoResults<Hospital> hospitals = service.hospitals(x, y);

                assertThat(hospitals.getContent().size()).isEqualTo(3);
                Distance dis = hospitals.getContent().get(0).getDistance();
                hospitals.getContent().forEach(result -> {
                    int compare = dis.compareTo(result.getDistance());
                    assertThat(compare <= 0).isTrue();
                });
            }

        }

    }

}
