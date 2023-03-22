package br.com.treino.springboot2.springbootessentials.service;

import br.com.treino.springboot2.springbootessentials.domain.Anime;
import br.com.treino.springboot2.springbootessentials.exception.EduardoException;
import br.com.treino.springboot2.springbootessentials.exception.NaiaraException;
import br.com.treino.springboot2.springbootessentials.mapper.AnimeMapper;
import br.com.treino.springboot2.springbootessentials.repository.AnimeRepository;
import br.com.treino.springboot2.springbootessentials.request.AnimePostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    AnimeMapper animeMapper;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) throws EduardoException {
        return animeRepository.findById(id)
                .orElseThrow(() -> new EduardoException("Anime not Found"));
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) throws NaiaraException {
        if (animePostRequestBody.getName().equalsIgnoreCase("naiara")){
            throw new NaiaraException("O nome não pode ser 'naiara'");
        }else {
            return animeRepository.save(animeMapper.toAnime(animePostRequestBody));
        }


    }
}
// Criar uma validação para não poder ter mais de uma "naiara";
//Criar uma validação para impedir que "naiara" com "y" seja criada;
//Criar um package validation;
