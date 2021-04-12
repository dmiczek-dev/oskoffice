package pl.kursant.oskoffice.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity 
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("SELECT login, password, enabled FROM public.users WHERE login = ?")
            .authoritiesByUsernameQuery("SELECT login, role_name FROM access WHERE login = ?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_INSTRUCTOR > ROLE_USER");
        return roleHierarchy;
    }
    
    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .expressionHandler(webExpressionHandler())
                .antMatchers("/kursant/**").hasRole("USER")
                .antMatchers("/instruktor/**").hasRole("INSTRUCTOR")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
            .headers()
                .frameOptions().disable()
                .and()
            .csrf().disable();
    }
    
}
