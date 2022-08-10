package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.domain.HospitalDocument;
import mongodb.demo.app.repository.HospitalMongoRepository;
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
import static org.junit.jupiter.api.Assertions.assertAll;

@ActiveProfiles("test")
@DisplayName("HospitalQueryService - 조회")
@SpringBootTest
class HospitalQueryServiceImplTest {

    private HospitalQueryService service;
    @Autowired
    private HospitalMongoRepository mongoRepository;
    @Autowired
    private HospitalRepository repository;

    @BeforeEach
    void setup() {
        this.service = new HospitalQueryServiceImpl(mongoRepository, repository);
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
                mongoRepository.save(HospitalDocument.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562));
                repository.save(Hospital.of("이로운 동물병원"));
            }

            @AfterEach
            void cleanUp() {
                mongoRepository.deleteAll();
                repository.deleteAll();
            }

            @DisplayName("RDB에 저장된 병원을 모두 조회한다.")
            @Test
            void findAllTest() {
                List<Hospital> hospitals = service.hospitals();

                assertAll(()-> {
                    assertThat(hospitals).isNotEmpty();
                    assertThat(hospitals.size()).isEqualTo(1);
                    assertThat(hospitals.get(0).getName()).isEqualTo("이로운 동물병원");
                });
            }
        }

        @DisplayName("좌표가 주어지면")
        @Nested
        class Context_with_point {

            private double x = 127.1087781, y = 37.1740255;

            @BeforeEach
            void setUp() {
                cleanUp();
                mongoRepository.saveAll(List.of(
                        HospitalDocument.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562),
                        HospitalDocument.of("나인동물의료센터", 127.113226, 37.172993),
                        HospitalDocument.of("이로운 동물병원", 127.1185382, 37.1702334)));
            }

            @AfterEach
            void cleanUp() {
                mongoRepository.deleteAll();
            }

            @DisplayName("가까운 순서대로 조회한다.")
            @Test
            void it_returns_sorted_list() {
                GeoResults<HospitalDocument> hospitals = service.hospitals(x, y);

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
