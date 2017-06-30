                                                    88ybg 网站 源码开源
由于服务器内存有限，正式网址当前使用旧版本，大家不要去访问了。

QQ群：314658875


 声明，虽然本产品是开源。但未经本人允许擅自申请专利，将公开追究法律责任。

测试数据库地址：[下载地址](https://share.weiyun.com/0ee7e5d96563534816eabbee7cd5491e) 数据库更新时间-2017年6月29日 09:54:56



 部署说明
1.使用百度登陆（需要去开放平台申请）

修改数据库表  baidu_login_setting_1 添加密钥和APPiD

2.使用新浪登陆（需要去开放平台申请）

修改数据库表  sina_login_setting_1 添加密钥和APPiD

3.使用QQ登陆（需要去开放平台申请）

![输入图片说明](https://git.oschina.net/uploads/images/2017/0623/073508_015b8d87_880593.png "在这里输入图片标题")
修改文件qqconnectconfig.properties 文件的密钥和APPiD

4.修改数据库 

![输入图片说明](https://git.oschina.net/uploads/images/2017/0623/073710_0c65d7f3_880593.png "在这里输入图片标题")
找到相关Key 修改

5.修改系统电子邮箱

![输入图片说明](https://git.oschina.net/uploads/images/2017/0623/073752_c3f8a5b8_880593.png "在这里输入图片标题")

找到相关的key 修改


-------------------------------------------------------------------------------------------------------------
### 代码结构






 -quanming_admin 项目名称

 --main(主要运行/打包模块）

 --ybg_base_jar 底层编译
 
 --ybg_base_jdbc Dao层的jdbc 的一些封装方法和类
 
 --ybg_base_util 工具类
 
 --ybg_company 企业模块

 --ybg_email 邮箱模块
 
 --ybg_inter 接口

 --ybg_jwt   java web token 模块
 
 --ybg_login_baidu 百度登陆

 --ybg_login_qq QQ登陆
 
 --ybg_login_sina 新浪登陆

 --ybg_mq 阿里MQ
 
 --ybg_quartz 定时任务
 
 --ybg_rbac 权限管理 (Controller)
 
 --ybg_rbac_base 权限底层(Service Dao 等）
 
 --ybg_weixin 微信模块
 
 --ybg_gen 代码生成模块（未完成 开发中）

-----------------------------------------------------------------------------------------------------------------

### 所用技术

1.Spring boot 1.5.3

2.Spring jdbc

3.Spring 

4.Spring Security 权限框架

5.Ehcache 缓存技术

6.阿里MQ 消息队列

7.layer.js 界面JS

8.jquery 

9.freemarker 模板引擎

10.jdk8

11.QQ 登陆

12.百度登陆

13.新浪登陆

14.微信登陆（开发中)

15.Email 邮箱发送

16.jwt(开发中） JAVA WEB TOKEN

17.quartz 定时任务

18.druid 连接池

19.javamelody 系统监控

20.Swagger 在线API文档


------------------------------------------------------------------------------------------------------------------

###  **1.0 java代码规范（临时）** （日后将参照阿里java 规范）


1.1 实体类（Domain包下）分为两个，一个是和数据库一样的实体 A，另一个 则是继承A的B  用来扩展和显示的.A 一般要重写toString()方法

1.2 实体类A 和 B 所有变量都是小写

1.3 实体类变量不可以出现“_”  下划线这种字符（除非第三方的数据变量名有下划线），只能是字母或字母+数字，

1.4 Qvo类继承A类 并添加布尔类型的变量 blurred ，该字段代表是否模糊查询，默认是false;

1.5 基础表不实现物理删除 ，多对多关联表可以物理删除， 字段是isdelete 1代表已删除，0是默认 

1.6 不使用StringBuffer 使用StringBuilder来处理字符串拼接

1.7 import中不能有通配符。

1.8 重载方法应该放在一起连续出现。

1.9 即使代码块中没有内容，或者只有一行代码，也必须使用大括号。

1.10 switch语句中，必须包含default语句。

1.11 修饰符的顺序应按照Java语言规范推荐的顺序。

1.12 常量命名应使用CONSTANT_CASE格式（译注：所有字母大写，单词使用下划线分隔）。注意，所有常量都必须是static final成员，但并不是所有的static final成员都是常量。

1.13 更新 删除 新增可以用baseDao封装好的方法去处理，但是查询不要玩Map ，切记。

1.14 不引用的多余包。

1.15 对于复杂的算法，请写好相关注释


###  **2.0 数据库代码规范** 


2.1 表名可以带有“_” 下划线这种字符，列字段名称不可以

2.2 数据库列的数据类型，一般只能是数字，字符串，浮点数，BLOB,不使用Date日期类型。日期用varcher 30

2.3 项目中，不可以出现 select   * from 这种查询

2.4 数据库 应满足三范式要求，必须保证数据的原子性。

2.4.1 数据不可用逗号分隔。比如说电话号码字段。

2.4.2 非以来主属性的列 不要放在同个表中。比如说学生依赖班级，班级表有班级id和班级名称。学生表可以放班级id作外键,但是不能放班级名称。

2.4.3 数据库范式  参考资料 http://www.cnblogs.com/linjiqin/archive/2012/04/01/2428695.html

2.4.4 数据库表关联时，不使用临时表，例如 select * from (select * from stu where ..)t left join (select * from class where  ...)cla on...

要写成 select * from stu left   join class  on  where ... 因为数据库是mysql 他会自动优化，如果是oracle则可以使用第一种方式。

2.4.5 Java写法中 数据库sql语句全部关键字一律大写，其他小写。

----------------------------------------------------------------------------------------------------------------
