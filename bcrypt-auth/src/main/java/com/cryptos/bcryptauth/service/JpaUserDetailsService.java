package com.cryptos.bcryptauth.service;

import com.cryptos.bcryptauth.model.CustomUserDetails;
import com.cryptos.bcryptauth.model.User;
import com.cryptos.bcryptauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("User not found");
        User user = userRepository.findByUsername(username).orElseThrow(supplier);
        return new CustomUserDetails(user);
    }
}
