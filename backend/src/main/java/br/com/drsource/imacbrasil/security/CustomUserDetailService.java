package br.com.drsource.imacbrasil.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    public CustomUserDetailService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Credential credential = Optional.ofNullable(credentialRepository.findCredentialByEmail(username)).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        StringBuffer authorities = new StringBuffer();
        credential.getRoles().forEach(r -> {
            authorities.append("ROLE_").append(r.getName()).append(",");
        });

        authorities.deleteCharAt(authorities.length() - 1);

        return new User(credential.getEmail(),credential.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList(authorities.toString()));
    }
}
