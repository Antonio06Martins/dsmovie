package com.antonio.dsmovie.repositories;

import com.antonio.dsmovie.entities.Score;
import com.antonio.dsmovie.entities.ScorePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScorePk> {
}
