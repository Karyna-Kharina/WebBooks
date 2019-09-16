package web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"web"})
@Import(DataBaseConfiguration.class)
public class AppConfig {
}
