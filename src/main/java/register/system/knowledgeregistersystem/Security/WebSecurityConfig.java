package register.system.knowledgeregistersystem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import register.system.knowledgeregistersystem.Models.Admin;
import register.system.knowledgeregistersystem.data.AdminRepo;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public WebSecurityConfig(AdminRepo adminRepo)
    {
        Admin admin = new Admin();
        admin.setUsername("Janusz");
        //admin.setPassword("Janusz123",passwordEncoder);
        //admin.setPassword(passwordEncoder.encode("Janusz123"));
        admin.setPassword("Janusz123");
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);

        adminRepo.save(admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.headers().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/admin")
                .and()
        .httpBasic()
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/");

        //Ponoć to pozwala na używanie cssów na stronie
        // http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        //albo to
        //http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
        /*http.csrf().disable();
        http.headers().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/mysql").permitAll()
                .antMatchers("/spring").permitAll()
                .antMatchers("/patterns").permitAll()
                .antMatchers("/algorithms").permitAll()
                .antMatchers("/htmlcss").permitAll()
                .antMatchers("/hibernate").permitAll()
                .antMatchers("/admin/addAdmin").hasAnyAuthority("ADMIN")
                .antMatchers("/admin/events").hasAnyAuthority("ADMIN")
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/admin").permitAll()
                .and()
                .logout().logoutUrl("/admin/logout").permitAll()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;*/

    }
}
