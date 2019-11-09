# mybatis-plus-generator
mybatis-plus的代码生成器maven插件

### 插件使用方法
```xml
<plugin>
    <groupId>com.ws.tool</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>1.0.1-SNAPSHOT</version>
    <configuration>
        <!-- 数据库相关配置 -->
        <dataSource>
            <ip>127.0.0.1</ip><!-- 数据库的IP 或者 host-->
            <port>3306</port><!-- 数据库的端口-->
            <user>username</user><!-- 数据库的登录账号-->
            <pwd>pwd</pwd><!-- 数据库的登录密码-->
            <db>db_name</db><!-- 数据库名称-->
            <type>mysql</type><!-- 数据库类型。目前只支持mysql-->
        </dataSource>
        <!-- 文件输出配置 -->
        <outputConfig>
            <pkg>com.test</pkg> <!-- java文件的包名 -->
            <xmlPath>src/main/resources</xmlPath><!-- mapper.xml 文件生成的位置-->
            <jcPath>src/main/java</jcPath><!-- mapper.java 文件生成的位置-->
            <createController>false</createController><!-- 是否需要生成controller，默认生成-->
            <rest>false</rest><!-- controller中是否生成@RestController注解-->
            <createService>true</createService><!-- 是否生成service -->
            <controller>controller</controller><!-- controller 文件生成的子包名-->
            <service>service</service><!-- service 文件生成的子包名-->
            <override>true</override><!-- 重新执行时，是否覆盖原来的文件-->
            <module>module</module><!-- 模块名。作为java和xml文件的子路径-->
            <tPrefix>p1_</tPrefix><!-- 数据表前缀。生成的实体类会自动过滤前缀-->
            <resultMap>true</resultMap><!-- xml中是否生成BaseResult-->
        </outputConfig>
        <!-- 你要实生成实体的数据表-->
        <tables>
            <table>p1_table1</table>
            <table>p1_table2</table>
            <table>p1_table3</table>
        </tables>
    </configuration>
</plugin>
```
**插件运行命令**
```shell script
mvn mybatis-plus-generator:generate
```