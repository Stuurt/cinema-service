package com.cinema.service.domain.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.repository.MovieRepository;
import com.cinema.service.rest.dto.MovieListResponse;
import com.cinema.service.rest.dto.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    public final MovieRepository movieRepository;

    public Page<MovieListResponse> findAll(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size));
    }
    

    public MovieResponse findById(Long id) {
        Movie movieEntity = movieRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Movie with id: " + id + " not found"));

            return new MovieResponse(
                movieEntity.getId(),
                movieEntity.getName(),
                movieEntity.getSynopsis(),
                movieEntity.getAgeGroup(),
                movieEntity.getCategory(),
                movieEntity.getReleaseDate(),
                movieEntity.getDuration(),
                movieEntity.getDirector(),
                movieEntity.getMovieCast(),
                movieEntity.getProducer());
    }
}
