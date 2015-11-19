package br.com.curiosity.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import br.com.curiosity.model.Probe;

@Configuration
public class ProbeFactory {

	@Bean
	@Scope("prototype")
	public Probe probe() {
		Probe probe = new Probe();
		return probe;
	}

}
