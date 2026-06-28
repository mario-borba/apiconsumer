package br.com.justdoit.apiconsumer.controllers;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersDTO;
import br.com.justdoit.apiconsumer.service.NarutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dattebayo")
@Tag(name = "Endpoint de consumo API Dattebayo", description = "Consulta de ninjas na API Dattebayo")
public class NarutoController {

    @Autowired
    private NarutoService narutoService;

    @GetMapping("/characters")
    public ResponseEntity<List<NarutoCharactersDTO>> getCharacters() {
        return ResponseEntity.ok(narutoService.getCharacters());
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<NarutoCharactersDTO> getCharactersById(@PathVariable Long id) {
        return ResponseEntity.ok(narutoService.getCharactersById(id));
    }
}
