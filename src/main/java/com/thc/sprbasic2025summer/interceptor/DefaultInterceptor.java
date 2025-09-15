package com.thc.sprbasic2025summer.interceptor;

import com.thc.sprbasic2025summer.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    String token_prefix = "Bearer ";

    private TokenFactory tokenFactory;
    public DefaultInterceptor(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle / request [{}]", request);

        String accessToken = request.getHeader("Authorization");
        logger.info("preHandle / accessToken [{}]", accessToken);
        if(accessToken != null && !accessToken.isEmpty() && accessToken.startsWith(token_prefix)){
            // 정상 토큰 입니다!
            accessToken = accessToken.substring(token_prefix.length());
            System.out.println("preHandle / accessToken [" + accessToken + "]");
            Long userId = tokenFactory.validateAccessToken(accessToken);
            if(userId != null){
                request.setAttribute("reqUserId", userId);
            }
        }



        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle / request [{}]", request);
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion / request [{}]", request);
    }

}