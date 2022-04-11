package com.adamin.demo.config;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @Classname JacksonConfig
 * @Description Jackson自动配置，把一些null指定为默认值
 * @Date 2022/4/10 12:16
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Configuration
public class JacksonConfig {
    private static final Logger LOGGER= LoggerFactory.getLogger(JacksonConfig.class);
    @Bean
public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
    ObjectMapper mapper = builder.createXmlMapper(false)
            .build();



    mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String fieldName = gen.getOutputContext().getCurrentName();
            LOGGER.info(fieldName+"----");
            try {
                //反射获取字段类型
                Field field = gen.getCurrentValue().getClass().getDeclaredField(fieldName);
                if (CharSequence.class.isAssignableFrom(field.getType())) {
                    //字符串型空值""
                    gen.writeString("");
                    return;
                } else if (Collection.class.isAssignableFrom(field.getType())) {
                    //列表型空值返回[]
                    gen.writeStartArray();
                    gen.writeEndArray();
                    return;
                } else if (Map.class.isAssignableFrom(field.getType())) {
                    //map型空值 或者 bean对象 返回{}
                    gen.writeStartObject();
                    gen.writeEndObject();
                    return;
                }else if(Number.class.isAssignableFrom(field.getType())){
                    gen.writeNumber(0);
                }
            } catch (NoSuchFieldException ignored) {
            }

//            gen.writeString("");
        }
    });
    return  mapper;
}
}
