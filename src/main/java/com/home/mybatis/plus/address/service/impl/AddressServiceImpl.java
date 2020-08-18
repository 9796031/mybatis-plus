package com.home.mybatis.plus.address.service.impl;

import com.home.mybatis.plus.address.entity.Address;
import com.home.mybatis.plus.address.mapper.AddressMapper;
import com.home.mybatis.plus.address.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqingdong
 * @since 2020-08-17
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
