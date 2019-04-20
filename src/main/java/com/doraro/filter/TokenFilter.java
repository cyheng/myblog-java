package com.doraro.filter;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.shiro.Oauth2Token;
import com.doraro.utils.Constant;
import com.doraro.utils.HttpContextUtils;
import com.doraro.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Setter
public class TokenFilter extends AuthenticatingFilter {

    private ObjectMapper mapper;

    /**
     * 提取header或param中的token值
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String token = HttpContextUtils.getAttr((HttpServletRequest) request, Constant.TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new Oauth2Token(token);
    }


    /**
     * 允许OPTIONS方法访问
     * 详情：https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS#%E9%A2%84%E6%A3%80%E8%AF%B7%E6%B1%82
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        final String method = ((HttpServletRequest) request).getMethod();
        return method.equals(RequestMethod.OPTIONS.name());
    }

    /**
     * 获取请求token，如果token不存在，直接返回
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = HttpContextUtils.getAttr((HttpServletRequest) request, Constant.TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            final ApiResponses responses = ApiResponses.failure(ErrorCodeEnum.UNAUTHORIZED);
            writeJson(httpResponse, responses);
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * 捕获Relam中doGetAuthenticationInfo抛出的异常并返回到客户端
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        writeJson(httpResponse, ApiResponses.failure(ErrorCodeEnum.FORBIDDEN.getHttpCode(), e.getMessage()));
        return false;
    }


    private void writeJson(HttpServletResponse httpResponse, ApiResponses responses) {
        setHeader(httpResponse);
        try {
            String json = mapper.writeValueAsString(responses);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void setHeader(HttpServletResponse httpResponse) {
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
    }
}
