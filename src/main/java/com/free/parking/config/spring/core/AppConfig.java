package com.free.parking.config.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by veladii on 2/12/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "com.free.parking")
public class AppConfig extends WebMvcConfigurerAdapter {
}
