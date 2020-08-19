package com.home.mybatis.plus.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qingdong.li
 * @date 2020/8/19 3:41 下午
 * 注意Accessors注解添加后, 使用BeanUtils.populate()方法是会有问题
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}