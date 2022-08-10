package mongodb.demo.app.repository;

import mongodb.demo.app.domain.HospitalDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class HospitalDocumentRepositoryTest {

    @Autowired
    private HospitalMongoRepository repository;

    @BeforeEach
    void setUp() {
        cleanUp();
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void test() {
        repository.saveAll(List.of(
                HospitalDocument.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562),
                HospitalDocument.of("나인동물의료센터", 127.113226, 37.172993),
                HospitalDocument.of("이로운 동물병원", 127.1185382, 37.1702334)));

        GeoResults<HospitalDocument> list = repository
                .findByLocationNear(
                        new Point(127.1087781, 37.1740255),
                        new Distance(1, Metrics.KILOMETERS)
                );

        list.forEach(it-> System.out.println("distance = " + it.getDistance()));
    }

    @DisplayName("특정 좌표를 기준으로 가까운 순서대로 출력한다.")
    @Test
    void geoNearTest() {
        repository.saveAll(List.of(
                HospitalDocument.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562),
                HospitalDocument.of("나인동물의료센터", 127.113226, 37.172993),
                HospitalDocument.of("이로운 동물병원", 127.1185382, 37.1702334)));

        List<GeoResult<HospitalDocument>> results
                = repository.findByGeoNear(new Point(127.1087781, 37.1740255)).getContent();

        results.forEach(result -> System.out.println("result = " + result));

        assertThat(results.size()).isEqualTo(3);

        Distance dis = results.get(0).getDistance();
        results.forEach(result -> {
            int compare = dis.compareTo(result.getDistance());
            assertThat(compare <= 0).isTrue();
        });
    }

}
