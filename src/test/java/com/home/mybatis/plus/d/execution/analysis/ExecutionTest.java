package com.home.mybatis.plus.d.execution.analysis;

import java.time.LocalDateTime;

import org.junit.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.company.entity.CompanyEntity;
import com.home.mybatis.plus.company.mapper.CompanyEntityMapper;

/**
 * @author liqingdong 执行SQL分析
 **/
public class ExecutionTest extends MybatisPlusBootstrapTest {

    @Autowired
    private CompanyEntityMapper companyEntityMapper;

    @Test
    public void testExecution() {
        companyEntityMapper.selectList(new QueryWrapper<>());
        companyEntityMapper.deleteById(1L);
        CompanyEntity student = new CompanyEntity();
        student.setName("test_update");
        companyEntityMapper
            .insert(new CompanyEntity(1L, "test", LocalDateTime.now(), "", LocalDateTime.now(), LocalDateTime.now()));
        companyEntityMapper.update(student, new QueryWrapper<CompanyEntity>().eq("id", 1L));
        try {
            companyEntityMapper.update(new CompanyEntity(), new QueryWrapper<>());
        } catch (MyBatisSystemException e) {
        }
        try {
            companyEntityMapper.delete(new QueryWrapper<>());
        } catch (MyBatisSystemException e) {

        }
        Assert.notEmpty(companyEntityMapper.selectList(new QueryWrapper<>()), "数据都被删掉了.(┬＿┬)");

    }
}
