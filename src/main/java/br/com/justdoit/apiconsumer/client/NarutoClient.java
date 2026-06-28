package br.com.justdoit.apiconsumer.client;

import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersDTO;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersFilterDTO;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersResponseDTO;
import exceptions.ApiExternaException;
import exceptions.PersonagemNaoEncontradoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NarutoClient {

    private final RestClient dattebayoRestClient;

    public NarutoClient(RestClient dattebayoRestClient) {
        this.dattebayoRestClient = dattebayoRestClient;
    }

    public NarutoCharactersResponseDTO getCharacters(
            NarutoCharactersFilterDTO params) {

        String name = params.name();

        return dattebayoRestClient
                .get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/characters")
                            .queryParam("page", params.pageOrDefault())
                            .queryParam("limit", params.limitOrDefault());

                    if (name != null && !name.isBlank()) {
                        uriBuilder.queryParam("name", name);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    if (response.getStatusCode().value() == 404) {
                        throw new PersonagemNaoEncontradoException("Personagens não encontrados.");
                    }
                }))
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ApiExternaException("A API do Naruto está instável ou fora do ar.");
                })
                .body(NarutoCharactersResponseDTO.class);

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
