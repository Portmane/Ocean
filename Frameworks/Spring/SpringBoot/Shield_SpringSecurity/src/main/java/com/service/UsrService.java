package com.service;

import com.domain.Usr;
import com.repos.UsrRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsrService implements UserDetailsService {
    final private
    UsrRepository usrRepository;                        // Getting dependency on userRepository instance.

    public UsrService(UsrRepository usrRepository) {
        this.usrRepository = usrRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userNameToFind) throws UsernameNotFoundException {
        Optional<Usr> user = usrRepository.findByUsername(userNameToFind);           /* Finds the instance of
                                                                                     * User in UsrRepository instance.*/

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userNameToFind));  // If User was not found.

        return user.map(UsrUserDetails::new).get();                                 // Comment.
    }
}
