package com.unistart.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    final ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	    converter.setObjectMapper(objectMapper);
	    converters.add(converter);
	    super.configureMessageConverters(converters);
	}
}
