package com.cinema.service.domain.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.mapper.MovieMapper;
import com.cinema.service.domain.repository.MovieRepository;
import com.cinema.service.rest.dto.request.MovieCreateRequest;
import com.cinema.service.rest.dto.response.MovieListResponse;
import com.cinema.service.rest.dto.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    public final MovieRepository movieRepository;
    @Value("${cinema.movie-image-upload-directory}")
    private String movieImagesPath;
    private final ImageService imageService;

    public MovieResponse create(MovieCreateRequest movieCreateRequest) {
        var movieEntity = MovieMapper.INSTANCE.fromCreateRequest(movieCreateRequest);
        return MovieMapper.INSTANCE.toDto(movieRepository.save(movieEntity));
    }

    public void saveMovieImage(MultipartFile[] movieImage, Long movieId) throws IOException {
        var movieEntity = movieRepository.findById(movieId)
                .orElseThrow();
        String adsImagesString = "";
        for (MultipartFile imageFile : movieImage) {
            adsImagesString += imageService.saveImageToStorage(movieImagesPath, imageFile) + ",";
        }
        movieEntity.setImagePath(adsImagesString);
        movieRepository.save(movieEntity);
    }

    public Page<MovieListResponse> findAll(int page, int size) throws IOException {
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(page, size));

        List<Movie> movieList = movies.getContent();

        Map<Long, String> moviePathMap = movieList.stream()
                .collect(Collectors.toMap(Movie::getId, Movie::getImagePath));

        List<MovieListResponse> movieResponseList = movies.stream()
                .map(movie -> MovieMapper.INSTANCE.toDtoList(movie))
                .collect(Collectors.toList());

        for (MovieListResponse movieResponse : movieResponseList) {
            String imagePath = moviePathMap.get(movieResponse.getId());
            if (imagePath != null) {
                try {
                    movieResponse.setMovieImage(imageService.getImage(movieImagesPath, imagePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return new PageImpl<>(movieResponseList, PageRequest.of(page, size), movies.getTotalElements());
    }

    public MovieResponse findById(Long id) {
        Movie movieEntity = movieRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Movie with id: " + id + " not found"));
            return MovieMapper.INSTANCE.toDto(movieEntity);
    }
}
