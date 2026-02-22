package org.practice.personal_movie_watchlist_api.repository;

import org.practice.personal_movie_watchlist_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
