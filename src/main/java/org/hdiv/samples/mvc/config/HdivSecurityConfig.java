package org.hdiv.samples.mvc.config;

import org.hdiv.config.annotation.EnableHdivWebSecurity;
import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.config.annotation.builders.SecurityConfigBuilder;
import org.hdiv.config.annotation.configuration.HdivWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHdivWebSecurity
public class HdivSecurityConfig extends HdivWebSecurityConfigurerAdapter {

	@Override
	public void configure(SecurityConfigBuilder builder) {

		builder
			.sessionExpired()
				.homePage("/").loginPage("/login.html");
	}
	
	@Override
	public void addExclusions(ExclusionRegistry registry) {

		registry.addUrlExclusions("/", "/login.html").method("GET");
		registry.addUrlExclusions("/j_spring_security_check", "/logout.html").method("POST");
		registry.addUrlExclusions("/attacks/.*");
		
		registry.addUrlExclusions("/resources/.*");
		
		registry.addParamExclusions("_csrf");
		
		registry.addParamExclusions("param1", "param2").forUrls("/attacks/.*");
	}

	@Override
	public void addRules(RuleRegistry registry) {

		registry.addRule("safeText").acceptedPattern("^[a-zA-Z0-9@.\\-_]*$");
	}

	@Override
	public void configureEditableValidation(ValidationConfigurer validationConfigurer) {

		validationConfigurer.addValidation("/secure/.*");
		validationConfigurer.addValidation("/safetext/.*").rules("safeText").disableDefaults();
	}
}
