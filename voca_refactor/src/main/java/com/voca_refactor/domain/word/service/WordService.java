package com.voca_refactor.domain.word.service;

import com.voca_refactor.domain.word.domain.Word;
import com.voca_refactor.domain.word.dto.AddWordDto;
import com.voca_refactor.domain.word.dto.DeleteWordDto;
import com.voca_refactor.domain.word.dto.EditWordDto;
import com.voca_refactor.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;
    public Page<Word> findAllByCond(String searchCond, Pageable pageable) {
        return wordRepository.findBySpellingContainingOrMeanContaining(searchCond, searchCond, pageable);
    }

    public Page<Word> findAll(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public Word save(AddWordDto word) {
        return wordRepository.save(word.toEntity());
    }

    public Word update(EditWordDto form) {
        Word findWord = wordRepository.findById(form.getWordId()).orElse(null);
        if (findWord != null) {
            findWord.updateWord(form.getSpelling(), form.getMean());
        }
        return findWord;
    }

    public void delete(DeleteWordDto form) {
        wordRepository.delete(form.toEntity());
    }
}
