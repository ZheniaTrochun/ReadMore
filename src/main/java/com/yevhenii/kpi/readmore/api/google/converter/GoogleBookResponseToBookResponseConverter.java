package com.yevhenii.kpi.readmore.api.google.converter;

import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.response.BookResponseBuilder;
import com.yevhenii.kpi.readmore.api.google.model.GoogleBookResponse;
import com.yevhenii.kpi.readmore.api.google.model.ImageLinks;
import com.yevhenii.kpi.readmore.api.google.model.VolumeInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class GoogleBookResponseToBookResponseConverter implements Function<GoogleBookResponse, BookResponse> {

    @Override
    public BookResponse apply(GoogleBookResponse googleBookResponse) {
        VolumeInfo volumeInfo = googleBookResponse.getVolumeInfo();

        Optional<ImageLinks> images = Optional.ofNullable(volumeInfo.getImageLinks());
        String imageLink = images.isPresent() ? images.get().getLarge() : "";

        return new BookResponseBuilder()
                .setId(googleBookResponse.getId())
                .setName(volumeInfo.getTitle())
                .setAuthor(String.join(", ", volumeInfo.getAuthors()))
                .setGenre(volumeInfo.getMainCategory())
                .setImageLink(imageLink)
                .setYear(Integer.parseInt(volumeInfo.getPublishedDate().split("-")[0]))
                .createBookResponse();
    }
}
