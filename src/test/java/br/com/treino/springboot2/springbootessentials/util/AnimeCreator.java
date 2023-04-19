package br.com.treino.springboot2.springbootessentials.util;

import br.com.treino.springboot2.springbootessentials.domain.Anime;

public class AnimeCreator {
    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Star Wars V")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Star Wars V")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Star Wars III")
                .id(1L)
                .build();
    }
}
