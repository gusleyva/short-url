package com.c4cydonia.shortener.url.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import com.c4cydonia.shortener.url.common.BaseConversion;
import com.c4cydonia.shortener.url.dto.UrlFullRequest;
import com.c4cydonia.shortener.url.model.Url;
import com.c4cydonia.shortener.url.repository.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private BaseConversion conversion;

    public UrlFullRequest convertToShortUrl(UrlFullRequest request) {
        var url = new Url();
        url.setFullUrl(request.getFullUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        var shortUrl = conversion.encode(entity.getId()); //unique id, it will be a unique conversion
        request.setShortUrl(shortUrl);
        return request;
    }

    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var url = urlRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (url.getExpiresDate() != null && url.getExpiresDate().before(new Date())){
            urlRepository.delete(url);
            throw new EntityNotFoundException("Link expired!");
        }

        return url.getFullUrl();
    }
}
