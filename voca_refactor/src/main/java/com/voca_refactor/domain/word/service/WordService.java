package com.voca_refactor.domain.word.service;

import com.voca_refactor.domain.word.domain.Word;
import com.voca_refactor.domain.word.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class WordService {
    private final WordRepository wordRepository;
    public Page<Word> findAllByCond(String searchCond, Pageable pageable) {
        return wordRepository.findBySpellingContainingOrMeanContaining(searchCond, searchCond, pageable);
    }

    public Page<Word> findAll(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public Word save(Word word) {
        Word savedWord = wordRepository.save(word);
        return savedWord;
    }

    public Word update(Word word) {
        Word findWord = wordRepository.findById(word.getWordId()).orElse(null);
        findWord.setSpelling(word.getSpelling());
        findWord.setMean(word.getMean());
        return findWord;
    }

    public void delete(Word word) {
        wordRepository.delete(word);
    }
}
