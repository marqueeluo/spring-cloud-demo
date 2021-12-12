package com.luo.demo.seata.controller;

import com.luo.demo.sc.base.enums.RespCodeEnum;
import com.luo.demo.sc.base.execption.MsgRuntimeException;
import com.luo.demo.sc.base.utils.JsonUtils;
import com.luo.demo.seata.base.BaseTest;
import com.luo.demo.seata.mapper.AccountMapper;
import com.luo.demo.seata.model.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.Assume;
import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

/**
 * 用户controller - 测试类
 *
 * @author luohq
 * @date 2021-12-11 11:18
 */
@Slf4j
public class AccountControllerTest extends BaseTest {


    //mockBean + spyBean
    //Assertions
    //TODO Assumptions
    //TODO ParameterizedTest
    //TODO maven插件


    //@MockBean
    @SpyBean
    private AccountMapper accountMapper;


    /**
     * 用户扣款
     *
     * @httpMethod post
     * @httpParam  form
     * @mvcParam   @RequestParam
     *
     * @throws Exception
     */
    @Test
    void debit() throws Exception {

        //录制mock操作
        //若使用@MockBean，则应使用when(targetObj.method(param...)).thenReturn(returnVal).thenThrow(ex)形式
        //when(this.accountMapper.debit(any(), any())).thenReturn(1);

        //录制spy操作
        //若使用@SpyBean，则应使用doReturn(returnVal).when(targetObj).method(param...)形式，
        //否则具体方法会被真实调用一次（引入未知问题，如null指针异常等）
        doReturn(1).when(this.accountMapper).debit(any(), any());


        this.mockMvc.perform(post("/account/debit")
                //post form参数
                .param("userId", "user-1")
                .param("money", "100.00"))
                //打印请求详细信息
                .andDo(print())
                //判断返回结果200
                .andExpect(status().isOk())
                //判断content-type兼容application/json
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //判断响应体respCode为成功
                .andExpect(jsonPath("$.respCode").value(RespCodeEnum.SUCCESS.getCode()));
    }

