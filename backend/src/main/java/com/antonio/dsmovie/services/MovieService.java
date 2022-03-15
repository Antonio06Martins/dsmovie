package com.antonio.dsmovie.services;

import com.antonio.dsmovie.dto.MovieDTO;
import com.antonio.dsmovie.entities.Movie;
import com.antonio.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable) {
        Page<Movie> result = movieRepository.findAll(pageable);
        Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
        return page;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie result = movieRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado no banco de dados"));
        MovieDTO movieDTO = new MovieDTO(result);
        return movieDTO;
    }
}
