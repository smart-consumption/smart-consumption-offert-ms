package com.unicauca.smart_consumption.infrastructure.handler;

import static java.util.Objects.nonNull;

import com.unicauca.smart_consumption.domain.common.ApiErrorDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global Exception Handler to manage various exception types.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {


  /**
   * Exception handler for validation errors.
   * This method handles `MethodArgumentNotValidException` and returns a `ResponseDto` containing the validation errors.
   *
   * @param ex the `MethodArgumentNotValidException` thrown during validation
   * @return a `ResponseDto` containing the list of `ApiErrorDto` objects representing the validation errors
   */
  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<List<ApiErrorDto>> handleException(MethodArgumentNotValidException ex) {
    List<ApiErrorDto> result = ex.getBindingResult().getAllErrors().stream()
        .map(this::mapError).toList();
    return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader
        .getInstance().getMessage(MessagesConstant.EM009), result);
  }

  /**
   * Maps an `ObjectError` to an `ApiErrorDto`.
   * If the error is a `FieldError`, it includes the field name and a localized error message.
   * Otherwise, it returns a generic error message.
   *
   * @param objectError the error object to be mapped
   * @return an `ApiErrorDto` representing the validation error
   */
  private ApiErrorDto mapError(ObjectError objectError) {
    if (objectError instanceof FieldError field) {
      return new ApiErrorDto(field.getField(),
          MessageLoader.getInstance().getMessage(objectError.getDefaultMessage(),
              field.getField(), nonNull(field.getArguments())
                  &&
                  field.getArguments().length >= 2 ? field.getArguments()[1] : null));
    }
    return new ApiErrorDto(objectError.getObjectName(), MessageLoader.getInstance().getMessage(MessagesConstant.EM001));
  }


  /**
   * Handles exceptions.
   * Logs the error message and returns a response for this specific exception.
   *
   * @param e The BusinessRuleException instance.
   * @return Response entity containing error details.
   */
  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ResponseDto<Object>> handleBusinessRuleException(BusinessRuleException e) {
    return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), e.getMessage()).of();
  }

  /**
   * Handles MissingServletRequestParameterException.
   * Logs the error and returns a response entity with error details.
   *
   * @param ex The MissingServletRequestParameterException instance.
   * @return Response entity containing error details.
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<Void> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
    return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.EM003, ex.getParameterName()));
  }

  /**
   * Handles RuntimeException.
   * Logs the error and returns a response entity with error details.
   *
   * @param ex The RuntimeException instance.
   * @return Response entity containing error details.
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<Void> handleRuntimeException(RuntimeException ex) {
    return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.EM001));
  }


  /**
   * Handles `MethodArgumentTypeMismatchException` by returning a response with a 400 BAD REQUEST status.
   *
   * @param ex the exception thrown when a method argument type mismatch occurs.
   * @return a response with the error details.
   */

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.EM011, ex.getPropertyName(),
            Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
  }

}
