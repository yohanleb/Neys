package com.yohan.neys.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Article implements Serializable {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getPublishedAtFormatted() {
        Instant instant = Instant.parse(publishedAt);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern( "'Le' E dd/MM 'à' HH:mm" )
                        .withLocale( Locale.FRANCE )
                        .withZone(ZoneId.systemDefault());

        return formatter.format(instant);
    }

    public String getContent() {
        return content;
    }
}
