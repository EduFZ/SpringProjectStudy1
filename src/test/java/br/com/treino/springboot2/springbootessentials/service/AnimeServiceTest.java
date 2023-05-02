package br.com.treino.springboot2.springbootessentials.service;

import br.com.treino.springboot2.springbootessentials.controller.AnimeController;
import br.com.treino.springboot2.springbootessentials.domain.Anime;
import br.com.treino.springboot2.springbootessentials.exception.BadRequestException;
import br.com.treino.springboot2.springbootessentials.exception.ExceptionMessage;
import br.com.treino.springboot2.springbootessentials.repository.AnimeRepository;
import br.com.treino.springboot2.springbootessentials.request.AnimePostRequestBody;
import br.com.treino.springboot2.springbootessentials.util.AnimeCreator;
import br.com.treino.springboot2.springbootessentials.util.AnimePostRequestBodyCreator;
import br.com.treino.springboot2.springbootessentials.validation.NaYValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks // Testar a classe em si
    private AnimeService animeService;
    @Mock // Testar as classes utilizadas na classe principal
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp() throws ExceptionMessage {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());


        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));


    }
    @Test
    @DisplayName("listAll returns list of anime inside page object when successful")
    void listAll_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){
        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAllNonPageable returns list of anime when successful")
    void listAllNonPageable_ReturnsListOfAnimes_WhenSuccessful(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeService.listAllNonPageable();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns anime when successful")
    void findByIdOrThrowBadRequestException_ReturnsListOfAnimes_WhenSuccessful() throws ExceptionMessage {
        Long expectedId = AnimeCreator.createValidAnime().getId();

        Anime anime = animeService.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    // ------------------ ERRO ------------------
//    @Test
//    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when anime is not found")
//    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenAnimeIsNotFound() throws ExceptionMessage {
//
//        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.empty());
//
//        Assertions.assertThatExceptionOfType(BadRequestException.class)
//                .isThrownBy(() -> animeService.findByIdOrThrowBadRequestException(1));
//
//    }

    @Test
    @DisplayName("findByName returns a list of anime when successful")
    void findByName_ReturnsListOfAnimes_WhenSuccessful() throws ExceptionMessage {
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of anime is not found")
    void findByName_ReturnsEmptyListOfAnimes_WhenAnimeIsNotFound() throws ExceptionMessage {

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();
    }

    // ------------------ ERRO ------------------
//    @Test
//    @DisplayName("save returns anime when successful")
//    void save_ReturnsAnimes_WhenSuccessful(NaYValidation naYValidation) throws ExceptionMessage {
//
//        Anime anime = animeService.save(AnimePostRequestBodyCreator.createAnimePostRequestBody());
//
//        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
//
//        // OU
////        AnimePostRequestBody animes = AnimePostRequestBodyCreator.createAnimePostRequestBody();
////
////        if (naYValidation.naYValidation(AnimePostRequestBodyCreator.createAnimePostRequestBody().getName())){
////            animeService.save(animes);
////        }
////
////
////
////        Assertions.assertThat(animes).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
//
//    }

    // ------------------ ERRO ------------------
//    @Test
//    @DisplayName("replace update anime when successful")
//    void replace_ReturnsAnimes_WhenSuccessful() throws ExceptionMessage {
//
//        Assertions.assertThatCode(() -> animeService.replace(1L, AnimePutRequestBodyCreator.createAnimePutRequestBody()))
//                .doesNotThrowAnyException();
//
//        ResponseEntity<Anime> entity = animeService.replace(1L, AnimePutRequestBodyCreator.createAnimePutRequestBody());
//
//        Assertions.assertThat(entity).isNotNull();
//
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//
//    }

    @Test
    @DisplayName("delete removes anime when successful")
    void delete_RemovesAnimes_WhenSuccessful() throws ExceptionMessage {

        Assertions.assertThatCode(() -> animeService.delete(1))
                .doesNotThrowAnyException();

    }

}