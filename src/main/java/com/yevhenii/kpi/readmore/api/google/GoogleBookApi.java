package com.yevhenii.kpi.readmore.api.google;

import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.api.RemoteBookApi;
import com.yevhenii.kpi.readmore.api.google.converter.GoogleBookResponseToBookResponseConverter;
import com.yevhenii.kpi.readmore.api.google.model.GoogleBookResponse;
import com.yevhenii.kpi.readmore.api.google.model.GoogleBooksHolder;
import com.yevhenii.kpi.readmore.utils.properties.AppPropertyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GoogleBookApi implements RemoteBookApi {

    private final RestTemplate restTemplate;

    private final String googleBookApiUrl;

    private GoogleBookResponseToBookResponseConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleBookApi.class);

    @Autowired
    public GoogleBookApi(RestTemplate restTemplate,
                         AppPropertyHolder propertyHolder,
                         GoogleBookResponseToBookResponseConverter converter) {

        this.restTemplate = restTemplate;
        this.googleBookApiUrl = "https://www.googleapis.com/books/v1/volumes%s&key=" +
                propertyHolder.getGooglebooks().getKey();
        this.converter = converter;
    }

    @Override
    public List<BookResponse> getBooksByNameAndAuthor(String name, String author) {

        ResponseEntity<GoogleBooksHolder> booksHolder =
                restTemplate.getForEntity(constructApiUrl(name, author), GoogleBooksHolder.class);

        if (!checkResponse(booksHolder)) {
            return new ArrayList<>();
        }

        return booksHolder
                .getBody()
                .getItems()
                .stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponse> getBookById(String id) {
        ResponseEntity<GoogleBookResponse> book =
                restTemplate.getForEntity(constructApiUrl(id), GoogleBookResponse.class);

        if (!checkSingleResponse(book)) {
            return Optional.empty();
        }

        return Optional.of(
                converter.apply(
                        book.getBody()));
    }

    @Override
    public Optional<BookResponse> getOneBookByNameAndAuthor(String name, String author) {

        return getBooksByNameAndAuthor(name, author)
                .stream()
                .findFirst();
    }

    private String constructApiUrl(String name, String author) {
        return String.format(googleBookApiUrl,
                String.format("?q=\"%s\"+inauthor:\"%s\"&projection=full&printType=books", name, author));
    }

    private String constructApiUrl(String id) {
        return String.format(googleBookApiUrl,
                String.format("/%s", id));
    }

    private Boolean checkResponse(ResponseEntity<GoogleBooksHolder> entity) {
        return entity.getStatusCode() == HttpStatus.OK
                && !Objects.isNull(entity.getBody())
                && !Objects.isNull(entity.getBody().getItems());
    }

    private Boolean checkSingleResponse(ResponseEntity entity) {
        return entity.getStatusCode() == HttpStatus.OK
                && !Objects.isNull(entity.getBody());
    }
}
