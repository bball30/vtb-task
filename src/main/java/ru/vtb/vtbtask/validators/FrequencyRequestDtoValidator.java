package ru.vtb.vtbtask.validators;

import ru.vtb.vtbtask.exceptions.InvalidParamException;
import ru.vtb.vtbtask.messages.FrequencyRequestDto;

import java.security.InvalidParameterException;

public class FrequencyRequestDtoValidator {
    public static void validate(FrequencyRequestDto dto) throws InvalidParameterException {
        if (dto.getInput() == null) throw new InvalidParamException("Строка не может быть null!");

        if (dto.getInput().isEmpty()) throw new InvalidParamException("Строка не может быть пустой!");

    }
}
