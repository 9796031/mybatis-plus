
# 1 单表增删改查
com.home.mybatis.plus.a.user.UserTest   
依赖表数据
```sql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID' auto_increment,
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');


create table address(
id bigint(20) primary key auto_increment,
user_id bigint(20) not null,
addr varchar(50) default '' not null
);

insert into address (id,user_id, addr) values
(1,1,'北京'),
(2,1,'上海'),
(3,2,'黑河'),
(4,3,'哈尔滨');

```
# 2 active record方式演示
可以直接通过UserEntity对象链式操作进行增删改查
com.home.mybatis.plus.b.user.UserEntityTest类  
**注意:**
- UserEntity需要继承com.baomidou.mybatisplus.extension.activerecord.Model
- UserEntity类需要重写```pkVal```方法
- 虽然不用UserEntityMapper但是也要有

**AR方式, 主键ID默认使用雪花算法生成ID**

# 2 自动填充字段
## 2.1 测试类
com.home.mybatis.plus.c.auto.fill.AutoFillTest
## 2.2 使用配置
1. 配置填充内容, insert,update时如何处理自动填充字段
com.home.mybatis.plus.handle.OperatorMetaObjectHandler
2. 实体类将需要填充的字段添加上@TableField(fill = FieldFill.INSERT_UPDATE)注解
注解属性可选

# 3 执行SQL分析
注入SqlExplainInterceptor对象  
在Mapper接口中添加```@Mapper```注解  
通过控制台进行查看

# 4 生成器
生成器是mybatis-plus提供的, 方便通用mapper, service, controller  

生成的类在com.home.mybatis.plus.address.*
# 5 测试生成器生成service使用
com.home.mybatis.plus.address.AddressTest