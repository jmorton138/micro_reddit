package com.microreddit.MicroReddit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean isPasswordConfirmed(User user) {
        boolean passwordConfirmed = true;
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            passwordConfirmed = false;
        }
        return passwordConfirmed;
    }


    public boolean isUserPresent(User user) {
        boolean userExists = false;
        Optional<User> existingUserName = userRepo.findByUsername((user.getUsername()));
        if (existingUserName.isPresent()) {
            userExists = true;
        }
        return userExists;

    }
    public void addUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        userRepo.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", username)
                )
        );
    }
}
