package com.voca_refactor.web.word.controller;

import com.voca_refactor.domain.word.domain.Word;
import com.voca_refactor.domain.word.service.WordService;
import com.voca_refactor.web.member.util.response.CommonResponse;
import com.voca_refactor.web.member.util.response.PagingResponse;
import com.voca_refactor.web.member.util.response.SingleResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.voca_refactor.web.word.dto.WordInfoDto.*;

/**
 * todo PageableDefault 예외 처리
 * word API 권한 체크
 */
@Slf4j
@RestController
@AllArgsConstructor
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
    public ResponseEntity<SingleResponse<Word>> addWord(@RequestBody AddWordForm form) {
        Word savedWord = wordService.save(new Word(form.getSpelling(), form.getMean()));
        return ResponseEntity.ok().body(
                new SingleResponse<>(HttpStatus.ACCEPTED, "success!", savedWord)
        );
    }

    @PatchMapping()
    public ResponseEntity<SingleResponse<Word>> editWord(@RequestBody EditWordForm form) {
        Word updatedWord = wordService.update(new Word(form.getWordId(), form.getSpelling(), form.getMean()));
        return ResponseEntity.ok().body(
                new SingleResponse<>(HttpStatus.ACCEPTED, "success!", updatedWord)
        );
    }

    @DeleteMapping()
    public ResponseEntity<CommonResponse> deleteWord(@RequestBody DeleteWordForm form) {
        wordService.delete(new Word(form.getWordId()));
        return ResponseEntity.ok().body(
                new CommonResponse(HttpStatus.ACCEPTED, "success!")
        );
    }
}