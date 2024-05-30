package com.cinema.service.domain.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.mapper.MovieMapper;
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
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(page, size));
        return movies.map(movie -> MovieMapper.INSTANCE.toDtoList(movie));
    }

    public MovieResponse findById(Long id) {
        Movie movieEntity = movieRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Movie with id: " + id + " not found"));
            return MovieMapper.INSTANCE.toDto(movieEntity);
    }
}
