package br.com.justdoit.apiconsumer.client;

import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersDTO;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersResponseDTO;
import exceptions.ApiExternaException;
import exceptions.PersonagemNaoEncontradoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class NarutoClient {

    private final RestClient dattebayoRestClient;

    public NarutoClient(RestClient dattebayoRestClient) {
        this.dattebayoRestClient = dattebayoRestClient;
    }

    public List<NarutoCharactersDTO> getCharacters() {

        NarutoCharactersResponseDTO result = dattebayoRestClient
                .get()
                .uri("/characters")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    if(response.getStatusCode().value() == 404) {
                        throw new PersonagemNaoEncontradoException("Personagens não encontrados.");
                    }
                }))
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ApiExternaException("A API do Naruto está instável ou fora do ar.");
                })
                .body(NarutoCharactersResponseDTO.class);

        if (Objects.isNull(result) || result.characters().isEmpty()) {
            return Collections.emptyList();
        }

        return result.characters();
    }

    public NarutoCharactersDTO getCharactersById(Long id) {
        return dattebayoRestClient
                .get()
                .uri("/characters", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    if (response.getStatusCode().value() == 404) {
                        throw new PersonagemNaoEncontradoException(String.format(
                                "Personagem com ID %s não encontrado.", id
                        ));
                    }
                }))
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ApiExternaException("A API do Naruto está instável ou fora do ar.");
                })
                .body(NarutoCharactersDTO.class);
    }
}
