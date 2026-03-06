package org.production.quiz_application.controller;
import org.production.quiz_application.model.QuestionWrapper;
import org.production.quiz_application.model.Response;
import org.production.quiz_application.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestParam String category, @RequestParam int totalQue, @RequestParam String title){

        return service.createQuiz(category, totalQue, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestion(@PathVariable int id){

        return service.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){

    return service.calculateResult(id, responses);
    }
}
