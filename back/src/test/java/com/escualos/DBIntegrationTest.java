package com.escualos;

import com.escualos.mongo.MongoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan(basePackageClasses = MongoConfig.class)
})
public class DBIntegrationTest extends IntegrationTest{

}
