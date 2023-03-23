package br.com.treino.springboot2.springbootessentials.service;

import br.com.treino.springboot2.springbootessentials.domain.Anime;
import br.com.treino.springboot2.springbootessentials.exception.ExceptionMessage;
import br.com.treino.springboot2.springbootessentials.mapper.AnimeMapper;
import br.com.treino.springboot2.springbootessentials.repository.AnimeRepository;
import br.com.treino.springboot2.springbootessentials.request.AnimePostRequestBody;
import br.com.treino.springboot2.springbootessentials.validation.RepeatNameValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    AnimeMapper animeMapper;

    @Autowired
    RepeatNameValidation repeat;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) throws ExceptionMessage {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ExceptionMessage("Anime not Found"));
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) throws ExceptionMessage {
        if (animePostRequestBody.getName().equalsIgnoreCase("naiara")){
            throw new ExceptionMessage("O nome não pode ser 'naiara'");
        } else if (repeat.repeatNameValidation(animePostRequestBody.getName()) == true){
            throw new ExceptionMessage("O nome não pode ser repetido");
        } else {
            return animeRepository.save(animeMapper.toAnime(animePostRequestBody));
        }


    }
}



//Criar uma validação para impedir que "naiara" com "y" seja criada;
//Criar um package validation;
