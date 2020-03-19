package edu.eci.arsw.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.arsw.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserPersistence userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserPersistence userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }   

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Create users
        User juan = new User("juan",passwordEncoder.encode("juan123"),"jualme18@gmail.com","1015443182","CC","USER");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"admin@mail.com","123456789","CC","ADMIN");
        User manager = new User("manager",passwordEncoder.encode("manager123"),"manager@mail.com","987654321","CC","MANAGER");

        List<User> users = Arrays.asList(juan,admin,manager);

        // Save to db
        this.userRepository.saveAll(users);
    }
}