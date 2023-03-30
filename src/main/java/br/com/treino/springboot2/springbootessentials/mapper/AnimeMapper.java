package br.com.treino.springboot2.springbootessentials.mapper;

import br.com.treino.springboot2.springbootessentials.domain.Anime;
import br.com.treino.springboot2.springbootessentials.request.AnimePostRequestBody;
import br.com.treino.springboot2.springbootessentials.request.AnimePutRequestBody;
import org.springframework.stereotype.Component;

@Component()
public  class AnimeMapper {

    //Esse método recebe um objeto animePostRequestBody, faz a conversão do objeto para Anime e retorna o anime;
    public Anime toAnime(AnimePostRequestBody animePostRequestBody) {
        Anime anime = new Anime();
        anime.setName(animePostRequestBody.getName());
        return anime;
    }

//    public Anime toAnime(Anime animePutRequestBody) {
//        Anime anime = new Anime();
//        anime.setName(animePutRequestBody.getName());
//        return anime;
//    }
}
