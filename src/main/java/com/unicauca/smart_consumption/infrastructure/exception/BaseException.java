package com.unicauca.smart_consumption.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  private final int status;
  private final String errorCode;
  private final String messageDetail;

  protected BaseException(int status, String errorCode, String message) {
    super(message);
    this.status = status;
    this.errorCode = errorCode;
    this.messageDetail = "";
  }

}
