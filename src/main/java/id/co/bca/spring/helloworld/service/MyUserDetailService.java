package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.model.MyUserDetail;
import id.co.bca.spring.helloworld.model.User;
import id.co.bca.spring.helloworld.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No user found!");
        }
        return new MyUserDetail(user);
    }
}
