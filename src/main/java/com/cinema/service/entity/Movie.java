package com.cinema.service.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie{
    
    //Atributos

    @Id
    private Long id;
    private String name;
    private String synopsis;
    private String ageGroup;
    private String category;
    private Date releaseDate;
    private String duration;
    private String director;
    private String cast;
    private String producer;

    // Método Construtor

    public Movie(Long id, String name, String synopsis, String ageGroup, String category, Date releaseDate, String duration, String director, String cast, String producer){

        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.ageGroup = ageGroup;
        this.category = category;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.director = director;
        this.cast = cast;
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
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
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
    public String getCast() {
        return cast;
    }
    public void setCast(String cast) {
        this.cast = cast;
    }
    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }

}
