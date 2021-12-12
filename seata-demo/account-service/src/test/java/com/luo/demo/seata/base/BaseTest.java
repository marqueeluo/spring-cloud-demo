package com.luo.demo.seata.base;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * 基础测试类 - 以mock方式启动
 *
 * @author luohq
 * @date 2021-12-11 11:19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class BaseTest {

    protected final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
    protected final String UTF_8 = "UTF-8";
    private final String URL_PATTERN_ALL = "/*";


    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                //添加拦截器 - 设置mockMvc默认响应编码UTF-8
                //覆盖MockHttpServletResponse.characterEncoding默认为为WebUtils.DEFAULT_CHARACTER_ENCODING = "ISO-8859-1"
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding(UTF_8);
                    chain.doFilter(request, response);
                }, URL_PATTERN_ALL)
                .build();
    }
}
