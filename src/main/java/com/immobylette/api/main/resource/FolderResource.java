package com.immobylette.api.main.resource;

import com.immobylette.api.main.dto.FolderDto;
import com.immobylette.api.main.dto.FolderSummaryDto;
import com.immobylette.api.main.exception.FolderNotFoundException;
import com.immobylette.api.main.exception.GCPStorageException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@AllArgsConstructor
public class FolderResource {
    private final RestClient restClient;

    public FolderDto getFolder(UUID id) throws FolderNotFoundException, GCPStorageException {
        ResponseEntity<FolderDto> result = restClient.get()
                .uri("/api/v1/folders/{id}/photos", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new FolderNotFoundException(id);
                    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                        throw new GCPStorageException("gcp error");
                    }
                })
                .toEntity(FolderDto.class);

        return result.getBody();
    }

    public FolderSummaryDto getFolderSummary(UUID id) throws FolderNotFoundException, GCPStorageException {
        ResponseEntity<FolderSummaryDto> result = restClient.get()
                .uri("/api/v1/folders/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new FolderNotFoundException(id);
                    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                        throw new GCPStorageException("gcp error");
                    }
                })
                .toEntity(FolderSummaryDto.class);

        return result.getBody();
    }
}
