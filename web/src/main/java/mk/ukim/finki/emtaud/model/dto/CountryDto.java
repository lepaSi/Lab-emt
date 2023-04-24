package mk.ukim.finki.emtaud.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private String name;

    private String continent;

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
