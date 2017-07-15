                                                    88ybg 网站 源码开源
由于服务器内存有限，正式网址当前使用旧版本，大家不要去访问了。实在想去访问旧版，请加群联系本人

QQ群：314658875

 声明，虽然本产品是开源。但未经本人允许擅自申请专利，将公开追究法律责任。
-------------------------------------------------------------------------------------------------------
### 系统介绍


88ybg 又名88云报告 ，是本人所开发的一个以工作报告为目的的系统网址。

88云报告系统开发状态：

![输入图片说明](https://git.oschina.net/uploads/images/2017/0715/203522_7a649abb_880593.png "云系统(4).png")



完成了 swagger2 的在线开放API 模块，随意随意 调试系统任意接口 http://localhost:8080/swagger-ui.html 
![输入图片说明](https://git.oschina.net/uploads/images/2017/0708/090800_dce0367c_880593.png "在这里输入图片标题")



### 本系统的特点：

以Springboot为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强。2种打包方式，传统可运行的tomcat目录 以及直接jar 方式运行。主要应用技术：Security+Ehcache+quartz+swagger2+Mysql5.6.... Ehcahce 缓存,减少数据库压力quartz 自定义任务执行时间。在线开放API文档 模块，一览系统所有的功能，生成在线API文档,并且可以调试。多维度监控系统，包含sql 监控，内存监控和管理，cpu监控，缓存管理，并发监控，磁盘监控，线程监控和管理，session监控，java 开销监控 等


系统运行需求：当前所有模块引用时，需要700M 以上的空余内存（不包含mysql).


### 代码结构

![输入图片说明](https://git.oschina.net/uploads/images/2017/0708/212856_12884395_880593.png "在这里输入图片标题")

-----------------------------------------------------------------------------------------------------------------

### 所用技术

![输入图片说明](https://git.oschina.net/uploads/images/2017/0708/084549_c46f2786_880593.png "在这里输入图片标题")


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

--------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------



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

### 开始开发

0.前期工作


JDK1.8  下载绿色版解压，请配置好环境变量或使用eclipse 自己安装jdk 

1.下载eclipse 本人使用![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/173525_6e20cf80_880593.png "在这里输入图片标题")版本  可以下载最新的

构建maven 环境 

0.1 下载apache-maven-3.3.9 以上  解压 下载地址：http://maven.apache.org/download.cgi


0.2 配置maven 环境变量
![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/173008_ec1ba1ed_880593.png "在这里输入图片标题")



0.3 安装 mysql 5.6

 **0.3.1 数据库默认名称 quanmin_sys 数据库编码Utf-8** 


0.4 导入sql文件： ###下载数据库：

![输入图片说明](https://git.oschina.net/uploads/images/2017/0710/003757_0439f1a5_880593.png "在这里输入图片标题")

 在附件上 请留意 数据库更新时间-2017年7月10日00:37:51
  
-------------------------------------------------------------------------------------------------------

eclipse 构建maven 环境

配置仓库路径

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/173318_115b827f_880593.png "在这里输入图片标题")




1.用eclipse 导入项目

下载zip 解压，右键导入
![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/171946_f1e7e9b1_880593.png "在这里输入图片标题")

以maven project 方式导入项目

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/172016_9d516f6b_880593.png "在这里输入图片标题")

导入后如下目录

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/172120_c683210d_880593.png "在这里输入图片标题")

左击![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/172153_abc2baec_880593.png "在这里输入图片标题")

然后输入 ALT+F5 刷新项目

等待 build workplace 结束



编译项目

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/190503_3aac13da_880593.png "在这里输入图片标题")

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/190634_5a7b6969_880593.png "在这里输入图片标题")
点击 run 即可。



----------------------------------------------------------------------------------------------------------------------


### 如何运行？
运行 main 模块的App.java
![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/185545_2cf3b81b_880593.png "在这里输入图片标题")

访问地址 localhost:8080 或者http://localhost:8080/common/login_do/login.do

如何修改 网址运行端口
![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/190209_81145121_880593.png "在这里输入图片标题")
红线位置修改端口

---------------------------------------------------------------------------------------------------------------------
###如何部署？
 **
 本目录的pom ** 
![输入图片说明](https://git.oschina.net/uploads/images/2017/0702/230202_c02ad6e9_880593.png "在这里输入图片标题")


pom.xml  打包方式  可执行jar 打包，本系统默认方式

pom-tomcat.xml 打包成tomcat 目录的pom 

pom-jar开发.xml jar形式部署的pom


 **根据自己所需使用不同的pom** 


A.传统tomcat 服务器 的目录运行

pom-tomcat.xml 的内容替换到pom.xml即可

执行项目打包 右键quanmin_admin 模块。

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/195535_fa8cd813_880593.png "在这里输入图片标题")

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/195623_2989a7eb_880593.png "在这里输入图片标题")

点击run即可

可运行的webapp目录在 main模块的webapp 目录，复制到tomcat 部署目录 或者修改 tomcat server.xml指向改目录即可



B.jar 方式运行，打包后

同上，只是pom 用原有的即可。

打包出来的项目就是可以执行的jar.


（看不到日志？进入该run.bat的目录   用run.bat 运行，右键可编辑相对路径更改%cd%成自己绝对路径 ）

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/195819_10d77f67_880593.png "在这里输入图片标题")

![输入图片说明](https://git.oschina.net/uploads/images/2017/0701/195844_0b6975e1_880593.png "在这里输入图片标题")



cmd 中文乱码解决方案 

1. win+R 输入regedit 进入注册表 
2. 找到 HKEY_CURRENT_USER\Console\%SystemRoot%_system32_cmd.exe 如果 该项下已存在CodePage项，则把值改为十进制”65001”；如果不存在，在该项下新建一个 DWORD（32位值），命名为“CodePage”，值设为“65001” 
3. 重启cmd后生效 
4. 对于Power shell修改同样，只需在第2步修改 
%SystemRoot%_system32_WindowsPowerShell_v1.0_powershell.exe 下的项。



### 运行账号初始账号密码：  admin   ,   11111111








