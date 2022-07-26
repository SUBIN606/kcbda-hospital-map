package mongodb.demo.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;

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
