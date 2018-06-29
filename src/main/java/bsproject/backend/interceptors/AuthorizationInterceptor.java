package bsproject.backend.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import bsproject.backend.configs.Config;
import bsproject.backend.annotations.Authorization;
import bsproject.backend.repositories.SQLSessionRepository;
import bsproject.backend.entities.Session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final SQLSessionRepository sqlSessionRepository;

    @Autowired
    public AuthorizationInterceptor(SQLSessionRepository sqlSessionRepository) {
        this.sqlSessionRepository = sqlSessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        String auth = request.getHeader(Config.AUTH_HEADER);

        if (auth == null && method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        if (sqlSessionRepository.existsByToken(auth)) {
            Session session = sqlSessionRepository.findByToken(auth);
            String uid = session.getEmail();
            request.setAttribute(Config.CURRENT_UID_ATTRIBUTE, uid);
        } else {
            // invalid auth provided.
            if (method.getAnnotation(Authorization.class) != null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        return true;
    }
}