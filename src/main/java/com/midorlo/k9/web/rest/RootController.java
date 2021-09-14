package com.midorlo.k9.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @RequestMapping("")
    public ResponseEntity<String> handleRootRequest() {
        return ResponseEntity.ok("Hi, I'm Batman");
    }
}
