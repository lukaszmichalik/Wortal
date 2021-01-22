package com.springboot.repository;

import com.springboot.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository  extends JpaRepository<Team, Long> {
    Team findByName(String name);

    Boolean existsByName(String name);
}
