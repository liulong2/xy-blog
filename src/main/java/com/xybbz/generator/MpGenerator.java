package com.xybbz.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.xybbz.generator.PageConstant.BASIS;

/**
 * 简陋的代码生成器
 * @author 刘梦龙
 */
@Data
public class MpGenerator {

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库链接驱动
     */
    private String driverClassName;

    /**
     * 创建路径
     */
    private String dirPath;

    /**
     * 设置表名
     */
    private String tableName;
    /**
     * 表前缀
     */
    private String prefix;
    /**
     * 作者
     */
    private String author;

    /**
     * 代码生成的包名
     */
    private String packageName = "com.xybbz";


    @Transactional(rollbackFor = Exception.class)
    public void generateByTables() throws Exception {

        if (StrUtil.isEmpty(tableName)) {
            throw new Exception("表明为空");
        }
        /**
         * 代码生成器
         */
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        /**
         * 全局配置
         */
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        GlobalConfig globalConfig = new GlobalConfig();
        //设计主键类型
        globalConfig.setIdType(IdType.ASSIGN_ID);
        //生成文件的输出目录
        // TODO: 2020/4/5  不能修改为项目根目录路径 不然会替换当前项目修改文件
//        String projectPath = System.getProperty("user.dir");
//        String projectPath = System.getProperty("auth");
        globalConfig.setOutputDir(getOutputDir());
        //Author设置作者
        globalConfig.setAuthor(author);
        //是否覆盖文件
        globalConfig.setFileOverride(true);
        //生成后打开文件
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);


        //if (!serviceNameStartWithI) {
        // TODO: 2020/4/5 自定义文件名
        globalConfig.setMapperName("%sDAO");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
//        globalConfig.setEntityName("%sEntity");
        globalConfig.setXmlName("%sMapper");
        //}
        mpg.setGlobalConfig(globalConfig);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        globalConfig.setMapperName("%sDAO");
//        globalConfig.setXmlName("%sMapper");
//        globalConfig.setServiceName("MP%sService");
//        globalConfig.setServiceImplName("%sServiceImpl");
//        globalConfig.setControllerName("%sController");
//        globalConfig.setEntityName("%sEntity");

        /**
         * 数据源配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型,默认MYSQL
        dataSourceConfig.setDbType(DbType.MYSQL);
        //自定义数据类型转换  将数据库里的字段对应到java字段上
        dataSourceConfig.setTypeConvert(new MySqlTypeConvertConfig());
        // TODO: 2020/4/5 数据库
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverClassName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        mpg.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        // TODO: 2020/4/5 修改输出包名
        pc.setParent(prefix);
        mpg.setPackageInfo(pc);


        /**
         * 模板
         */

        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tableName);
//        strategy.setSuperMapperClass("tes.com.mybatis.entity");
        //实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //设置自定义继承的Entity类全称，带包名
        strategy.setSuperEntityClass("com.xybbz.utils.BaseEntity");
        //设置自定义继承的Controller类全称，带包名
        //strategy.setSuperControllerClass("com.wxgzh.config.AbstractHttp");
        //strategy.setSuperServiceImplClass();
        //设置自定义基础的Entity类，公共字段  解开后typeid注解不存在

        BASIS.forEach(strategy::setSuperEntityColumns);

        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀pc.getModuleName() +
        // TODO: 2020/4/5 删除第一个下划线之前的字母
        strategy.setTablePrefix(prefix);
        mpg.setStrategy(strategy);


        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                //可以用来往前端模板中写东西
            }
        };
        /**
         * 自定义输出配置
         */
        //如果模板引擎是 freemarker
        String templatePath = "/templates/entityDTO.java.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！ + pc.getModuleName()
                return getOutputDir() + "/" + packageName.replace("/", ".") + "/dto"
                        + "/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });

        String voph = "/templates/entityVO.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(voph) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！ + pc.getModuleName()
                return getOutputDir() + "/" + packageName.replace("/", ".") + "/vo"
                        + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 配置模板
         */
//        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // TODO: 2020/4/5 自定义模板  不使用的话会默认使用mybatis-plus的模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        /*templateConfig.setEntity("/templates/entity.java");
        templateConfig.setService("/templates/service.java");
        templateConfig.setController("/templates/controller.java");
        templateConfig.setServiceImpl("/templates/mapper.xml");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
*/

        //templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        /**
         * 策略配置
         */


        mpg.execute();

//        System.out.println("**********************************************************");
//        System.out.println("/\n/\n/\n/");
//        System.out.println("/************************代码生成结束**********************/");
//        System.out.println("/\n/\n/\n/");
//        System.out.println("**********************************************************");
//        System.exit(0);
    }

    private String getOutputDir() {
        return (StrUtil.isBlank(dirPath) ? System.getProperty("user.dir") + "/xy" : dirPath) + "/src/main/java/";
    }
}
