package com.home.mybatis.plus.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qingdong.li
 * @date 2020/8/19 3:41 下午
 * 注意Accessors注解添加后, 使用BeanUtils.populate()方法是会有问题
 */
@Data
@Accessors(chain = true)
/** 映射数据库表名 */
@TableName("user")
public class UserEntity extends Model<UserEntity> {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Override
    protected Serializable pkVal() {
        /**
         * AR 模式这个必须有，否则 xxById 的方法都将失效！
         * 另外 UserMapper 也必须 AR 依赖该层注入，有可无 XML
         */
        return id;
    }
}