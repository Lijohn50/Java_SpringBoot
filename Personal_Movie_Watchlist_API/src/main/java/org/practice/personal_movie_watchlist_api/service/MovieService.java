package org.practice.personal_movie_watchlist_api.service;


import org.practice.personal_movie_watchlist_api.model.Movie;
import org.practice.personal_movie_watchlist_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;
    public MovieService(MovieRepository repository){

        this.repository = repository;
    }

    public List<Movie> getMovies(){

        return repository.findAll();
    }

    public void addMovie(Movie movie) {

         repository.save(movie);
    }

    public Movie getMovieById(int id) {

        return repository.findById(id).orElse(null);
    }

    public Movie updateMovieById(int id, Movie movie) {

        return repository.save(movie);
    }

    public void deleteMovieById(int id) {

        repository.deleteById(id);
    }
}
