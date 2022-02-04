package com.alzal.nadeulseoulbackend.domain.tag.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagCustomException extends RuntimeException {
    private ErrorStatusEnum errorStatusEnum;
}
