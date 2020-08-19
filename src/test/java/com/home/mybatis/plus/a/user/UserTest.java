package com.home.mybatis.plus.a.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.user.entity.User;
import com.home.mybatis.plus.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基础测试, 依赖user.sql中的表和数据 注意需要添加mapperScan注解
 */
@Slf4j
public class UserTest extends MybatisPlusBootstrapTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询单个用户
     */
    @Test
    public void testQueryUser() {
        log.info("根据ID查询------------------------------------------------------------------------");
        User selectById = userMapper.selectById(1);
        Assert.assertNotNull(selectById);
        Assert.assertEquals(selectById.getName(), "Jone");
        log.info("条件查询一个结果集------------------------------------------------------------------------");
        User selectOne = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1));
        Assert.assertNotNull(selectOne);
        Assert.assertEquals(selectOne.getName(), "Jone");
        log.info("通过sql结果集查询一个结果集------------------------------------------------------------------------");
        User inSql = userMapper.selectOne(
            new QueryWrapper<User>().lambda().inSql(User::getId, "select user_id from address where id = 1"));
        Assert.assertNotNull(inSql);
        Assert.assertEquals(inSql.getName(), "Jone");
        log.info("通过sql注入查询一个结果集------------------------------------------------------------------------");
        User user = userMapper.selectOne(new QueryWrapper<User>().apply("id = {0}", 1));
        Assert.assertNotNull(inSql);
        Assert.assertEquals(inSql.getName(), "Jone");
    }

    /**
     * 查询多个用户
     */
    @Test
    public void testQueryList() {
        log.info("查询多个结果集------------------------------------------------------------------------");
        List<User> users = userMapper.selectList(null);
        Assert.assertTrue(users.size() > 1);
        log.info("通过lambda查询多个结果集------------------------------------------------------------------------");
        List<User> usernameNotNull = userMapper.selectList(new QueryWrapper<User>().lambda().isNotNull(User::getName));
        Assert.assertEquals(usernameNotNull.size(), users.size());
        log.info("通过子集查询多个结果集------------------------------------------------------------------------");
        List<User> inSql =
            userMapper.selectList(new LambdaQueryWrapper<User>().inSql(User::getId, "select user_id from address"));
        Assert.assertTrue(inSql.size() > 1);
        log.info("通过select返回指定的列------------------------------------------------------------------------");
        userMapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId, User::getName)).forEach(i -> {
            assertThat(i.getId()).isNotNull();
            assertThat(i.getName()).isNotNull();
            assertThat(i.getAge()).isNull();
            assertThat(i.getEmail()).isNull();
        });
        log.info("in查询结果集------------------------------------------------------------------------");
        assertThat(userMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getId, Arrays.asList(1, 2))).size())
            .isEqualTo(2);
    }

    /**
     * 插入测试
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setName("insertA");
        user.setEmail("insertA@qq.com");
        user.setAge(38);
        int insert = userMapper.insert(user);
        assertThat(insert).isGreaterThan(0);
    }

    /**
     * 删除数据
     */
    @Test
    public void testDelete() {
        int insertA = userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getName, "insertA"));
        assertThat(insertA).isGreaterThan(0);
    }

    /**
     * 更新数据
     */
    @Test
    public void testUpdate() {
        log.info("测试通过限制条件更新数据------------------------------------------------------------------------");
        assertThat(
            userMapper.update(new User(), Wrappers.<User>lambdaUpdate().set(User::getName, "Jone").eq(User::getId, 1)))
                .isGreaterThan(0);

        log.info("根据wrapper更新数据------------------------------------------------------------------------");
        assertThat(
            userMapper.update(null, Wrappers.<User>lambdaUpdate().set(User::getName, "update").eq(User::getId, 1)))
                .isGreaterThan(0);
        log.info("根据ID更新数据------------------------------------------------------------------------");
        User jone = new User().setId(1L).setAge(26).setEmail("jone@163.com").setName("jone");
        assertThat(userMapper.updateById(jone)).isGreaterThan(0);

        log.info("根据ID更新数据,空值不会进行更新------------------------------------------------------------------------");
        User user = new User().setId(1L).setAge(39);
        assertThat(userMapper.updateById(user)).isGreaterThan(0);
        User result = userMapper.selectById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isNotNull();
        assertThat(result.getEmail()).isNotEmpty();
        assertThat(result.getAge()).isEqualTo(39);

        log.info("将数据修改为null------------------------------------------------------------------------");
        assertThat(userMapper.update(null, Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 1)))
            .isGreaterThan(0);
        User dbUser = userMapper.selectById(1L);
        assertThat(dbUser).isNotNull();
        assertThat(dbUser.getEmail()).isNull();
    }

    @Test
    public void testOrderBy() {
        List<Map<String, Object>> maps = userMapper.selectMaps(Wrappers.<User>lambdaQuery().orderByAsc(User::getId));
        assertThat(maps).isNotNull();
        assertThat(maps.get(0)).isNotNull();
        // 输出结果: maps.get(0) = {name=jone, id=1, age=39}
        System.out.println("maps.get(0) = " + maps.get(0));
    }

    /**
     * 统计
     */
    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = userMapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }
}
