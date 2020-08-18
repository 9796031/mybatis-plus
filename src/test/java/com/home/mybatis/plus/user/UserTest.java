package com.home.mybatis.plus.user;

import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.user.entity.User;
import com.home.mybatis.plus.user.mapper.UserMapper;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基础测试, 依赖user.sql中的表和数据
 * 注意需要添加mapperScan注解
 */
public class UserTest extends MybatisPlusBootstrapTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查询
     */
    @Test
    public void testQueryUser() {
        User user = userMapper.selectById(1);
        System.out.println(JSONValue.toJSONString(user));
    }

    /**
     * 查询全部
     */
    @Test
    public void testQueryAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
