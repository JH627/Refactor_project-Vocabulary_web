package com.voca_refactor.domain.word.dto;

import com.voca_refactor.domain.word.domain.Word;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DeleteWordDto {
    private Integer wordId;

    public Word toEntity() {
        return Word.builder()
                .wordId(wordId)
                .build();
    }

}
