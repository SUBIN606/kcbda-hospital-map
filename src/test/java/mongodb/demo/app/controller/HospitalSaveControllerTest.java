package mongodb.demo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mongodb.demo.app.repository.HospitalMongoRepository;
import mongodb.demo.app.repository.HospitalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@MockMvcWithUtf8
class HospitalSaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HospitalMongoRepository mongoRepository;
    @Autowired
    private HospitalRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URL = "/hospitals";

    @BeforeEach
    void setUp() {
        cleanUp();
    }

    @AfterEach
    void cleanUp() {
        mongoRepository.deleteAll();
        repository.deleteAll();
    }

    @DisplayName("[POST] /hospitals - 신규 병원 등록")
    @Nested
    class Describe_save_hospital {

        private final HospitalSaveController.HospitalSaveDto saveDto =
                new HospitalSaveController.HospitalSaveDto("24시 동탄 이음동물의료센터", 127.1019816, 37.1678562);

        @DisplayName("병원이 정상적으로 등록되면 201 created 상태코드를 응답한다.")
        @Test
        void it_response_201_created() throws Exception {
            mockMvc.perform(post(URL)
                            .content(objectMapper.writeValueAsString(saveDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

            assertThat(mongoRepository.findAll().size()).isEqualTo(1);
            assertThat(repository.findAll().size()).isEqualTo(1);
        }
    }


}
