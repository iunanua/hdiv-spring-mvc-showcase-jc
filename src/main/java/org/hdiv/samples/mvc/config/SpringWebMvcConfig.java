package org.hdiv.samples.mvc.config;

import org.hdiv.web.multipart.HdivCommonsMultipartResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({ "org.hdiv.samples.mvc.controllers" })
@EnableWebMvc
public class SpringWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/welcome.html");
		registry.addViewController("/welcome.html").setViewName("welcome");
		registry.addViewController("/attacks/attacks.html").setViewName("/attacks/attacks");
		registry.addViewController("/secure/attacks.html").setViewName("/attacks/attacks");
		registry.addViewController("/login.html").setViewName("/login");
		registry.addViewController("/authenticated/info.html").setViewName("/authenticated/info");
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		HdivCommonsMultipartResolver resolver = new HdivCommonsMultipartResolver();
		return resolver;
	}

}
