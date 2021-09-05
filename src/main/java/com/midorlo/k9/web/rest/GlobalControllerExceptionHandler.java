//package com.midorlo.k9.rest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@RestControllerAdvice
//public class GlobalControllerExceptionHandler {
//
//   @ExceptionHandler(RuntimeException.class)
//   @ResponseStatus(HttpStatus.BAD_REQUEST)
//   public ResponseEntity<String> handle400(RuntimeException ex, HttpServletRequest request) {
//      String trace = Arrays.stream(ex.getStackTrace())
//                           .map(StackTraceElement::toString)
//                           .collect(Collectors.joining(System.lineSeparator()));
//      String rip = request.getRemoteAddr();
//      return new ResponseEntity<>("<h1>BAD REQUEST</h1>"
//      + rip + ", "
//         + trace, HttpStatus.BAD_REQUEST);
//   }
//
//   @ResponseStatus(HttpStatus.UNAUTHORIZED)
//   public ResponseEntity<String> handle401(RuntimeException ex) {
//      return new ResponseEntity<>("401", HttpStatus.UNAUTHORIZED);
//   }
//
//   @ResponseStatus(HttpStatus.NOT_FOUND)
//   public ResponseEntity<String> handleBookNotFound(RuntimeException ex) {
//      return new ResponseEntity<>("<h1>404</h1>", HttpStatus.NOT_FOUND);
//   }
//}
