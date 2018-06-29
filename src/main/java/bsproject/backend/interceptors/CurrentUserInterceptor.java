package bsproject.backend.interceptors;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import bsproject.backend.configs.Config;
import bsproject.backend.annotations.CurrentUser;
import bsproject.backend.entities.User;
import bsproject.backend.repositories.UserRepository;

@Component
public class CurrentUserInterceptor implements HandlerMethodArgumentResolver {
    private final UserRepository userRepository;

    @Autowired
    public CurrentUserInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(@NotNull MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @NotNull
    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, @NotNull ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest, @NotNull WebDataBinderFactory binderFactory) throws Exception {
        Object uid = webRequest.getAttribute(Config.CURRENT_UID_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return userRepository.findByEmail((String) uid);
    }
}