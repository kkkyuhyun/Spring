package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@Log4j
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    // 문자셋필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        //경로별 접근 권한 지정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")//권한명 설정
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

        http.formLogin() // form 기반 로그인 활성화, 나머지는 모두 디폴트
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/");
        http.logout()
                .logoutUrl("/security/logout")
                .invalidateHttpSession(true)
                // 로그아웃 설정 시작
                // POST: 로그아웃 호출 url
                // 세션 invalidate
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout"); // GET: 로그아웃 이후 이동할 페이지
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        log.info("configure............");
        //in memory user 정보 삭제 -> UserDetailsService 와 같이 사용 불가
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        /*auth.inMemoryAuthentication()
                .withUser("admin")
        //        .password("{noop}1234")
                .password("$2a$10$V1.P6ZPJbWH92XS7/E8nnOQSZBjBT0S3hnyl4IGYSYnznd8MQmYdW")
                .roles("ADMIN","MEMBER");
        auth.inMemoryAuthentication()
                .withUser("member")
                // .password("{noop}1234")
                .password("$2a$10$4jn6JyrborfFeA9aXhy0iOG/kpn4AazASTd6bqyroOdVu51Dn3X72")
                .roles("MEMBER"); // ROLE_MEMBER*/
    }

}

/*
package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Lombok의 @Slf4j를 사용
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/admin").hasRole("ADMIN")
                .antMatchers("/security/member").hasAnyRole("MEMBER", "ADMIN");

        http.formLogin()
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/", true); // true로 설정하여 항상 이 URL로 리디렉션

        http.logout()
                .logoutUrl("/security/logout")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me", "JSESSIONID")
                .logoutSuccessUrl("/security/logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$V1.P6ZPJbWH92XS7/E8nnOQSZBjBT0S3hnyl4IGYSYnznd8MQmYdW") // BCrypt 암호화된 비밀번호
                .roles("ADMIN", "MEMBER");

        auth.inMemoryAuthentication()
                .withUser("member")
                .password("$2a$10$4jn6JyrborfFeA9aXhy0iOG/kpn4AazASTd6bqyroOdVu51Dn3X72") // BCrypt 암호화된 비밀번호
                .roles("MEMBER");
    }
}

 */