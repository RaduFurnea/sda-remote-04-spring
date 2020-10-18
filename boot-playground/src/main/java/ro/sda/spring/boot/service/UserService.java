package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.dto.UserRegisterDTO;
import ro.sda.spring.boot.entity.User;
import ro.sda.spring.boot.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveUserDTO(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(encoder.encode(userRegisterDTO.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @PostConstruct
    public void init() {
        List<User> users = new ArrayList<>();
        if (!userRepository.findByUsername("admin").isPresent()) {
            users.add(new User("admin", encoder.encode("admin"), "ROLE_ADMIN"));
        }
        if (!userRepository.findByUsername("user").isPresent()) {
            users.add(new User("user", encoder.encode("user"), "ROLE_USER"));
        }
        userRepository.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = this.userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    optUser.get().getUsername(),
                    optUser.get().getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(optUser.get().getRole()))
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
