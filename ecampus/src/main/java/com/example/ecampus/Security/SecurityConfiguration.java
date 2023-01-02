package com.example.ecampus.Security;

import com.example.ecampus.Filters.CustomAuthenticationFilter;
import com.example.ecampus.Filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().and().formLogin();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2-console/**").permitAll();
                /*.antMatchers("/users").permitAll()
                .antMatchers("/roles").permitAll()
                .antMatchers("/sozlesmeler").permitAll();  */

        //http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll();
        http.authorizeRequests().antMatchers( "/dersler").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");

        http.authorizeRequests().antMatchers( "/users").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/users/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers( "/users/rolename").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/users/rolename/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers( "/roles").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/roles/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers( "/dersroles").hasAnyAuthority("ROLE_ADMIN","ROLE_ENSTITU_YONETICI","ROLE_FAKULTE_YONETICI");
        http.authorizeRequests().antMatchers( "/dersroles/**").hasAnyAuthority("ROLE_ADMIN","ROLE_ENSTITU_YONETICI","ROLE_FAKULTE_YONETICI");
        http.authorizeRequests().antMatchers( "/bolumroles/**").hasAnyAuthority("ROLE_ADMIN","ROLE_ENSTITU_YONETICI","ROLE_FAKULTE_YONETICI");
        http.authorizeRequests().antMatchers( "/bolumroles").hasAnyAuthority("ROLE_ADMIN","ROLE_ENSTITU_YONETICI","ROLE_FAKULTE_YONETICI");


        http.authorizeRequests().antMatchers( "/fakulteler").hasAnyAuthority("ROLE_SISTEM_YONETICISI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/fakulteler/**").hasAnyAuthority("ROLE_SISTEM_YONETICISI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/fakulteler").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/fakulteler").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ADMIN");


        http.authorizeRequests().antMatchers(HttpMethod.GET, "/enstituler").hasAnyAuthority("ROLE_ENSTITU_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/enstituler").hasAnyAuthority("ROLE_SISTEM_YONETICISI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/enstituler/**").hasAnyAuthority("ROLE_SISTEM_YONETICISI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/enstituler/**").hasAnyAuthority("ROLE_ENSTITU_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/enstituler/**").hasAnyAuthority("ROLE_ENSTITU_YONETICI","ROLE_ADMIN");


        http.authorizeRequests().antMatchers( "/bolumler/**").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ENSTITU_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/bolumler").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ENSTITU_YONETICI","ROLE_ADMIN");

        http.authorizeRequests().antMatchers( "/dersler/**").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/dersler").hasAnyAuthority("ROLE_FAKULTE_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/dersler/**").hasAnyAuthority("ROLE_ENSTITU_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/dersler").hasAnyAuthority("ROLE_ENSTITU_YONETICI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/dersler").hasAnyAuthority("ROLE_OGRENCI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/dersler/**").hasAnyAuthority("ROLE_OGRENCI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/dersler").hasAnyAuthority("ROLE_OGRENCI_ISLERI","ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/dersler/**").hasAnyAuthority("ROLE_OGRENCI_ISLERI","ROLE_ADMIN");



        http.authorizeRequests().antMatchers("/derskayitlari").hasAnyAuthority("ROLE_OGRENCI","ROLE_ADMIN","ROLE_OGRENCI_ISLERI");
        http.authorizeRequests().antMatchers("/derskayitlari/**").hasAnyAuthority("ROLE_OGRENCI","ROLE_ADMIN","ROLE_OGRENCI_ISLERI");
        //http.authorizeRequests().antMatchers("/derskayitlari").hasAnyAuthority("ROLE_OGRENCI_ISLERI","ROLE_ADMIN");
        //http.authorizeRequests().antMatchers("/derskayitlari/**").hasAnyAuthority("ROLE_OGRENCI_ISLERI","ROLE_ADMIN");





        http.authorizeRequests().anyRequest().authenticated();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilter(new CustomAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
