package ir.sadeqcloud.BontechEvaluationProject.security;

import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Admin;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.Simple;
import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import ir.sadeqcloud.BontechEvaluationProject.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
@Component
/**
 * here is no need to explicitly set the userDetailsService and passwordEncoder on the AuthenticationManager.
 * You can register them as beans and the default AuthenticationManager created by Spring Security will pick them up.
 */
public class UserAuthenticationDao implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthenticationDao(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);
        optionalUser.orElseThrow(()->new UsernameNotFoundException(username+" not exists!"));
        User user = optionalUser.get();

        Set<GrantedAuthority> grantedAuthorities;

        if (user instanceof Admin)
            grantedAuthorities=Set.of(new SimpleGrantedAuthority("ROLE_ADMIN"));//JAVA 9+
        else
            grantedAuthorities=((Simple)user).grantedAuthorities();

        // org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().authorities(grantedAuthorities).username(username).password(user.getPassword());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuthorities);
    }
}
