package com.home.mybatis.plus.c.auto.fill;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.user.entity.UserEntity;
import com.home.mybatis.plus.user.mapper.UserEntityMapper;

/**
 * @author liqingdong
 **/
public class AutoFillTest extends MybatisPlusBootstrapTest {

    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * @author liqingdong 测试通过 MetaObjectHandler注入operator值
     */
    @Test
    public void testInsertFill() {
        boolean userEntity = new UserEntity().setAge(26).setName("小A").setEmail("aa@163.com").insert();
        assertThat(userEntity).isTrue();
        UserEntity result =
            new UserEntity().selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getName, "小A"));
        assertThat(result).isNotNull();
        assertThat(result.getOperator()).isEqualTo("insertFill");
        result.deleteById();
    }

    /**
     * 测试手动设置操作人, 不通过注入器注入值
     */
    @Test
    public void testInsertUnFill() {
        boolean userEntity =
            new UserEntity().setAge(26).setName("小B").setEmail("aa@163.com").setOperator("test").insert();
        assertThat(userEntity).isTrue();
        UserEntity result =
            new UserEntity().selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getName, "小A"));
        assertThat(result).isNotNull();
        assertThat(result.getOperator()).isEqualTo("test");
        result.deleteById();
    }

    @Test
    public void testUpdateFill() {
        testInsertFill();
        boolean update =
            new UserEntity().setName("小a").update(Wrappers.<UserEntity>lambdaUpdate().eq(UserEntity::getName, "小A"));
        assertThat(update).isTrue();
        UserEntity userEntity =
            new UserEntity().selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getName, "小a"));
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getOperator()).isEqualTo("updateFill");
        userEntity.deleteById();
    }

}
