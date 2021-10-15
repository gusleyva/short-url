package com.c4cydonia.shortener.url.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.c4cydonia.shortener.url.common.URLValidator;
import com.c4cydonia.shortener.url.dto.UrlFullRequest;
import com.c4cydonia.shortener.url.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import java.net.URI;

@RestController
@RequestMapping("/api/shorturl/v1")
public class UrlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private UrlService urlService;

    @GetMapping(value="/")
    public String loadIndex() {
        LOGGER.info("Load index page");
        return "index";
    }

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("/shortUrl")
    public ResponseEntity<Object> convertToShortUrl(@RequestBody UrlFullRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + request.getFullUrl());

        var fullUrl = request.getFullUrl();
        if (URLValidator.INSTANCE.validateURL(fullUrl)) {
            return new ResponseEntity<Object>(urlService.convertToShortUrl(request), HttpStatus.OK);
        }
        throw new Exception("Please enter a valid URL");
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "/{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        LOGGER.info("Received shortened url to redirect: " + shortUrl);
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
