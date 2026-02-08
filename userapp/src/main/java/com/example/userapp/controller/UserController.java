package com.example.userapp.controller;

import com.example.userapp.entity.User;
import com.example.userapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.example.userapp.util.CsvHelper;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // CREATE new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/upload")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please upload a CSV file!";
        }

        List<User> users = CsvHelper.csvToUsers(file);
        userRepository.saveAll(users);

        return "CSV uploaded successfully!";
    }

}
