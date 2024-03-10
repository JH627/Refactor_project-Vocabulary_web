package com.voca_refactor.web.util.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

/**
 *  Page -> count 쿼리를 포함해서 페이징 기능 (일반 페이징)
 *  Slice -> count 쿼리 없이 다음 페이지만 확인 (더보기)
 */
@Getter
@RequiredArgsConstructor
public class PagingResponse<T> extends CommonResponse {

    private Page<T> data;

    public PagingResponse(HttpStatus status, String message, Page<T> dataSlice) {
        super(status, message);
        this.data = dataSlice;
    }
}
