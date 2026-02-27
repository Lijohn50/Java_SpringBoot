package org.practice.security_1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetails userDetails;

    List<Student> students = new ArrayList<>(List.of(new Student(1, "loki"), new Student(2, "thor")));

    @GetMapping("/")
    public List<Student> getStudents(){

        return students;
    }
    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student student){

        students.add(student);
        return student;
    }

    @GetMapping("/login")
    public User verifyUser(User user){

        userDetails.loadUserByUsername(user.getUsername());
        return user;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){

        user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
        return user;
    }

}
