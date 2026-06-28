package br.com.justdoit.apiconsumer.service;

import br.com.justdoit.apiconsumer.client.NarutoClient;
import br.com.justdoit.apiconsumer.dto.naruto.NarutoCharactersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NarutoService {

    @Autowired
    private NarutoClient narutoClient;

    public List<NarutoCharactersDTO> getCharacters() {
        return narutoClient.getCharacters();
    }

    public NarutoCharactersDTO getCharactersById(Long id) {
        return narutoClient.getCharactersById(id);
    }
}
