package com.pragma.powerup.infrastructure.security;

import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.security.dto.AuthLoginRequest;
import com.pragma.powerup.infrastructure.security.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor TODO: buscar requiredargs comentados y probar con beanconfiguration
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private /*final*/ IUserPersistencePort userPersistencePort;
    @Autowired
    private /*final*/ IRolePersistencePort rolePersistencePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: MEJORA se usaron modelos de dominio pero se podria utilizar entidades personalizadas
        //TODO: MEJORA se conecta directamente a peristencia pero podria estar entrando por servicios mediante el handler
        com.pragma.powerup.domain.model.User user = userPersistencePort.findByDocumentNumber(Integer.parseInt(username));
        com.pragma.powerup.domain.model.Role role = rolePersistencePort.findById(user.getIdRole());

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName())));

        User userSecurity = new User(Integer.toString(user.getDocumentNumber()), user.getPass(), authorityList);

        return userSecurity;
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.getUsername();
        String password = authLoginRequest.getPass();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(username);
        authResponse.setMessage("Logged successfuly");
        authResponse.setJwt(accessToken);
        authResponse.setStatus(true);

        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
