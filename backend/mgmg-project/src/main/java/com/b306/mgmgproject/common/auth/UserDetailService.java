package com.b306.mgmgproject.common.auth;

import com.b306.mgmgproject.api.service.UserService;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserId(username);
        if (user != null) {
            UserDetails userDetails = new UserDetails(user);
            return userDetails;
        }
        return null;
    }
}
