package com.cinema.service.domain.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table
public class Movie{
    
    //Atributos

    @Id
    private Long id;
    private String name;
    private String synopsis;
    private String ageGroup;
    private String category;
    private LocalDate releaseDate;
    private String duration;
    private String director;
    private String movieCast;
    private String producer;

    // Método Construtor

    public Movie(Long id, String name, String synopsis, String ageGroup, String category, LocalDate releaseDate, String duration, String director, String movieCast, String producer){

        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.ageGroup = ageGroup;
        this.category = category;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.director = director;
        this.movieCast = movieCast;
        this.producer = producer;
    }

    // O JPA precisa de um método construtor vazio
    public Movie() {

    }

    //Getter e Setter

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public String getAgeGroup() {
        return ageGroup;
    }
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getMovieCast() {
        return movieCast;
    }
    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }
    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }

}
