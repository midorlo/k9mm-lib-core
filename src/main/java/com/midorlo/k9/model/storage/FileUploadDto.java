package com.midorlo.k9.model.storage;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@ToString
@Getter
@Slf4j
public class FileUploadDto {

    private final MultipartFile multipartFile;
    private final String originalFileName;
    private final String targetFileName;
    private final long sizeBytes;
    private final String extension;


    public FileUploadDto(MultipartFile multipartFile) {

        this.multipartFile = multipartFile;
        if (multipartFile == null) {
            throw new RuntimeException("multipart file must not be null");
        }

        this.originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null) {
            throw new RuntimeException("original filename must not be null");
        }

        this.targetFileName = toLocalFileName(originalFileName);
        if (targetFileName == null) {
            throw new RuntimeException("cannot generate local file name");
        }

        this.sizeBytes = multipartFile.getSize();
        if (sizeBytes == 0) {
            throw new RuntimeException("File empty or cannot get file size.");
        }

        this.extension = getExtension(originalFileName);
    }

    /**
     * Gets a file's extension.
     * <p>Via https://www.baeldung.com/java-file-extension#2-filenameutilsgetextension-from-apache-commons-io</p>
     *
     * @param filename name of the file.
     * @return extension String.
     */
    private static String getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse(null);
    }

    /**
     * Creates a UUID based on the given file name.
     *
     * @param originalFileName a filename String.
     * @return uuid as String.
     */
    private static String toLocalFileName(@NonNull String originalFileName) {
        return UUID.fromString(originalFileName).toString();
    }
}
