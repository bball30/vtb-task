package ru.vtb.vtbtask.services;


import java.util.Map;

public interface FrequencyService {
    Map<Character, Integer> calculateFrequency(String input);
}
