package com.doraro.filter;

import com.doraro.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.doraro.utils.JwtUtil.extractToken;

public class TokenFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request.getHeader(HEADER));
            JwtUtil jwtBean = getJWTBean();
            Claims claimsFromToken = jwtBean.getClaimsFromToken(token);
            if (claimsFromToken == null || claimsFromToken.getId() == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token 无效");
                return;
            }
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未认证");
        }
    }


    private JwtUtil getJWTBean() {
        ServletContext servletContext = super.getFilterConfig().getServletContext();
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean(JwtUtil.class);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "OPTIONS".equals(request.getMethod());
    }
}
