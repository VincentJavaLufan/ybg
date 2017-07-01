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

###  **系统主要结构

系统包图



![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/170750_5ba5186a_880593.png "在这里输入图片标题")



----------------------------------------------------------------------------------------------------------------

系统界面
![登陆界面](https://git.oschina.net/uploads/images/2017/0701/080625_127ef49d_880593.png "登陆界面")

![账号密码错误](https://git.oschina.net/uploads/images/2017/0701/080713_12d32f30_880593.png "账号密码错误")

![用户管理](https://git.oschina.net/uploads/images/2017/0701/080842_8697e8f8_880593.png "用户管理")

![角色管理](https://git.oschina.net/uploads/images/2017/0701/080914_4aca9b42_880593.png "角色管理")

![分配权限](https://git.oschina.net/uploads/images/2017/0701/081208_2af08e8d_880593.png "分配权限")

![需要分配权限的访问资源](https://git.oschina.net/uploads/images/2017/0701/081302_03810ec4_880593.png "需要分配权限的访问资源")

![没有权限页面](https://git.oschina.net/uploads/images/2017/0701/081542_3c94fff4_880593.png "没有权限页面")

![系统监控](https://git.oschina.net/uploads/images/2017/0701/081723_e789e73f_880593.png "系统监控")

![系统监控](https://git.oschina.net/uploads/images/2017/0701/081745_575583b8_880593.png "系统监控")

![阿里连接池监控](https://git.oschina.net/uploads/images/2017/0701/081833_7e6aeca7_880593.png "阿里连接池监控")