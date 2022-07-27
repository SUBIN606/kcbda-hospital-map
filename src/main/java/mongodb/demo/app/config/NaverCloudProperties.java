package mongodb.demo.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;

/**
 * 네이버 클라우드 플랫폼 클라이언트 아이디, 시크릿
 * naver-cloud-key.yml 파일 참고
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "naver")
@PropertySource("classpath:naver-cloud-key.yml")
public class NaverCloudProperties {

    private String clientId;
    private String clientSecret;

    public NaverCloudProperties(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

}
