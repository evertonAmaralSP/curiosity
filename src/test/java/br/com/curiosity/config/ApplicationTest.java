package br.com.curiosity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.curiosity.utils.builder.WalkBuilder;

@Configuration
@ComponentScan("br.com.curiosity")
public class ApplicationTest {

	@Bean(name = "walkBuilder")
	public WalkBuilder getWalkBuilder() {
		return WalkBuilder.getInstance();
	}
	
	
}
