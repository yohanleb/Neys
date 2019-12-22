package com.yohan.neys.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    public String getPublishedAtFormatted() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Instant instant = Instant.parse(publishedAt);
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern( "'Le' E dd/MM 'à' HH:mm" )
                            .withLocale( Locale.FRANCE )
                            .withZone(ZoneId.systemDefault());
            return formatter.format(instant);
        } else {
            return publishedAt;
        }
    }

    public String getContent() {
        return content;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Article article = (Article) obj;
        return title.equals(article.title);
    }
}
