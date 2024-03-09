package com.voca_refactor.domain.word.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordId;

    private String spelling;
    private String mean;

    public Word(String spelling, String mean) {
        this.spelling = spelling;
        this.mean = mean;
    }

    public Word(Integer wordId) {
        this.wordId = wordId;
    }
}
