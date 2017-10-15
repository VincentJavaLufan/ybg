                                                    88ybg 网站 源码开源
正式地址 www.88ybg.com(`不一定是最新的而且账号密码不是admin/11111111提供的开源数据库才是`)

QQ群：`314658875（收费）`，310391018（免费）

###  声明，虽然本产品是开源。但未经本人允许擅自申请专利，将公开追究法律责任。

### 更多信息 请移步到wiki [链接地址](http://git.oschina.net/YYDeament/88ybg/wikis/A1-%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E8%B4%A6%E5%8F%B7
)





 `数据库`在本页面的`附件`中有 或者`交流群`也有
-------------------------------------------------------------------------------------------------------
### 系统介绍（图太大，请右键在新标签中打开。）

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/233041_328fd825_880593.png "云系统.png")

88ybg 又名88云报告 ，是本人所开发的一个以工作报告为目的的系统网址。



### 本系统的特点：

以Springboot为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强。2种打包方式，传统可运行的tomcat目录 以及直接jar 方式运行。主要应用技术：Security+Ehcache+quartz+swagger2+Mysql5.6.... Ehcahce 缓存,减少数据库压力quartz 自定义任务执行时间。在线开放API文档 模块，一览系统所有的功能，生成在线API文档,并且可以调试。多维度监控系统，包含sql 监控，内存监控和管理，cpu监控，缓存管理，并发监控，磁盘监控，线程监控和管理，session监控，java 开销监控 等


系统运行需求：当前所有模块引用时，需要700M 以上的空余内存（不包含mysql).

-----------------------------------------------------------------------------------------------------------------

### 所用技术


当前软件版本：`1.5.4`


| 搭配环境      | 版本 |
| --------- | -----:|
| java  | 8 |
| Maven     |   3.3.9 |
| eclipse      |    neon2 |
| mysql |    5.6+|
| svn 插件 | eclipse SVN插件  |
| svn 工具 | tortoiseSVN  |
| Maven镜像 | t阿里云的maven镜像  |



--------------------------------------------

| 技术选型      | 版本 |  描述 | 版本新增|是否涉及数据库|
| ---------   | ----- | ----- | -----  | -----:|
| uflo     |   | 一种工作流引擎| `1.5版本新增`|是|
| ureport      |  | 自定义报表| `1.5版本新增`|是|
| urule      |   | 规则报表| `1.5版本新增`||
| spring boot    | 1.5.7 | |1.3版本之前||
| spring         |    | |1.3版本之前||
| springjdbc    |    | 数据库操作模板|1.3版本之前||
| springmvc    |    | 视图层|1.3版本之前||
|  spring social       |    |社交登陆 |1.4版本新增|是|
| ehcache |   | 缓存|1.3版本之前||
| spring session | |session |1.4版本新增||
| spring security | |权限框架 |1.3版本之前|是|
| quartz| | 定时任务|1.3版本之前|是|
| freemarker | 2.X|模板引擎 |1.3版本之前||
| swagger2| 2.X| 在线API|1.3版本之前||
| activiti| 5.22.0 | 工作流|1.3版本之前|是|
| vue.js| 2.X| 前端JS|1.3版本之前||
| Jquery|   | 前端JS|1.3版本之前||
| qrcode|   | 二维码|1.3版本之前||
| layerui|   | 界面|1.3版本之前||
| adminlte|   | 界面|1.3版本之前||
| kaptche|   | 验证码|1.3版本之前||
| druid|   | 数据库连接池|1.3版本之前||
| javamail|   | 邮箱发送|1.3版本之前||
| mysql|  5.6 | 数据库|1.3版本之前||
| poi|  | excel 操作|1.3版本之前||
| javamelody|  | 系统监控|1.3版本之前||
| https|  | 传输安全（可选是否集成）|1.3版本之前||
| jwt|  | （未使用）|1.3版本之前||
| oss|  | 云存储|1.3版本之前|是|
| sdk-dysmsapi |  | 阿里云短信（未使用）|1.3版本之前||
| spring-boot-admin |  | 系统监控|1.3版本之前||
| jdk       |  8 | |1.3版本之前||
| maven      |  3.3.9 | | 1.3版本之前||


--------------------------------------------


开发注意事项：
1.    maven 要使用阿里镜像。maven 版本越高越好 3.3 以上


2.    1.5版本以后 需要在D盘创建一个叫 `repo` 的文件夹,否则无法启动 修改文件的路径在urule 模块 的config.properties 文件。




------------------------------------

### `如何打包上线`。


1.5.4版本之后的打包方式  不再使用 打包成一个jar 的形式，也不在使用tomcat 目录形式打包。而是使用lib 外置模块方式打包。这个更加贴近模块化的开发思想。

如何打包？ 

1.先编译您的项目  单击quanming_admin 模块 run as 如下图


![输入图片说明](https://gitee.com/uploads/images/2017/1015/152101_a579f13f_880593.png "屏幕截图.png")

![输入图片说明](https://gitee.com/uploads/images/2017/1015/152250_19b6b979_880593.png "屏幕截图.png")

执行 compile 命令


2.重复以上操作。但是compile 命令 改成 package.

3.执行打包命令后 在 main 模块下面有一个压缩包。如果没有请单击target 目录 F5刷新

![输入图片说明](https://gitee.com/uploads/images/2017/1015/152346_4fb4f76d_880593.png "屏幕截图.png")

解压 main-1.0.0.exe-distribution.tar 就是网站部署的目录。 里面有 bat文件。双击就是运行网站。即时运行。也有.sh liunix 运行。
如果不喜欢 还可以直接复制里面的命令 到 命令行界面上。这种方式  可以方便修改配置文件。模块替换上线。


打包后目录结构

![输入图片说明](https://gitee.com/uploads/images/2017/1015/153043_179ec88a_880593.png "屏幕截图.png")


![输入图片说明](https://gitee.com/uploads/images/2017/1015/152756_e0944e2c_880593.png "屏幕截图.png")


![输入图片说明](https://gitee.com/uploads/images/2017/1015/152850_6abf7502_880593.png "屏幕截图.png")


![输入图片说明](https://gitee.com/uploads/images/2017/1015/153329_60c29628_880593.png "屏幕截图.png")


运行脚本 运营网站

bat 是windows 执行

sh 是liunix 执行




-------------------------------------------------

###  `系统内部界面` 


![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225615_5146cdb0_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225544_14b5665b_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225638_07386920_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225650_5353ad68_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225702_c1400c23_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225714_3760c3e3_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225723_0798b4ec_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225732_15c2e9f4_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225742_70072048_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225759_4cc74341_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225807_4c68d67b_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225815_75940daf_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225836_bdffff30_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225846_798dbb7f_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225854_abf77003_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225914_80ee5dcc_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225927_228435f0_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225936_e30e8fd8_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/225950_5564f631_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/230013_38075b34_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/230024_ee2ea759_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/230051_7858e0a5_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/230101_25ac01e1_880593.png "屏幕截图.png")

![输入图片说明](https://git.oschina.net/uploads/images/2017/1009/230110_0b1ca8cb_880593.png "屏幕截图.png")
