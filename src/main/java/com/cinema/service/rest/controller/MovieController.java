package com.cinema.service.rest.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Get movies", description = "bar", parameters = {@Parameter(name = "lelelel")})
    public String getMethodName(@RequestParam String param) {
            return new String();
    }
}
