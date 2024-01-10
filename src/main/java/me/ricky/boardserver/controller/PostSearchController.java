package me.ricky.boardserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PostSearchController.BASE_URI)
@Slf4j
@RequiredArgsConstructor
public class PostSearchController {
    public static final String BASE_URI = "/post/search";
}
