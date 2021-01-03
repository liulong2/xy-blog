package com.xybbz.knife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

@Configuration
//@EnableSwagger2WebMvc
@EnableOpenApi
public class Knife4jConfig {
    private static final String SPLIT = ",";
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("liu", "www.xybbz.com", "xxxx"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("3.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(basePackage("com.xybbz.auth.controller,com.xybbz.generator.modules.controller"))
                .paths(PathSelectors.any())
                .build()
                /*.groupName("3.X1版本")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xybbz.generator.modules.controller"))
                .paths(PathSelectors.any()).build()*/;
        return docket;
    }


    public static Predicate<RequestHandler> basePackage(String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)).orElse(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            String[] split = basePackage.split(SPLIT);
            for (String pack :split){
                boolean b = ClassUtils.getPackageName(input).startsWith(pack);
                if (b) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return ofNullable(input.declaringClass());
    }
}
