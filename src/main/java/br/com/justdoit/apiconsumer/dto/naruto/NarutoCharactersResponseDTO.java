package br.com.justdoit.apiconsumer.dto.naruto;

import java.util.List;

public record NarutoCharactersResponseDTO(
        Integer total,
        Integer pageSize,
        Integer currentPage,
        List<NarutoCharactersDTO> characters
) {
}
