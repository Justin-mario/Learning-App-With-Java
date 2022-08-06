package com.learningapp.security.authuser;

import com.learningapp.data.entity.LearningParty;
import com.learningapp.repository.LearningPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service("authUser")
@RequiredArgsConstructor
public class AuthUserImpl implements UserDetailsService {
    private final LearningPartyRepository learningPartyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LearningParty> activeUser = Optional.of ( Optional.ofNullable ( learningPartyRepository.findByEmail ( username ) )
                .orElseThrow ( () -> new UsernameNotFoundException ( String.format ( "user with {} username not found", username ) ) ) );


        return new User ( activeUser.get ().getEmail (), activeUser.get ().getPassword (), getAuthority ( activeUser.get () ));
    }
        private Set<SimpleGrantedAuthority> getAuthority(LearningParty user){
        return user.getRoles ().stream ().map ( role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect( Collectors.toSet());
//            Set<SimpleGrantedAuthority> authorities = new HashSet<> ();
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().name()));
//            });
//            return authorities;

    }
}
