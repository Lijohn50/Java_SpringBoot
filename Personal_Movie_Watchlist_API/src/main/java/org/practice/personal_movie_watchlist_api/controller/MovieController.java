package org.practice.personal_movie_watchlist_api.controller;

import org.practice.personal_movie_watchlist_api.model.Movie;
import org.practice.personal_movie_watchlist_api.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService service;
    public MovieController(MovieService service){

        this.service = service;
    }

    @GetMapping("/api/movies")
    public ResponseEntity<List<Movie>> getMovies(){

        return new ResponseEntity<>(service.getMovies(), HttpStatus.OK);
    }

    @PostMapping("/api/movies")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){

        System.out.println("hello");
        if(movie != null){

            service.addMovie(movie);
            return new ResponseEntity<>("Add Successful!", HttpStatus.OK);
        }else{

            return new ResponseEntity<>("couldn't add movie", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){

        Movie movie = service.getMovieById(id);
        if(movie != null){

            return new ResponseEntity<>(movie, HttpStatus.OK);
        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/movies/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable int id, @RequestBody Movie updateMovie){

        Movie movie = service.updateMovieById(id, updateMovie);
        if(movie != null){

            return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
        }else{

            return new ResponseEntity<>("Couldn't Update!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/movies/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id){

        try{

            service.deleteMovieById(id);
            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
        }catch(EnumConstantNotPresentException e){

            return new ResponseEntity<>("Movie not found!", HttpStatus.NOT_FOUND);
        }
    }
}
