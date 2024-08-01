package app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/login", "/oauth2/**").permitAll() // 누구나 접근 가능
                        .anyRequest().authenticated() // 인증이 필요한 요청
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/loginSuccess", true) // 로그인 성공 후 리다이렉트 URL
                        .failureUrl("/loginFailure") // 로그인 실패 시 리다이렉트 URL
                )
                .httpBasic(Customizer.withDefaults()); // 기본 HTTP 인증

        return http.build();
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration.withRegistrationId("my-client")
                .clientId("your-client-id")
                .clientSecret("your-client-secret")
                .scope("read", "write")
                .authorizationUri("https://example.com/oauth2/authorize")
                .tokenUri("https://example.com/oauth2/token")
                .userInfoUri("https://example.com/userinfo")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();

        return new InMemoryClientRegistrationRepository(Arrays.asList(registration));
    }
}
