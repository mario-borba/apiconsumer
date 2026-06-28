package br.com.justdoit.apiconsumer.dto.naruto;

import java.util.List;

public record NarutoCharactersDTO (
        Long id,
        String name,
        List<String> images){
}