    /**
     * 获取用户详情
     *
     * @httpMethod get
     * @httpParam  queryParam
     * @mvcParam   @RequestParam
     *
     * @throws Exception
     */
    @Test
    void getDetail() throws Exception {

        Integer id = 1;
        //录制mock操作
        //when(this.accountMapper.selectById(any())).thenReturn(this.buildAccount(id, "user-100"));
        //录制spy操作
        doReturn(this.buildAccount(id, "user-100")).when(this.accountMapper).selectById(any());

        this.mockMvc.perform(get("/account/detail")
                //get queryParam参数
                .queryParam("id", id.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value(RespCodeEnum.SUCCESS.getCode()));

    }

    /**
     * 获取用户详情
     *
     * @httpMethod get
     * @httpParam  pathParam
     * @mvcParam   @PathVariable
     *
     * @throws Exception
     */
    @Test
    void getDetailWithId() throws Exception {
        Integer id = 1;
        //录制mock操作
        //when(this.accountMapper.selectById(any())).thenReturn(this.buildAccount(id, "user-100"));
        //录制spy操作
        doReturn(this.buildAccount(id, "user-100")).when(this.accountMapper).selectById(any());

        //get pathParam参数
        this.mockMvc.perform(get("/account/detail/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value(RespCodeEnum.SUCCESS.getCode()));

    }

    /**
     * 新增用户信息
     *
     * @httpMethod post
     * @httpParam  application/json
     * @mvcParam   @RequestBody
     *
     * @throws Exception
     */
    @Test
    void addAccount() throws Exception {
        //请求体
        Account account = this.buildAccount(null, "user-100");
        String accountJson = JsonUtils.toJson(account);
        log.info("用户信息json: {}", accountJson);

        //录制mock操作
        //when(this.accountMapper.insert(any())).thenReturn(1);
        //录制spy操作
        doReturn(1).when(this.accountMapper).insert(any());


        MvcResult mvcResult = this.mockMvc.perform(post("/account/add")
                //application/json请求体
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF_8)
                //具体请求Json参数
                .content(accountJson))
                .andDo(print())
                .andExpect(status().isOk())
                //由于在BaseTest setup中设置response.characterEncoding=UTF-8后，
                //此处实际返回MediaType.APPLICATION_JSON_UTF8，即application/json;charset=UTF-8
                //所以使用contentTypeCompatibleWith
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value(RespCodeEnum.SUCCESS.getCode()))
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        log.info("新增用户信息，结果：{}", resultJson);
    }

    /**
     * 新增用户信息
     *
     * @httpMethod post
     * @httpParam  application/json
     * @mvcParam   @RequestBody
     *
     * @throws Exception
     */
    @Test
    void addAccountUtf8() throws Exception {
        Account account = this.buildAccount(null, "用户-100");
        String accountJson = JsonUtils.toJson(account);
        log.info("用户信息json: {}", accountJson);

        ///录制mock操作
        //when(this.accountMapper.insert(any())).thenReturn(1);
        //录制spy操作
        doReturn(1).when(this.accountMapper).insert(any());

        //设置post请求及参数
        ResultActions resultActions  = this.mockMvc.perform(post("/account/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF_8)
                .content(accountJson));
        //设置响应结果编码
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //验证响应结果
        MvcResult mvcResult = resultActions.andDo(print())
                .andExpect(status().isOk())
                //设置完response.characterEncoding=UTF-8后，
                //此处实际返回MediaType.APPLICATION_JSON_UTF8，即application/json;charset=UTF-8
                //所以使用contentTypeCompatibleWith
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value(RespCodeEnum.SUCCESS.getCode()))
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        log.info("新增用户信息，结果：{}", resultJson);
    }

    @Test
    void testJUnit5Assertions() {
        Integer result1 = 1;
        Integer result2 = 2;

        String[] strArray1 = {"A", "B", "C"};
        String[] strArray2 = {"A", "B", "C"};
        String[] strRegexArray = {"\\w", "B", "C"};

        /** assert格式：assertXXX(期望值, 实际值, 错误提示信息) */

        /** ========== 是否相等 ========== */
        //是否相等（通过equals判断）
        assertEquals(1, result1);
        assertNotEquals(2, result1);

        //为true或false
        assertTrue(() -> true);
        assertFalse(false);

        //是否为同一个对象引用（通过==判断）
        assertSame(result1, result1);
        assertNotSame(result1, result2);

        //数组是否相同
        assertArrayEquals(strArray1, strArray2);
        //iterable是否否相同（即collection，如List、set等）
        assertIterableEquals(Arrays.asList(strArray1), Arrays.asList(strArray2));
        //判断List<String>, Stream<String>是否相同或者正则匹配（或者fast-forward）
        assertLinesMatch(Arrays.asList(strRegexArray), Arrays.asList(strArray1));

        //是否是子类型
        Number num = assertInstanceOf(Number.class, result1);


        /** ========== 是否抛出异常 ========== */
        //抛出指定类型异常(抛出的异常是指定异常的子类型即可)
        RuntimeException runEx = assertThrows(RuntimeException.class, () -> {
            throw new MsgRuntimeException("测试异常");
        });
        assertEquals("测试异常", runEx.getMessage());

        //抛出指定类型异常(抛出的异常和指定异常的class类型必须完全相同)
        runEx = assertThrowsExactly(RuntimeException.class, () -> {
            throw new RuntimeException("测试异常");
        });
        assertEquals("测试异常", runEx.getMessage());

        //不抛出任何异常
        assertDoesNotThrow(() -> {
            log.info("test assertDoesNotThrow");
        });

        //所有的executables（在当前线程依次执行）均不抛出异常
        assertAll(() -> {}, () -> {}, () -> {});

        /** ========== 是否执行超时 ========== */
        //executable是否执行超时
        //在当前线程执行executable，在执行完成后计算超时，所以不会打断executable执行
        assertTimeout(Duration.ofSeconds(3), () -> {
            Thread.sleep(2000);
        });

        //executable是否执行超时
        //在不同线程执行executable，在executable执行算超后，可被抢占式abort
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            Thread.sleep(2000);
        });

        /** ========== 手动触发失败 ========== */
        fail("手动触发测试失败", new RuntimeException("手动触发测试失败异常"));
    }

    @Test
    void testJUnit5Assumptions() {
        Integer result1 = 1;
        Integer result2 = 2;

        String[] strArray1 = {"A", "B", "C"};
        String[] strArray2 = {"A", "B", "C"};
        String[] strRegexArray = {"\\w", "B", "C"};

        /** assert格式：assertXXX(期望值, 实际值, 错误提示信息) */

    }

    private Account buildAccount(Integer id, String userId) {
        Account account = new Account();
        account.setId(id);
        account.setUserId(userId);
        account.setMoney(BigDecimal.valueOf(1000.0));
        String accountJson = JsonUtils.toJson(account);
        log.info("用户信息json: {}", accountJson);
        return account;
    }

}
