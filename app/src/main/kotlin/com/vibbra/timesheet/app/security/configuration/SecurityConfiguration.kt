package com.vibbra.timesheet.app.security.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.vibbra.timesheet.app.security.jwt.filter.JWTAuthenticationFilter
import com.vibbra.timesheet.app.security.jwt.filter.JWTAuthorizationFilter
import com.vibbra.timesheet.app.user.converter.UserConverter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(SecurityConfigurationProperties::class)
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
    private val properties: SecurityConfigurationProperties,
    private val userConverter: UserConverter,
    private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        val authManager = authenticationManager()

        val authorizationFilter = JWTAuthorizationFilter(
            authManager = authManager,
            userDetailsService = userDetailsService,
            properties = properties
        )

        val authenticationFilter = JWTAuthenticationFilter(
            authManager = authManager,
            properties = properties,
            userConverter = userConverter,
            objectMapper = objectMapper
        )

        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
            .antMatchers(HttpMethod.POST, "/authentication").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(authorizationFilter)
            .addFilter(authenticationFilter)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}