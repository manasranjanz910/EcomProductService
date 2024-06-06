package dev.manas.EcomProductService.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAuthClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${UserAuth.LoginUrl}")
    private String loginUrl;
    @Value("${UserAuth.validateTokenUrl}")
    private String validateUser;

    public boolean validateToken(String token)
    {
        String validateUrl = validateUser;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Boolean> result  = restTemplate.getForEntity(validateUrl, Boolean.class);
        return result.getBody();
    }




}
