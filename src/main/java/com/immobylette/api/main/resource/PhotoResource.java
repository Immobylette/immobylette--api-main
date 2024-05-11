package com.immobylette.api.main.resource;

import com.immobylette.api.main.config.PhotoConfig;
import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.exception.PhotoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class PhotoResource {
    private final PhotoConfig photoConfig;
    private final RestClient restClient;

    public PhotoResource(PhotoConfig photoConfig) {
        this.photoConfig = photoConfig;

        this.restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl(this.photoConfig.getUrl())
//                .defaultHeader("My-Header", "Foo") TODO: Change this line to use authentication
                .build();
    }

    public PhotoDto getPhoto(UUID id) throws PhotoNotFoundException, GCPStorageException{
        ResponseEntity<PhotoDto> result = restClient.get()
                .uri("/api/v1/photos/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new PhotoNotFoundException(id);
                    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                        throw new GCPStorageException("gcp error");
                    }
                })
                .toEntity(PhotoDto.class);

        return result.getBody();
    }
}
