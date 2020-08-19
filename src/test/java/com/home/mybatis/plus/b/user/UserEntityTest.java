package com.home.mybatis.plus.b.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.user.entity.UserEntity;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author qingdong.li
 * @date 2020/8/19 4:40 下午 测试 Active Record 这里需要注意UserEntity类, 注意, 虽然不用userEntityMapper, 但是这个类必须要有
 **/
public class UserEntityTest extends MybatisPlusBootstrapTest {

    @Test
    public void testInsert() {
        boolean activeRecord = new UserEntity().setAge(20).setName("active record").setEmail("ar@163.com").insert();
        assertThat(activeRecord).isTrue();
    }

    @Test
    public void testUpdate() {
        boolean activeRecord = new UserEntity().update(Wrappers.<UserEntity>lambdaUpdate()
            .set(UserEntity::getEmail, "ar@qq.com").eq(UserEntity::getName, "active record"));
        assertThat(activeRecord).isTrue();
        UserEntity userEntity =
            new UserEntity().selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getName, "active record"));
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getEmail()).isEqualTo("ar@qq.com");
    }

    @Test
    public void testSelect() {
        List<UserEntity> userEntities =
            new UserEntity().selectList(new LambdaQueryWrapper<UserEntity>().gt(UserEntity::getId, 0));
        assertThat(userEntities).isNotNull();
        assertThat(userEntities.size()).isGreaterThan(1);
    }

}
