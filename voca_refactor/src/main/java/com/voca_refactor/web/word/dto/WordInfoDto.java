package com.voca_refactor.web.word.dto;

import lombok.*;

/**
 * todo validation
 */
public class WordInfoDto {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class AddWordForm {
        private String spelling;
        private String mean;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class EditWordForm {
        private Integer wordId;
        private String spelling;
        private String mean;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class DeleteWordForm {
        private Integer wordId;
    }
}
