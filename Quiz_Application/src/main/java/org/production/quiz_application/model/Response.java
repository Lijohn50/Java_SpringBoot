package org.production.quiz_application.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {


    private int id;
    private String response;
}
