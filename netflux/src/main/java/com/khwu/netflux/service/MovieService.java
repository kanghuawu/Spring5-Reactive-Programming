package com.khwu.netflux.service;

import com.khwu.netflux.domain.Movie;
import com.khwu.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
    Flux<MovieEvent> events(String movieId);
    Mono<Movie> getMovieById(String id);
    Flux<Movie> getAllMovies();
}
