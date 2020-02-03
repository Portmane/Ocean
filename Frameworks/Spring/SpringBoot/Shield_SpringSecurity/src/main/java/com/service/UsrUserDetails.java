package com.service;

import com.domain.Usr;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsrUserDetails implements UserDetails {                    // Implementing UserDetails interface.
    private String userName;                // Username of the instance.
    private String password;                // Username of the instance.
    private boolean enabled;                // Username of the instance.

    private List<GrantedAuthority> authorities; // Authorities of the instance.



    public UsrUserDetails(Usr user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();

        this.authorities = Arrays.stream(user.getRoles().split(","))        // Creates the stream of given role.
            .map(SimpleGrantedAuthority::new)                                     // Sets operation to be operated on this stream.
            .collect(Collectors.toList());                                        /* Converts Collector<T, A, R> returned
                                                                                  * from toList() method in the List. */
    }

    // Standard constructor.
    public UsrUserDetails() {
        // ...
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
