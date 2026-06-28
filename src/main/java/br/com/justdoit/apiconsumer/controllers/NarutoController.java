package br.com.justdoit.apiconsumer.controllers;

import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersDTO;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersFilterDTO;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersResponseDTO;
import br.com.justdoit.apiconsumer.service.NarutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dattebayo")
@Tag(name = "Naruto", description = "Endpoints para consulta da API Dattebayo")
public class NarutoController {

    @Autowired
    private NarutoService narutoService;

    @Operation(
            summary = "Lista todos os ninjas",
            description = "Retorna todos os ninjas da API Dattebayo"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de personagens retornada com sucesso")
    })
    @GetMapping("/characters")
    public ResponseEntity<NarutoCharactersResponseDTO> getCharacters(

            @Parameter(description = "Página atual", example = "1")
            @RequestParam(defaultValue = "1")
            Integer page,

            @Parameter(description = "Quantidade de registros por página", example = "20")
            @RequestParam(defaultValue = "20")
            Integer limit,

            @Parameter(description = "Filtrar pelo nome do personagem", example = "Naruto")
            @RequestParam(required = false)
            String name
    ) {
        return ResponseEntity.ok(narutoService.getCharacters(new NarutoCharactersFilterDTO(page, limit, name)));
    }

    @Operation(
            summary = "Consulta um ninja por ID",
            description = "Retorna os dados de um personagem específico da API Dattebayo"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Personagem encontrado"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    })
    @GetMapping("/characters/{id}")
    public ResponseEntity<NarutoCharactersDTO> getCharactersById(
            @Parameter(description = "ID do personagem")
            @PathVariable Long id) {
        return ResponseEntity.ok(narutoService.getCharactersById(id));
    }
}
