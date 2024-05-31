package com.immobylette.api.main.resource;

import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.dto.PhotoUrlDto;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.exception.PhotoNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PhotoResource {
    private final RestClient restClient;
    private final RestTemplate restTemplate;

    public PhotoUrlDto getPhoto(String id) throws PhotoNotFoundException, GCPStorageException {
        ResponseEntity<PhotoDto> result = null;
            result = restClient.get()
                    .uri(String.format("api/v1/photos/%s", id))
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            throw new PhotoNotFoundException(id);
                        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                            throw new GCPStorageException("gcp error");
                        }
                    })
                    .toEntity(PhotoUrlDto.class);

        return result.getBody();
    }

    public UUID uploadPhotos(List<PhotoDto> photos) throws GCPStorageException {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

        final HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);

        photos.forEach(photo -> data.add("photos", photo.getFile().getResource()));

        data.add("descriptions", new HttpEntity<>(photos.stream().map(PhotoDto::getDescription).toList(), jsonHeaders));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(data, headers);

        ResponseEntity<UUID> response = restTemplate.exchange("/api/v1/folders", HttpMethod.POST, requestEntity, UUID.class);

        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new GCPStorageException("gcp error");
        }

        return response.getBody();
    }
}
