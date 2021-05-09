package com.vibbra.timesheet.app.security.configuration.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.vibbra.timesheet.app.entrypoint.rest.user.dto.response.UserResponse
import com.vibbra.timesheet.app.security.configuration.UserDetailsImpl
import com.vibbra.timesheet.app.security.response.AuthenticationResponse
import com.vibbra.timesheet.app.user.entity.Credentials
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
    private val authManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {


    init {
        setFilterProcessesUrl("/api/v1/authenticate")
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (email, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)
            val token = UsernamePasswordAuthenticationToken(email, password)
            return authManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found!")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + 60000))
            .signWith(SignatureAlgorithm.HS512, "my-secret-token-to-change-in-production")
            .compact()


        val a = (authResult.principal as UserDetailsImpl).user


        val authenticationResponse = AuthenticationResponse(
            token = token,
            user = UserResponse(
                id = a.id,
                name = a.name,
                email = a.email,
                login = a.login,
                createdAt = a.createdAt,
                updatedAt = a.updatedAt
            )
        )
        response.addHeader("Authorization", "Bearer $token")
        response.contentType = "application/json"
        response.writer.write(ObjectMapper().writeValueAsString(authenticationResponse))
        response.writer.flush()
    }
}