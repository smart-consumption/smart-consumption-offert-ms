package com.unicauca.smart_consumption.domain.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain to manage field errors.
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorDto implements Serializable {
  private String field;
  private String message;
}
