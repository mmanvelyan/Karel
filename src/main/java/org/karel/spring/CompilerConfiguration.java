package org.karel.spring;

import org.karel.karel.compiler.Compiler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompilerConfiguration {

    @Bean
    public Compiler compiler(){
        return new Compiler();
    }

}
