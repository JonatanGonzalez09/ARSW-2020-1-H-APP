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
        /*this.userRepository.deleteAll();
    	// 
        // Create users
        User juan = new User(true,"juan",passwordEncoder.encode("juan123"),"jualme18@gmail.com","1015443182","cc","USER");
    	User assistant = new User(true,"assistant",passwordEncoder.encode("assistant123"),"assistant@mail.com","147852369","cc","ASSISTANT");
        User admin = new User(true,"admin",passwordEncoder.encode("admin123"),"admin@mail.com","123456789","cc","ADMIN");
        User manager = new User(true,"manager",passwordEncoder.encode("manager123"),"manager@mail.com","987654321","cc","MANAGER");

        List<User> users = Arrays.asList(juan,assistant,admin,manager);

        // Save to db
        this.userRepository.saveAll(users);*/
    }
}