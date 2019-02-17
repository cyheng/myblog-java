package com.doraro.controller

import com.doraro.DoraroBlogApplication
import com.vip.vjtools.vjkit.base.ObjectUtil
import com.vip.vjtools.vjkit.mapper.JsonMapper
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/***
 * 用于mock mvc
 */
@ContextConfiguration(classes = DoraroBlogApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class SuperMockMvcSetup extends Specification {
    @Autowired
    WebApplicationContext context
    @Shared
    MockMvc mockMvc
    /***
     *
     *  注入context并建立mockmvc
     */
    def "should boot up without errors"() {
        expect: "web application context exists"
        context != null
        mockMvc != null
    }

    void setup() {
        mockMvc = webAppContextSetup(context).build()
    }
    /***
     * get请求
     * @param url
     * @param 参数map[ "id":3,"q":4] ===> id=3&q=4
     * @return 含json content 的 MockHttpServletResponse
     */
    def Get(String url, Map<String, Object> paramsMap = null) {
        def argvs = get(url)
        paramsMap.each {
            k, v -> argvs.param(k, ObjectUtil.toPrettyString(v))
        }
        def content = mockMvc.perform(argvs)
                .andReturn().getResponse()
        return new Response(content)
    }
    /***
     *
     * @param url 目的url
     * @param jsonParam json参数字符串
     * @return 含json content 的 MockHttpServletResponse
     */
    def Post(String url, String jsonParam = null) {
        def builder = post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
        if (jsonParam != null) {
            builder.content(jsonParam)
        }
        def content = mockMvc.perform(builder)
                .andReturn().getResponse()
        return new Response(content)
    }
    /***
     *
     * @param url 目的url
     * @param jsonParam jsonmap
     * @return 含json content 的 MockHttpServletResponse
     */
    def Post(String url, Map<String, Object> jsonParam) {
        def builder = post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
        if (jsonParam != null) {
            builder.content(JsonOutput.toJson(jsonParam))
        }
        def content = mockMvc.perform(builder)
                .andReturn().getResponse()
        return new Response(content)
    }


    private class Response {
        @Delegate
        private MockHttpServletResponse response

        Response(MockHttpServletResponse response) {
            this.response = response
        }

        def getContentAsJson() {
            return JsonMapper.INSTANCE.fromJson(response.contentAsString, HashMap.class)
        }
    }
}
