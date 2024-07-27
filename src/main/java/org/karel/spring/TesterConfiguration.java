package org.karel.spring;

import org.karel.karel.compiler.Compiler;
import org.karel.karel.tester.Tester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CompilerConfiguration.class)
public class TesterConfiguration {

    @Bean
    public Tester tester(Compiler compiler){
        return new Tester(compiler);
    }

}
