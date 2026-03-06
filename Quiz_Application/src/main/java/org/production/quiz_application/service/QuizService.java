package org.production.quiz_application.service;

import org.production.quiz_application.model.Question;
import org.production.quiz_application.model.QuestionWrapper;
import org.production.quiz_application.model.Quiz;
import org.production.quiz_application.model.Response;
import org.production.quiz_application.repository.QuestionRepository;
import org.production.quiz_application.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository repository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int totalQue, String title) {

        List<Question> questions = questionRepository.getQuestionByCategory(category, totalQue);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        try{
            repository.save(quiz);
            return new ResponseEntity<>("quiz created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {

        try{
            Optional<Quiz> quiz = repository.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            List<QuestionWrapper> list = new ArrayList<>();
            for(Question q : questions){

                QuestionWrapper wrapper = new QuestionWrapper(q.getOption4(), q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3());
                list.add(wrapper);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {

        try{
            Quiz quiz = repository.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int score = 0;
            int i = 0;
            for(Question q: questions){

                if(q.getRightAnswer().equals(responses.get(i).getResponse())){

                    score++;
                }
                i++;
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
