package com.home.mybatis.plus.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author liqingdong
 **/
@Data
@TableName("mp_company")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity extends Model<CompanyEntity> {

    private long id;

    private String name;

    private LocalDateTime registerDate;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
