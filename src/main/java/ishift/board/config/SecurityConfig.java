package ishift.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity // 시큐리티 필터가 등록됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private MemberDetailService memberDetailService;

    @Bean // PasswordEncoder를 Bean으로 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override // 받아온 password를 같은 방식으로 암호화해서 DB에 있는 암호화 비밀번호랑 비교
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable() // CSRF 토큰 비활성화
        .authorizeRequests()  // 권한 요청
            .antMatchers("/", "/member/**", "/js/**") // 특정 리소스들에 대한 권한 설정
            .permitAll() // 설정한 리소스들은 인증절차 없이 허용
            .anyRequest()
            .authenticated() // 다른 리소스들은 인증절차 필요
        .and()
            .formLogin()
            .loginPage("/member/login-form") // 시큐리티 로그인의 뷰를 /member/login-form으로 지정
            .usernameParameter("memberId") // username의 파라미터를 "memberId" 명칭으로 지정
            .loginProcessingUrl("/member/login-proc") // 로그인 요청 프로세스 지정
            .defaultSuccessUrl("/"); // 성공 시 보낼 url 지정
    }
}
