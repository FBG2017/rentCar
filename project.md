restful规范，前后端分离

一.
首先:选择rent  整体mvn clean  install
然后:选择rent-web mvn  jetty:run
注意都是1.8 如果是1.7 就会出错
二.
数据库设计
user     :user_id,username,password,role
category :category_id,name
stuff	 :stuff_id,category_id,name,deposit,rental,status
item	:item_id,user_id,stuff_id,create_time,rent_day,end_time,
          apply_time,status
三.
spring+springMVC+Mybatis
restful,前后端分离
四.
- Velocity     模板引擎，前后端分离
- Swagger2     利于restful APi接口，可在线测试
五.
web 依赖sevice---service依赖 dao---dao依赖common
这里的依赖，谁都不要去删除。会出错。
因为比如dao里面就引用了common里的工具类，如果去掉依赖，就找不到该类
六.
common模块的jar包依赖和父组织的jar包依赖一致，那么可以只配置父组织，不配子项目吗

如果父pom中使用的是<dependencies>....</dependencies>方式，则子pom会自动使用pom中的jar包，
如果父pom用<dependencyManagement><dependencies>....</dependencies>
</dependencyManagement>方式，则子pom不会自动使用父pom中的jar包，这时如果子pom想使用的话，就要给出groupId和artifactId，无需给出version
七.
dao模块依赖common，所以dao中依赖很少
common有junit，那么dao中junit模块可注释掉。
八.
注意：文件可以放在dao模块，也可以放在dao依赖的common模块下。不能放在service模块下。
rent-common下的.vm文件
generatorConfig_vm = "/template/generatorConfig.vm";
MybatisGeneratorUtil.class.getResource(generatorConfig_vm)。

gg包下的aa.properties
PropertiesFileUtil instance = PropertiesFileUtil.getInstance("gg/aa");
		String name = instance.get("generator.jdbc.driver");
		System.out.println(name);
		//会根据在本项目中的文件名，来读取配置文件。aa.properties=--aa
		//放在根目录下，如果新建gg包，那么就会读取不到。gg/aa.properties---gg/aa
九.
E:/redis/rent/rent-dao/src/main/resources/generatorConfig.xml
E:redis\rent\rent-dao\src\main\java\priv\thinkam\rent\dao\model\
E:redis\rent\rent-dao\src\main\java\priv\thinkam\rent\dao\mapper\
E:redis\rent\rent-service\src\main\java\priv\thinkam\rent\dao\mapper
E:redis/rent/rent-service/src/main/java/priv/thinkam/rent/service
E:redis/rent/rent-service/src/main/java/priv/thinkam/rent/service/impl
 mybatisGeneratorUtil 可以改路径的相关配置。
十.
classpath：只会到你的class路径中查找文件。
classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。

之所以mapper中的xml没被映射，是因为没被打包到jar中，或classes中
<build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

十一： 解决
Spring-config.xml中
java.lang.ClassNotFoundException: org.apache.commons.fileupload.FileItemFactory   
 
<dependency>
<groupId>commons-fileupload</groupId>
<artifactId>commons-fileupload</artifactId>
<version>1.3</version>
</dependency>

十二:
spring-service.xml 已经配置了任务调度注解，
故不需要再配置spring-task.xml，不然会报错

十三:
vm 文件是模板文件，volecity.引擎
根据generatorConfig.vm 生成 generatorConfig.xml文件。
mybatis根据genaratorConfig.xml,生成po,mapper,mapper.xml文件

根据service.vm serviceImpl.vm生成业务层。
