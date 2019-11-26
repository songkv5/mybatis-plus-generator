package com.ws.tool.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ws.tool.mybatisplus.config.DataSource;
import com.ws.tool.mybatisplus.config.OutputConfig;
import com.ws.tool.mybatisplus.constants.DbTypeEnum;
import com.ws.tool.mybatisplus.constants.ErrorCodeConstant;
import com.ws.tool.mybatisplus.engine.CustomerFreemarkerTemplateEngine;
import com.ws.tool.mybatisplus.exception.BaseException;
import com.ws.tool.mybatisplus.exception.DbConfigException;
import com.ws.tool.mybatisplus.helper.GeneratorHelper;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月07日 22:22
 */
@Mojo(name = "generate")
public class MybatisPlusCodeGenerator extends AbstractMojo {
    private static final String FILE_SEPARATOR = File.separator;
    /**
     * 引入插件的项目信息
     */
    @Parameter(defaultValue = "${project}")
    private MavenProject project;
    @Parameter
    private DataSource dataSource;
    @Parameter
    private OutputConfig outputConfig;
    /**
     * 要生成代码的表
     */
    @Parameter(property = "tables")
    private String[] tables;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            File basedir = project.getBasedir();
            String absPath = basedir.getAbsolutePath();
            getLog().info("输出配置：" + outputConfig.toString());
            checkDbConfig(dataSource);

            AutoGenerator generator = new AutoGenerator();
            generator.setDataSource(dsc());
            generator.setPackageInfo(pkgc());
            generator.setCfg(injectionConfig());
            generator.setGlobalConfig(globalConfig());
            generator.setTemplateEngine(new CustomerFreemarkerTemplateEngine(getLog()));
            generator.setTemplate(templateConfig());
            generator.setStrategy(strategy());
            generator.execute();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BaseException) {
                throw e;
            }
        }
    }

    private TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        boolean createController = outputConfig.getCreateController();
        boolean createService = outputConfig.getCreateService();
        if (!createController) {
            templateConfig.setController(null);
        }
        if (!createService) {
            templateConfig.setServiceImpl(null);
            templateConfig.setService(null);
        }
        return templateConfig;
    }

    /**
     * 数据库连接字符串
     * @return
     */
    private String getUrl() {
        String type = dataSource.getType();
        DbTypeEnum dbType = DbTypeEnum.getByName(type);
        if (dbType == DbTypeEnum.UNKNOWN) {
            throw new DbConfigException(ErrorCodeConstant.DB_TYPE_NOT_SUPPORT_ERROR_CODE, "暂不支持数据库类型：" + dbType);
        }
        StringBuilder sb = new StringBuilder();
        switch (dbType) {
            case MYSQL: {
                sb.append("jdbc:mysql://").append(dataSource.getIp())
                        .append(":")
                        .append(dataSource.getPort())
                        .append("/")
                        .append(dataSource.getDb())
                        .append("?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
                break;
            }
            default:break;
        }
        return sb.toString();
    }
    public void checkDbConfig(DataSource dataSource) {
        if (this.dataSource.getIp() == null) {
            throw new DbConfigException(ErrorCodeConstant.DB_NO_IP_ERROR_CODE, "缺少数据库ip或host。配置路径：configuration>dataSource>ip");
        }
        if (this.dataSource.getDb() == null) {
            throw new DbConfigException(ErrorCodeConstant.DB_NO_DB_ERROR_CODE, "缺少数据库名称。配置路径：configuration>dataSource>db");
        }
        if (this.dataSource.getUser() == null) {
            throw new DbConfigException(ErrorCodeConstant.DB_NO_USER_ERROR_CODE, "缺少数据库名称。配置路径：configuration>dataSource>user");
        }
        if (this.dataSource.getPwd() == null) {
            throw new DbConfigException(ErrorCodeConstant.DB_NO_PWD_ERROR_CODE, "缺少数据库名称。配置路径：configuration>dataSource>pwd");
        }
    }

    /**
     * 代码生成策略
     * @return
     */
    private StrategyConfig strategy() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(outputConfig.getRest());
//        strategy.setControllerMappingHyphenStyle(false);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        // 要生成哪些表的code
        strategy.setInclude(tables);
        String tPrefix = outputConfig.gettPrefix();
        if (tPrefix != null) {
            strategy.setTablePrefix(tPrefix);
        }
        strategy.setEntityColumnConstant(outputConfig.getEntityColumnConstant());
        return strategy;
    }
    private InjectionConfig injectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String templatePath = "/templates/mapper.xml.ftl";
        File basedir = project.getBasedir();
        String basePath = basedir.getAbsolutePath();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                /** 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！*/
                StringBuilder sb = new StringBuilder();
                sb.append(basePath);
                String xmlPath = outputConfig.getXmlPath();
                String module = outputConfig.getModule();
                if (!xmlPath.startsWith(FILE_SEPARATOR)) {
                    sb.append(FILE_SEPARATOR);
                }
                sb.append(xmlPath);
                if (module != null) {
                    sb.append(FILE_SEPARATOR).append(module);
                }
                sb.append(FILE_SEPARATOR);
                sb.append(tableInfo.getEntityName()).append("Mapper");
                sb.append(StringPool.DOT_XML);
                return sb.toString();
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }
    private DataSourceConfig dsc() {
        DataSourceConfig dsc = new DataSourceConfig();
        String dbUrl = getUrl();
        dsc.setUrl(dbUrl);
        // dsc.setSchemaName("public");
        String type = dataSource.getType();
        String driver = GeneratorHelper.transferDriverClass(type);
        dsc.setDriverName(driver);
        dsc.setUsername(dataSource.getUser());
        dsc.setPassword(dataSource.getPwd());
        return dsc;
    }
    private PackageConfig pkgc() {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(outputConfig.getModule());
        pc.setParent(outputConfig.getPkg());
        String controller = outputConfig.getController();
        String service = outputConfig.getService();
        if (controller != null) {
            pc.setController(controller);
        }
        if (service != null) {
            pc.setService(service);
            pc.setServiceImpl(service.concat(".impl"));
        }
        return pc;
    }
    private GlobalConfig globalConfig() {
        File basedir = project.getBasedir();
        String basePath = basedir.getAbsolutePath();
        GlobalConfig globalConfig = new GlobalConfig();
        String jcPath = outputConfig.getJcPath();
        StringBuilder oppath = new StringBuilder();
        oppath.append(basePath);
        if (!jcPath.startsWith(FILE_SEPARATOR)) {
            oppath.append(FILE_SEPARATOR);
        }
        oppath.append(jcPath);
        globalConfig.setOutputDir(oppath.toString());
        globalConfig.setAuthor(System.getProperty("user.name"));
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(outputConfig.getOverride());
        globalConfig.setControllerName(null);
        globalConfig.setServiceName(null);
        globalConfig.setServiceImplName(null);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setBaseResultMap(outputConfig.getResultMap());
        return globalConfig;
    }
}