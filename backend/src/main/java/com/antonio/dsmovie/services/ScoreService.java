package com.antonio.dsmovie.services;

import com.antonio.dsmovie.dto.MovieDTO;
import com.antonio.dsmovie.dto.ScoreDTO;
import com.antonio.dsmovie.entities.Movie;
import com.antonio.dsmovie.entities.Score;
import com.antonio.dsmovie.entities.User;
import com.antonio.dsmovie.repositories.MovieRepository;
import com.antonio.dsmovie.repositories.ScoreRepository;
import com.antonio.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO) {
        User user = userRepository.findByEmail(scoreDTO.getEmail());
        if(user == null) {
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(scoreDTO.getMovieId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());
        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for(Score s : movie.getScores()) {
            sum = sum + s.getValue();
        }

        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}
