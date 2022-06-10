package ir.sadeqcloud.BontechEvaluationProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
/**
 *  component-based security configuration
 */
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity.authorizeRequests()
                 .antMatchers("/swagger-ui/**").permitAll()
                 .antMatchers("/swagger-ui.html/**").permitAll()
                 .antMatchers("/v3/api-docs/**").permitAll()
                 .antMatchers("/ADMIN/**").hasRole("ADMIN")
                 .anyRequest().authenticated();
         httpSecurity.httpBasic();
         httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Profile("!cloud")
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Profile("cloud")
    @Bean
    public PasswordEncoder passwordEncoderForCloudEnvironment(){
        return new BCryptPasswordEncoder();
    }

}
