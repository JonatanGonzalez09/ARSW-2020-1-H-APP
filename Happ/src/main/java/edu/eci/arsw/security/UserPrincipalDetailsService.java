package edu.eci.arsw.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.persistence.UserPersistence;
import edu.eci.arsw.model.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
    private UserPersistence userPersistence;

    public UserPrincipalDetailsService(UserPersistence userRepository) {
        this.userPersistence = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userPersistence.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}