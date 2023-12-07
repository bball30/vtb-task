package ru.vtb.vtbtask.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    @Override
    public Map<Character, Integer> calculateFrequency(String input) {
        // создаем словарь символов и их частот
        Map<Character, Integer> frequencyMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));

        // возвращаем отсортированный словарь
        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new)
                );
    }
}
