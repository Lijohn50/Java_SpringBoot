package org.production.quiz_application.repository;

import org.production.quiz_application.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :totalQue", nativeQuery = true)
    List<Question> getQuestionByCategory(String category, int totalQue);
}
