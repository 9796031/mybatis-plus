package com.home.mybatis.plus.address;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.mybatis.plus.MybatisPlusBootstrapTest;
import com.home.mybatis.plus.address.entity.Address;
import com.home.mybatis.plus.address.mapper.AddressMapper;
import com.home.mybatis.plus.address.service.IAddressService;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class AddressTest extends MybatisPlusBootstrapTest {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testAdd() {
        Address address = new Address(5L, 4L, "七台河");
        boolean save = addressService.save(address);
        System.out.println("save = " + save);
    }

    @Test
    public void testBatchAdd() {
        Address address1 = new Address(6L, 4L, "七台河");
        Address address2 = new Address(7L, 4L, "七台河");
        boolean b = addressService.saveBatch(Arrays.asList(address1, address2));
        System.out.println("b = " + b);
    }

    @Test
    public void testSaveOrUpdate() {
        Address address = new Address(7L, 4L, "甘南");
        boolean b = addressService.saveOrUpdate(address);
        System.out.println("b = " + b);
    }

    /** 通过查询删除记录 */
    @Test
    public void testRemove() {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.lambda().eq(Address::getId, 7);
        addressService.remove(qw);
    }

    @Test
    public void testUpdate() {
        UpdateWrapper<Address> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(Address::getAddr, "上海")
                .eq(Address::getId, 6);
        boolean update = addressService.update(wrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void testGetOne() {
        Address one = addressService.getOne(new QueryWrapper<Address>().lambda().eq(Address::getId, 1L));
        System.out.println("one = " + one);
    }

    @Test
    public void testPage() {
//        addressMapper.
        Page<Address> page = new Page<>(1, 1);

        Page<Address> page1 = addressService.page(page);
        System.out.println("page1 = " + JSONValue.toJSONString(page1));
        System.out.println("page1 = " + page1);
    }
}
