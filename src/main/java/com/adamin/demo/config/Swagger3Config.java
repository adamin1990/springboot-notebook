package com.adamin.demo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Swagger3Config
 * @Description Swagger3自动配置
 * @Date 2022/4/10 16:11
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@EnableKnife4j
@Configuration
@EnableOpenApi

public class Swagger3Config {
   @Bean
    public Docket createRestApi(){
         return new Docket(DocumentationType.OAS_30)
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                 .paths(PathSelectors.any())
                 .build()
                 .globalRequestParameters(getGlobalRequestParameters())
                 .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                 .globalResponses(HttpMethod.POST, getGlobalResponseMessage());

    }

    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("未找到资源").build());
        return responseList;

    }

    /**
     * full common request params
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("authentication")
                .description("用户token")
                .required(true)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;

    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("adam doc")
                .description("description: email: im_jmer@foxmail.com")
                .contact(new Contact("adam","https://lixiaopeng.top"
                ,"im_jmer@foxmail.com"))
                .version("1.0.0")
                .build();

    }
}
