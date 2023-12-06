package ru.vtb.vtbtask.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrequencyRequestDto {
    @NotNull
    @JsonProperty("input")
    private String input;
}
