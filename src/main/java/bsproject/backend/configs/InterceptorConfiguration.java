package bsproject.backend.configs;

import bsproject.backend.interceptors.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import bsproject.backend.repositories.UserRepository;
import bsproject.backend.repositories.SQLSessionRepository;

import bsproject.backend.interceptors.CurrentUserInterceptor;

import java.util.List;

@Configuration
public class InterceptorConfiguration {
    private final SQLSessionRepository sqlSessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public InterceptorConfiguration(SQLSessionRepository sqlSessionRepository, UserRepository userRepository) {
        this.sqlSessionRepository = sqlSessionRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public WebMvcConfigurer addAuthorizationInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AuthorizationInterceptor(sqlSessionRepository));
            }
        };
    }

    @Bean
    public WebMvcConfigurer addUserArgumentSolver() {
        return new WebMvcConfigurer() {
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new CurrentUserInterceptor(userRepository));
            }
        };
    }
}