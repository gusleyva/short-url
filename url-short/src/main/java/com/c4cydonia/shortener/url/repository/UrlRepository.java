package com.c4cydonia.shortener.url.repository;

import com.c4cydonia.shortener.url.model.Url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
