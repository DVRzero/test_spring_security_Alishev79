package com.example.test_spring_security.security;

import com.example.test_spring_security.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderIml implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderIml(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UserDetails personDetails = personDetailsService.loadUserByUsername(name);
        String password = authentication.getCredentials().toString();
        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return authentication
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
