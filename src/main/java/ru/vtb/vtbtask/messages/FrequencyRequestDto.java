package ru.vtb.vtbtask.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrequencyRequestDto {
    @NotNull
    @JsonProperty("input")
    private String input;
}
