package vumc.org.springreact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import vumc.org.springreact.utils.Constants;


@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Constants.CORS_ALLOWED_ORIGINS);
        corsConfiguration.setAllowedHeaders(Constants.CORS_ALLOWED_HEADERS);
        corsConfiguration.setExposedHeaders(Constants.CORS_EXPOSED_HEADERS);
        corsConfiguration.setAllowedMethods(Constants.CORS_ALLOWED_METHODS);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration(Constants.CORS_CONFIGURATION_PATTERN, corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}