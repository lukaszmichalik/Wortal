package com.okta.Wortal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.filter.CorsFilter

@SpringBootApplication
class WortalApplication

	fun main(args: Array<String>) {
		runApplication<WortalApplication>(*args)
	}

	// Fix the CORS errors
	@Bean
	fun simpleCorsFilter(): FilterRegistrationBean<*> {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.allowCredentials = true
		// *** URL below needs to match the Vue client URL and port ***
		config.allowedOrigins = listOf("http://localhost:3030")
		config.allowedMethods = listOf("GET", "POST", "OPTIONS", "PUT", "DELETE", "HEAD")
		config.allowedHeaders = listOf("Access-Control-Allow-Origin")
		source.registerCorsConfiguration("/**", config)
		val bean: FilterRegistrationBean<*> = FilterRegistrationBean(CorsFilter(source))
		bean.order = Ordered.HIGHEST_PRECEDENCE
		return bean
	}
