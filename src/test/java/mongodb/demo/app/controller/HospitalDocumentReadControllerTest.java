package mongodb.demo.app.controller;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@MockMvcWithUtf8
class HospitalDocumentReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HospitalMongoRepository mongoRepository;
    @Autowired
    private HospitalRepository repository;

    private final String URL = "/hospitals";

    @BeforeEach
    void setUp() {
        cleanUp();
        mongoRepository.saveAll(List.of(
                HospitalDocument.of("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562),
                HospitalDocument.of("나인동물의료센터", 127.113226, 37.172993),
                HospitalDocument.of("이로운 동물병원", 127.1185382, 37.1702334)));
        repository.saveAll(List.of(
                Hospital.of("24시 동탄 이음동물의료센터"),
                Hospital.of("나인동물의료센터"),
                Hospital.of("이로운 동물병원")
        ));
    }

    @AfterEach
    void cleanUp() {
        mongoRepository.deleteAll();
        repository.deleteAll();
    }

    @DisplayName("[GET] /hospitals - 병원 조회")
    @Nested
    class Describe_hospitals {

        @DisplayName("좌표가 주어지지 않으면")
        @Nested
        class Context_without_point {

            @DisplayName("모든 병원 목록을 응답한다.")
            @Test
            void it_response_all_hospitals() throws Exception {
                mockMvc.perform(get(URL))
                        .andExpect(status().isOk())
                        .andDo(print());
            }
        }

        @DisplayName("좌표가 주어지면")
        @Nested
        class Context_with_point {

            private double x = 127.1087781, y = 37.1740255;

            @DisplayName("가까운 순서대로 응답한다.")
            @Test
            void it_response_sorted_hospitals() throws Exception {
                mockMvc.perform(get(String.format("%s?x=%s&y=%s", URL, x, y)))
                        .andDo(print())
                        .andExpect(status().isOk());
            }
        }
    }

}
