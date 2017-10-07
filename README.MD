                                                    88ybg 网站 源码开源
正式地址 www.88ybg.com

QQ群：314658875（收费），310391018（免费）

###  声明，虽然本产品是开源。但未经本人允许擅自申请专利，将公开追究法律责任。

### 更多信息 请移步到wiki [链接地址](http://git.oschina.net/YYDeament/88ybg/wikis/A1-%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E8%B4%A6%E5%8F%B7
)





 数据库在本页面的附件中有 或者交流群也有
-------------------------------------------------------------------------------------------------------
### 系统介绍

![输入图片说明](https://git.oschina.net/uploads/images/2017/0917/222127_cef2a748_880593.png "云系统(7).png")

88ybg 又名88云报告 ，是本人所开发的一个以工作报告为目的的系统网址。


![输入图片说明](https://git.oschina.net/uploads/images/2017/0917/222625_e0a53f72_880593.png "框架(1).png")

### 本系统的特点：

以Springboot为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强。2种打包方式，传统可运行的tomcat目录 以及直接jar 方式运行。主要应用技术：Security+Ehcache+quartz+swagger2+Mysql5.6.... Ehcahce 缓存,减少数据库压力quartz 自定义任务执行时间。在线开放API文档 模块，一览系统所有的功能，生成在线API文档,并且可以调试。多维度监控系统，包含sql 监控，内存监控和管理，cpu监控，缓存管理，并发监控，磁盘监控，线程监控和管理，session监控，java 开销监控 等


系统运行需求：当前所有模块引用时，需要700M 以上的空余内存（不包含mysql).

-----------------------------------------------------------------------------------------------------------------

### 所用技术



| 搭配环境      | 版本 |
| --------- | -----:|
| java  | 8 |
| Maven     |   3.3.9 |
| eclipse      |    neon2 |
| mysql |    5.6+|
| svn 插件 | eclipse SVN插件  |
| svn 工具 | tortoiseSVN  |


--------------------------------------------

| 技术选型      | 版本 |  描述 |
| ---------   | -----  | -----:|
| spring boot    | 1.5.7 | |
| spring         |    | |
| springjdbc    |    | |
| springmvc    |    | |
|  spring social       |    | |
| ehcache |   | |
| spring session | | |
| quartz| | |
| freemarker | 2.X| |
| swagger2| 2.X| |
| activiti| 5.22.0 | |
| vue.js| 2.X| |
| Jquery|   | |
| qrcode|   | 二维码|
| layerui|   | |
| adminlte|   | |
| kaptche|   | 验证码|
| druid|   | 数据库连接池|
| javamail|   | 邮箱发送|
| mysql|  5.6 | 数据库|
| poi|  | excel 操作|
| javamelody|  | 系统监控|
| https|  | 传输安全（可选是否集成）|
| jwt|  | （未使用）|
| oss|  | 云存储|
| sdk-dysmsapi |  | 阿里云短信（未使用）|
| spring-boot-admin |  | 系统监控|
| jdk       |  8 | |
| maven      |  3.3.9 | | 


--------------------------------------------
