package br.com.treino.springboot2.springbootessentials.service;

import br.com.treino.springboot2.springbootessentials.domain.Anime;
import br.com.treino.springboot2.springbootessentials.exception.ExceptionMessage;
import br.com.treino.springboot2.springbootessentials.mapper.AnimeMapper;
import br.com.treino.springboot2.springbootessentials.repository.AnimeRepository;
import br.com.treino.springboot2.springbootessentials.request.AnimePostRequestBody;
import br.com.treino.springboot2.springbootessentials.request.AnimePutRequestBody;
import br.com.treino.springboot2.springbootessentials.validation.NaYValidation;
import br.com.treino.springboot2.springbootessentials.validation.RepeatNameValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    AnimeMapper animeMapper;

    @Autowired
    RepeatNameValidation repeat;

    @Autowired
    NaYValidation naYValidation;

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
        if (naYValidation.naYValidation(animePostRequestBody.getName())){
            throw new ExceptionMessage("O nome 'naiara' não pode ser com 'y'");
        } else if (repeat.repeatNameValidation(animePostRequestBody.getName())){
            throw new ExceptionMessage("O nome não pode ser repetido");
        } else {
            return animeRepository.save(animeMapper.toAnime(animePostRequestBody));
        }
    }

    public void delete(long id) throws ExceptionMessage {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void deleteByName(@NotNull @NotEmpty(message = "The anime cannot be empty") Anime name) throws ExceptionMessage{
        animeRepository.delete(name);
    }

    public Anime replace(Long id, AnimePutRequestBody animePutRequestBody) throws ExceptionMessage {
        Anime newAnime = findByIdOrThrowBadRequestException(id);
        newAnime.setName(animePutRequestBody.getName());
        return animeRepository.save(newAnime);
    }
}
