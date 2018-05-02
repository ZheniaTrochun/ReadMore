package com.yevhenii.kpi.readmore.api.google.converter;

import com.yevhenii.kpi.readmore.api.google.model.ImageLinks;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.api.google.model.GoogleBookResponse;
import com.yevhenii.kpi.readmore.api.google.model.VolumeInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class GoogleBookResponseToBookResponseConverter implements Function<GoogleBookResponse, BookResponse> {

//    todo move to properties
    private final static String DEFAULT_IMAGE = "";

    @Override
    public BookResponse apply(GoogleBookResponse googleBookResponse) {
        VolumeInfo volumeInfo = googleBookResponse.getVolumeInfo();

        return BookResponse
                .builder()
                .id(googleBookResponse.getId())
                .name(volumeInfo.getTitle())
                .author(String.join(", ", volumeInfo.getAuthors()))
                .genre(volumeInfo.getMainCategory())
                .imageLink(constructImage(volumeInfo))
                .year(constructYear(volumeInfo))
                .description(volumeInfo.getDescription())
                .build();
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

//    todo create default link
    private String constructImage(VolumeInfo volumeInfo) {
        ImageLinks imageLinks = volumeInfo.getImageLinks();

        if (Objects.isNull(imageLinks)) {
            return DEFAULT_IMAGE;
        }

        if (Strings.isNotBlank(imageLinks.getLarge())) {
            return imageLinks.getLarge();
        }

        if (Strings.isNotBlank(imageLinks.getThumbnail())) {
            return imageLinks.getThumbnail();
        }

        return DEFAULT_IMAGE;
    }
}
