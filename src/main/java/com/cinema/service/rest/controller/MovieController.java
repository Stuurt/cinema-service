package com.cinema.service.rest.controller;

import com.cinema.service.rest.dto.MovieListResponse;
import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.service.MovieService;
import com.cinema.service.rest.dto.MovieResponse;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
@Validated
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Get movies", description = "bar", parameters = {@Parameter(name = "lelelel")})
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

@GetMapping
public ResponseEntity<Page<MovieListResponse>> getAllMoviePaginated(
    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
    @RequestParam(value = "size", required = false, defaultValue = "10") int size
){
    return ResponseEntity.status(HttpStatus.OK)
            .body(movieService.findAllMoviePaginated(page, size));
}

@GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movieService.findById(movieId));
    }
}
