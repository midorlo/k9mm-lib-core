//package com.midorlo.k9.web.rest;
//
//import com.midorlo.k9.domain.util.ApplicationEntity;
//import com.midorlo.k9.repository.ApplicationRepository;
//import com.midorlo.k9.service.util.K9Service;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.util.List;
//
//import static com.midorlo.k9.web.RestMapper.generatePaginationHttpHeaders;
//
//
///**
// * Rest Controller Template providing quick ways to implement typical CRUD for Abstract Entities.
// *
// * @param <E> entity scope.
// */
//public abstract class ApplicationController<E extends ApplicationEntity, R extends ApplicationRepository<E>, S extends K9Service<E, R>> {
//
//   /**
//    * Autowired Entity Service Class.
//    */
//   protected final S service;
//
//   /**
//    * Default Constructor.
//    *
//    * @param service injected from boot.
//    */
//   protected ApplicationController(S service) {
//      this.service = service;
//   }
//
//   /**
//    * Default implementation for findAll.
//    * NOT enabled by default. To enable it, do override the method and set any Mapping Annotation.
//    *
//    * @param pageable pageable scope.
//    * @return Response Entity.
//    */
//   public ResponseEntity<List<E>> findAll(Pageable pageable) {
//      Page<E>     page    = service.findAll(pageable);
//      HttpHeaders headers = generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//      return ResponseEntity.ok().headers(headers).body(page.getContent());
//   }
//}
