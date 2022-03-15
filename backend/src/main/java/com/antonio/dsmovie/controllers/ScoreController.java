package com.antonio.dsmovie.controllers;

import com.antonio.dsmovie.dto.MovieDTO;
import com.antonio.dsmovie.dto.ScoreDTO;
import com.antonio.dsmovie.services.MovieService;
import com.antonio.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO scoreDTO) {
        MovieDTO movieDTO = scoreService.saveScore(scoreDTO);
        return movieDTO;
    }
}
