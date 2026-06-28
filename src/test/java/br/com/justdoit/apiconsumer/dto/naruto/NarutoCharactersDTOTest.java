package br.com.justdoit.apiconsumer.dto.naruto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class NarutoCharactersDTOTest {

    @Autowired
    private JacksonTester<NarutoCharactersDTO> jsonTest;

    @Test
    void deveSerializarOsObjetosParaJsonCorretamente() throws IOException {
        NarutoCharactersDTO obj = new NarutoCharactersDTO(1L, "Naruto Uzumaki", List.of("www.image.com"));

        JsonContent<NarutoCharactersDTO> resultado = jsonTest.write(obj);
        assertThat(resultado).hasJsonPathNumberValue("$.id");
        assertThat(resultado).extractingJsonPathNumberValue("$.id").isEqualTo(1);

        assertThat(resultado).hasJsonPathStringValue("$.name");
        assertThat(resultado).extractingJsonPathStringValue("$.name").isEqualTo("Naruto Uzumaki");
    }

    @Test
    void deveDesserializarJsonParaObjetoCorretamente() throws IOException {
        String jsonBruto = """
                {
                    "id": 1,
                    "name": "Naruto Uzumaki",
                    "images": ["url1.jpg"]
                }
                """;

        NarutoCharactersDTO resultadoObjeto = jsonTest.parseObject(jsonBruto);

        assertThat(resultadoObjeto.id()).isEqualTo(1L);
        assertThat(resultadoObjeto.name()).isEqualTo("Naruto Uzumaki");
        assertThat(resultadoObjeto.images()).hasSize(1).contains("url1.jpg");
    }

    @Test
    void deveRetornarTrueObjetosIguais() {
        NarutoCharactersDTO obj = new NarutoCharactersDTO(1L, "Naruto Uzumaki", List.of("www.image.com"));
        NarutoCharactersDTO obj2 = new NarutoCharactersDTO(1L, "Naruto Uzumaki", List.of("www.image.com"));

    }
}
