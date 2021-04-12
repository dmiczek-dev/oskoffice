package pl.kursant.oskoffice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig extends WebMvcConfigurerAdapter{
    
    @Value("${machine}")
    private String machine;
    
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        return driverManagerDataSource;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(machine.equals ("linux")) {           
            registry.addResourceHandler("/images/**").addResourceLocations("file:webapps/ROOT/WEB-INF/classes/static/images/").setCachePeriod(0);
            registry.addResourceHandler("/css/**").addResourceLocations("file:webapps/ROOT/WEB-INF/classes/static/css/").setCachePeriod(0);
            registry.addResourceHandler("/js/**").addResourceLocations("file:webapps/ROOT/WEB-INF/classes/static/js/").setCachePeriod(0);
            registry.addResourceHandler("/assets/**").addResourceLocations("file:webapps/ROOT/WEB-INF/classes/static/assets/").setCachePeriod(0);
        }
        if (machine.equals("windows")) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:src/main/resources/static/images/").setCachePeriod(0);
            registry.addResourceHandler("/css/**").addResourceLocations("file:src/main/resources/static/css/").setCachePeriod(0);
            registry.addResourceHandler("/js/**").addResourceLocations("file:src/main/resources/static/js/").setCachePeriod(0);
            registry.addResourceHandler("/assets/**").addResourceLocations("file:src/main/resources/static/assets/").setCachePeriod(0);
        }
    }
}
