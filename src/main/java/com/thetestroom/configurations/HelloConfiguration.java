package com.thetestroom.configurations;

import com.thetestroom.beans.Hello;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Bean
    public Hello hello() {
        return new Hello();
    }

}
