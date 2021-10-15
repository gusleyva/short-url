package com.c4cydonia.shortener.url.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(description = "Request object for POST method")
public class UrlFullRequest {
    @ApiModelProperty(required = true, notes = "Url to convert to short")
    private String fullUrl;

    @ApiModelProperty(required = true, notes = "Short url")
    private String shortUrl;

    @ApiModelProperty(notes = "Expiration datetime of url")
    private Date expiresDate;

    @ApiModelProperty(notes = "Action to execute when full url is requested")
    private String webHook;

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }
}
