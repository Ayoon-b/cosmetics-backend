package com.teamtbd.cosmetics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CosmeticsBackendApplicationTests {

	@Autowired
	OAuth2ClientProperties oAuth2ClientProperties;
	@Test
	void contextLoads() {
		System.out.println("oAuth2ClientProperties = " + oAuth2ClientProperties);
	}

}
