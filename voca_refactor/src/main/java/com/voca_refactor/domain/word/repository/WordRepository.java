package com.voca_refactor.domain.word.repository;

import com.voca_refactor.domain.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    Page<Word> findBySpellingContainingOrMeanContaining(String spelling, String mean, Pageable pageable);
}
