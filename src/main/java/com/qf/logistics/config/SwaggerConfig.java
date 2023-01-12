package com.qf.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())                     //指定说明书的"封面"信息
                .select()                                   //监控哪些接口
                .apis(RequestHandlerSelectors.basePackage("com.qf.logistics.controller"))  //指定文档扫描范围
                .paths(PathSelectors.any())                 //指定生成api的路径
                .build()
                .globalOperationParameters(getParameterList());
        return docket;
    }

    public List<Parameter> getParameterList(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> list = new ArrayList<>();
        final Parameter parameter = parameterBuilder.name("token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        list.add(parameter);
        return list;
    }

//    public ApiInfo getApiInfo(){
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("图书管理系统接口文档")
//                .description("次文档详细描述了****")
//                .version("v1.2")
//                .contact(new Contact("", "", ""))
//                .build();
//        return apiInfo;
//    }

}