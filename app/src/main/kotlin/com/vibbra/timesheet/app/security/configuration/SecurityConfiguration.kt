package com.vibbra.timesheet.app.security.configuration

import com.vibbra.timesheet.app.security.configuration.jwt.JWTAuthenticationFilter
import com.vibbra.timesheet.app.security.configuration.jwt.JWTAuthorizationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
            .antMatchers(HttpMethod.POST, "/authentication").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(JWTAuthorizationFilter(authenticationManager(), userDetailsService))
            .addFilter(JWTAuthenticationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}