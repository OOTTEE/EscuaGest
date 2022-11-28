package com.escualos.core;

import com.escualos.mongo.MongoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.escualos.core.domain")
@ComponentScans({
        @ComponentScan(basePackageClasses = MongoConfig.class)
})
public class IntegrationTest {

}
