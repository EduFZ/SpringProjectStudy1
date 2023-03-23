package br.com.treino.springboot2.springbootessentials.validation;

import br.com.treino.springboot2.springbootessentials.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepeatNameValidation{
    @Autowired
    AnimeRepository animeRepository;

    public boolean repeatNameValidation(String anime){
        return animeRepository.findByName(anime).toString().contains(anime);
    }

}

//animeRepository.findByName(animePostRequestBody.getName()).toString().contains(animePostRequestBody.getName())