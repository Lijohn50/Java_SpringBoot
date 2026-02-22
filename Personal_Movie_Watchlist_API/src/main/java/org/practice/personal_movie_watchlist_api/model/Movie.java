package org.practice.personal_movie_watchlist_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movie {

    @Id
    private int id;
    private String title;
    private String genre;
    private String director;
    private int releaseYear;
    private boolean watched;
}
