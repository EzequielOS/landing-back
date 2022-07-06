package br.edu.iftm.imbMary.security;

import br.edu.iftm.imbMary.domain.Admin;
import br.edu.iftm.imbMary.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(admin);

    }
}