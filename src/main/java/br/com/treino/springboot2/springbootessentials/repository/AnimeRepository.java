package br.com.treino.springboot2.springbootessentials.repository;

import br.com.treino.springboot2.springbootessentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByName(String name);
}
