package com.home.mybatis.plus.address.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author liqingdong
 * @since 2020-08-17
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String addr;

}
