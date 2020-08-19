package com.home.mybatis.plus.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * TableName : 映射数据库表名
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends Model<UserEntity> {
    /** 使用数据库自增主键, AR模式下默认使用雪花算法生成主键 */
    @TableId(type = IdType.AUTO)
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