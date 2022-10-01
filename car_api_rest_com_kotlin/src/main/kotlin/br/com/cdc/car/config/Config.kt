package br.com.cdc.car.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.annotation.PostConstruct
import javax.sql.DataSource
import br.com.cdc.car.domain.UserRepository
import br.com.cdc.car.domain.User as DomainUser

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class SecurityConfig(val dataSource: DataSource): WebSecurityConfigurerAdapter() {
//class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        // https://stackoverflow.com/questions/26220083/h2-database-console-spring-boot-load-denied-by-x-frame-options
        http.headers().frameOptions().sameOrigin()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
            .antMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {

//        val password = "{noop}password";
//
//        val driver = User.builder()
//            .username("driver")
//            .password(password)
//            .roles("DRIVER")
//
//        val passenger = User.builder()
//            .username("passenger")
//            .password(password)
//            .roles("PASSENGER")
//
//        val admin = User.builder()
//            .username("admin")
//            .password(password)
//            .roles("ADMIN")
//
//        auth.inMemoryAuthentication()
//            .withUser(driver)
//            .withUser(passenger)
//            .withUser(admin)



        val queryUsers = "select username, password, enabled from `user` where username=?"
        val queryRoles = "select u.username, r.roles from user_roles r, `user` u where r.user_id = u.id and u.username=?"

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery(queryUsers)
            .authoritiesByUsernameQuery(queryRoles)
    }
}

@Configuration
class LoadUserConfig(
    val passwordEncoder: PasswordEncoder,
    val userRepository: UserRepository
) {

    @PostConstruct
    fun init() {
        val admin = DomainUser(
            username = "admin",
            password = passwordEncoder.encode("password"),
            roles = mutableListOf("ROLE_ADMIN")
        )
        userRepository.save(admin)
    }

}