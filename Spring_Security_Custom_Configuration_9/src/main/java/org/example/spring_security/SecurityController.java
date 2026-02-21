package org.example.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SecurityController {

    List<Student> students = new ArrayList<>(List.of(new Student("john", 1, 45), new Student("mark", 2, 87), new Student("loki", 3, 67)));

    @RequestMapping("/")
    public String welcome(HttpServletRequest session){

        return "Welcome to backend!" + session.getSession().getId();
    }

    @GetMapping("/students")
    public List<Student>getStudents(){

        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        students.add(student);
        return student;
    }
}
