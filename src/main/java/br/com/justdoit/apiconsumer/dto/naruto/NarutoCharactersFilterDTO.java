package br.com.justdoit.apiconsumer.dto.naruto;

public record NarutoCharactersFilterDTO(Integer page,
                                        Integer limit,
                                        String name) {

    public Integer pageOrDefault() {
        return page != null ? page : 1;
    }

    public Integer limitOrDefault() {
        return limit != null ? limit : 20;
    }
}
