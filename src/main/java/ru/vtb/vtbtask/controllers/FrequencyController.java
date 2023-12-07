package ru.vtb.vtbtask.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.vtbtask.messages.FrequencyRequestDto;
import ru.vtb.vtbtask.services.FrequencyService;
import ru.vtb.vtbtask.validators.FrequencyRequestDtoValidator;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/frequency")
public class FrequencyController {

    private final FrequencyService frequencyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Character, Integer>> getFrequencies(@RequestBody FrequencyRequestDto input) {
        FrequencyRequestDtoValidator.validate(input);
        return ResponseEntity.status(HttpStatus.OK).body(frequencyService.calculateFrequency(input.getInput()));
    }
}
