package com.spring.xue.Exception;

import com.spring.xue.core.ResultCode;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private ResultCode code;

    public GlobalException(ResultCode code) {
        this.code = code;
    }
}
