package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.dto.UserRegisterDTO;
import ro.sda.spring.boot.entity.User;
import ro.sda.spring.boot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = this.userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(optUser.get().getUsername(),
                    optUser.get().getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(optUser.get().getRole())));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    public User saveUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(encoder.encode(userRegisterDTO.getPassword()));
        user.setRole("admin");
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    public void init() {
        User user = new User("admin", encoder.encode("admin"), "admin");
        userRepository.save(user);
    }
}
