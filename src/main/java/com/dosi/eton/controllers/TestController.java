package com.dosi.eton.controllers;


import com.dosi.eton.user.User;
import com.dosi.eton.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private UserRepository userRepository;
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }
  @GetMapping("/allUsers")
  public ResponseEntity<?> allUsers() {
    if (userRepository.findAll().isEmpty()) {
      return ResponseEntity.badRequest().body("No users found");
    }else {
      List<User> users = userRepository.findAll().stream().map(user -> {
        user.setPassword(encoder.encode(user.getPassword()));
        return user;
      }).toList();
        return ResponseEntity.ok(users);
    }



  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }


  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
