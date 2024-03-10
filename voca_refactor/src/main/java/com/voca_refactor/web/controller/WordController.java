package com.voca_refactor.web.controller;

import com.voca_refactor.domain.word.domain.Word;
import com.voca_refactor.domain.word.dto.AddWordDto;
import com.voca_refactor.domain.word.dto.DeleteWordDto;
import com.voca_refactor.domain.word.dto.EditWordDto;
import com.voca_refactor.domain.word.service.WordService;
import com.voca_refactor.web.util.response.CommonResponse;
import com.voca_refactor.web.util.response.PagingResponse;
import com.voca_refactor.web.util.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * todo PageableDefault 예외 처리
 * word API 권한 체크
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    @GetMapping()
    public ResponseEntity<PagingResponse<Word>> words(
            @PageableDefault(sort = "spelling", size = 20) Pageable pageable,
            @RequestParam(required = false) String searchCond) {
        Page<Word> words = (searchCond == null) ? wordService.findAll(pageable) : wordService.findAllByCond(searchCond, pageable);
        return ResponseEntity.ok().body
                (new PagingResponse<>(HttpStatus.ACCEPTED, "success!", words));
    }

    @PostMapping()
    public ResponseEntity<SingleResponse<Word>> addWord(@RequestBody AddWordDto form) {
        Word savedWord = wordService.save(form);
        return ResponseEntity.ok().body(
                new SingleResponse<>(HttpStatus.ACCEPTED, "success!", savedWord)
        );
    }

    @PatchMapping()
    public ResponseEntity<SingleResponse<Word>> editWord(@RequestBody EditWordDto form) {
        Word updatedWord = wordService.update(form);
        return ResponseEntity.ok().body(
                new SingleResponse<>(HttpStatus.ACCEPTED, "success!", updatedWord)
        );
    }

    @DeleteMapping()
    public ResponseEntity<CommonResponse> deleteWord(@RequestBody DeleteWordDto form) {
        wordService.delete(form);
        return ResponseEntity.ok().body(
                new CommonResponse(HttpStatus.ACCEPTED, "success!")
        );
    }
}
