package br.com.justdoit.apiconsumer.dto.naruto;

import java.util.List;
import java.util.Objects;

public class NarutoCharactersDTO {
    private Long id;
    private String name;
    private List<String> images;

    public NarutoCharactersDTO() {
    }

    public NarutoCharactersDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NarutoCharactersDTO that = (NarutoCharactersDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "NarutoCharactersDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
