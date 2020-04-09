package com.cegres.springwarehouse.securitysimple;

import com.cegres.springwarehouse.securitysimple.model.UserAccount;
import com.cegres.springwarehouse.securitysimple.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class SecuritySimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritySimpleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserAccountRepository userAccountRepository){
        return args ->{
            userAccountRepository.save(new UserAccount("sanj", "sanj", true));
            userAccountRepository.save(new UserAccount("srik", "sri", true));
        };
    }

    @Bean
    public static NoOpPasswordEncoder noOpPasswordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Service
    class MyUserDetailsService implements UserDetailsService{

        @Autowired
        UserAccountRepository userAccountRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<UserAccount> byUsername = userAccountRepository.findByUsername(username);
            if(!byUsername.isPresent()){
                throw new UsernameNotFoundException("User -> " + username + " is not found");
            }
            UserAccount userAccount = byUsername.get();

            return new User(userAccount.getUsername(),
                    userAccount.getPassword(),
                    userAccount.isActive(),
                    userAccount.isActive(),
                    userAccount.isActive(),
                    userAccount.isActive(),
                    AuthorityUtils.createAuthorityList("ADMIN", "USER"));
        }
    }

}
