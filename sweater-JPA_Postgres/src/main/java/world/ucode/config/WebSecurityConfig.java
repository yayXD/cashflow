package world.ucode.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import world.ucode.service.RegistrationService;
//import world.ucode.service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private RegistrationService registrationService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable();
//        http.authorizeRequests().antMatchers(HttpMethod.POST,"/registration").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET,"/greeting","/registration").permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        http
                .authorizeRequests()
                .antMatchers("/","/greeting", "/registration", "/logn").permitAll()

               .mvcMatchers(HttpMethod.POST, "/logn").permitAll()

                .anyRequest().authenticated()
                .and()

//                .csrf().disable()

                .formLogin()
                .loginPage("/logn")

//               .loginProcessingUrl("/logn")
//                .usernameParameter("login")
//                .passwordParameter("password")
//                //.defaultSuccessUrl()

                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
       // auth.userDetailsService(registrationService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select login, password, active from registration where login=?")
                .authoritiesByUsernameQuery("select r.login, ur.roles from registration r inner join user_role ur on r.id = ur.user_id where r.login=?");

    }
}
//        @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
////                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//}