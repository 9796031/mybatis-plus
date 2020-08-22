package com.home.mybatis.plus.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author liqingdong
 * 填充器
 **/
@Slf4j
@Component
public class OperatorMetaObjectHandler implements MetaObjectHandler {

    /**
     * @author liqingdong
     * 当插入时填充值
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert fill success !!!");
        String operator = (String)this.getFieldValByName("operator", metaObject);
        if (StringUtils.isEmpty(operator)) {
            this.strictInsertFill(metaObject, "operator", String.class, "insertFill");
        }
    }

    /**
     * @author liqingdong
     * 当更新数据时填充值
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update fill success !!!");
        this.strictUpdateFill(metaObject, "operator", String.class, "updateFill");
    }
}
