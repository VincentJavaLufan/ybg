                                                    88ybg 网站 源码开源
由于服务器内存有限，正式网址当前使用旧版本，大家不要去访问了。


 

测试数据库地址：[下载地址](https://share.weiyun.com/e220f6d47c3b0f7b0e2bfe41f4731af3)



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
