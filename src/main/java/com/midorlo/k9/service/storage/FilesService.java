//package com.midorlo.k9.service.storage;
//
//import com.midorlo.k9.configuration.ApplicationProperties;
//import com.midorlo.k9.model.storage.FileUploadDto;
//import com.midorlo.k9.repository.storage.FileReferenceRepository;
//import com.midorlo.k9.service.ApplicationService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static com.midorlo.k9.model.security.mapper.FileReferenceMapper.toFileReference;
//
//@SuppressWarnings("unused")
//@Service
//@Slf4j
//public class FilesService extends ApplicationService<FileReference, FileReferenceRepository> {
//
//   private final ApplicationProperties properties;
//
//   public FilesService(FileReferenceRepository repository, ApplicationProperties properties) {
//      super(repository);
//      this.properties = properties;
//   }
//
//   /**
//    * Handles uploading a new file.
//    *
//    * @param dto transfer object of the file.
//    * @return database reference for the uploaded file.
//    * @throws IOException any problem handling the upload.
//    */
//   @Transactional
//   public FileReference handleFileUpload(FileUploadDto dto) throws IOException {
//      Path localFilePath = storeUploadedFile(dto);
//      FileReference fileReference = toFileReference(dto, localFilePath);
//      return create(fileReference);
//   }
//
//   private Path storeUploadedFile(FileUploadDto dto) throws IOException {
//      Path targetLocation = Paths.get(properties.getStorage().getBasedir()).resolve(dto.getTargetFileName());
//      long bytesCopied = Files.copy(dto.getMultipartFile().getInputStream(), targetLocation);
//      if (bytesCopied == 0) {
//         throw new IOException("Source file was empty.");
//      }
//      if (bytesCopied != dto.getSizeBytes()) {
//         throw new IOException("Invalid file size. Expected: " + dto.getSizeBytes() + ", Actual: " + bytesCopied);
//      }
//      return targetLocation;
//   }
//}
