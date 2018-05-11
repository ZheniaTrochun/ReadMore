package com.yevhenii.kpi.readmore.api.google.converter;

import com.google.common.base.Strings;
import com.yevhenii.kpi.readmore.api.google.model.GoogleBookResponse;
import com.yevhenii.kpi.readmore.api.google.model.ImageLinks;
import com.yevhenii.kpi.readmore.api.google.model.VolumeInfo;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.utils.properties.AppPropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

//import org.apache.logging.log4j.util.Strings;

@Component
public class GoogleBookResponseToBookConverter implements Function<GoogleBookResponse, Book> {

    private final String defaultImage;


    @Autowired
    public GoogleBookResponseToBookConverter(AppPropertyHolder holder) {
        this.defaultImage = holder.getGooglebooks().getDefaultImage();
    }


    @Override
    public Book apply(GoogleBookResponse googleBookResponse) {
        VolumeInfo volumeInfo = googleBookResponse.getVolumeInfo();

        return Book
                .builder()
                .name(volumeInfo.getTitle())
                .author(constructAuthor(volumeInfo))
                .genre(volumeInfo.getMainCategory())
                .imageUrl(constructImage(volumeInfo))
                .year(constructYear(volumeInfo))
                .description(volumeInfo.getDescription())
                .build();
    }

    private String constructAuthor(VolumeInfo volumeInfo) {
        return Objects.isNull(volumeInfo.getAuthors()) ?
                "-" : String.join(", ", volumeInfo.getAuthors());
    }

    private Integer constructYear(VolumeInfo volumeInfo) {
        if (Objects.isNull(volumeInfo) ||
                Objects.isNull(volumeInfo.getPublishedDate()) ||
                volumeInfo.getPublishedDate().isEmpty()) {

            return null;
        }

        String[] dateArr = volumeInfo.getPublishedDate().split("-");

        return dateArr.length == 0 ? null : Integer.parseInt(dateArr[0]);
    }

    private String constructImage(VolumeInfo volumeInfo) {
        ImageLinks imageLinks = volumeInfo.getImageLinks();

        if (Objects.isNull(imageLinks)) {
            return defaultImage;
        }

        if (!Strings.isNullOrEmpty(imageLinks.getLarge())) {
            return imageLinks.getLarge();
        }

        if (!Strings.isNullOrEmpty(imageLinks.getThumbnail())) {
            return imageLinks.getThumbnail();
        }

        return defaultImage;
    }

}
