package com.vibbra.timesheet.app.security.jwt.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.vibbra.timesheet.app.security.authentication.response.AuthenticationResponse
import com.vibbra.timesheet.app.security.configuration.SecurityConfigurationProperties
import com.vibbra.timesheet.app.user.converter.UserConverter
import com.vibbra.timesheet.app.user.entity.Credentials
import com.vibbra.timesheet.app.user.entity.UserAuthenticationWrapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val properties: SecurityConfigurationProperties,
    private val userConverter: UserConverter,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    init {
        setFilterProcessesUrl("/api/v1/authenticate")
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (email, password) = objectMapper.readValue(request.inputStream, Credentials::class.java)
            val token = UsernamePasswordAuthenticationToken(email, password)
            return authManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found!")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val userAuth = authResult.principal as UserAuthenticationWrapper

        val token = Jwts.builder()
            .setSubject(userAuth.username)
            .setExpiration(Date(System.currentTimeMillis() + properties.expirationTime))
            .signWith(SignatureAlgorithm.HS512, properties.secret)
            .compact()


        val authenticationResponse = AuthenticationResponse(
            token = token,
            user = userConverter.toResponse(userAuth.user)
        )

        response.contentType = "application/json"
        response.writer.write(objectMapper.writeValueAsString(authenticationResponse))
        response.writer.flush()
    }
}