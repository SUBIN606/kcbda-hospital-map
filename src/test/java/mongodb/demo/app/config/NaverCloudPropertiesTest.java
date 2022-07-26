package mongodb.demo.app.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NaverCloudPropertiesTest.TestConfiguration.class)
class NaverCloudPropertiesTest {

    @Autowired
    private NaverCloudProperties properties;

    @Test
    void test() {
        assertThat(properties.getClientId()).isNotNull();
    }

    @EnableConfigurationProperties({NaverCloudProperties.class})
    public static class TestConfiguration {}

}
