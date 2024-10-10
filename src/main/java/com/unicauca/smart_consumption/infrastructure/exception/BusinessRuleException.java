package com.unicauca.smart_consumption.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessRuleException extends BaseException {

  public BusinessRuleException(int status, String errorCode, String message) {
    super(status, errorCode, message);
  }
}
