package com.voca_refactor.domain.word.dto;

import com.voca_refactor.domain.word.domain.Word;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EditWordDto {
    private Integer wordId;
    private String spelling;
    private String mean;

    public Word toEntity() {
        return Word.builder()
                .wordId(wordId)
                .spelling(spelling)
                .mean(mean)
                .build();
    }
}