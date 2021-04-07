package org.hswebframework.isdp.hyk.platform.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.ezorm.rdb.exception.DuplicateKeyException;
import org.hswebframework.ezorm.rdb.metadata.RDBColumnMetadata;
import org.hswebframework.web.crud.web.ResponseMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 错误包装处理
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseMessage<?>> handleException(DecodingException decodingException) {
        Throwable cause = decodingException.getCause();
        if (cause != null) {
            if (cause instanceof JsonMappingException) {
                return handleException(((JsonMappingException) cause));
            }

            return Mono.just(ResponseMessage.error(400, "illegal_argument", cause.getMessage()));
        }
        return Mono.just(ResponseMessage.error(400, "illegal_argument", decodingException.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseMessage<?>> handleException(JsonMappingException decodingException) {
        Throwable cause = decodingException.getCause();
        if (cause != null) {
            if (cause instanceof IllegalArgumentException) {
                return Mono.just(ResponseMessage.error(400, "illegal_argument", cause.getMessage()));
            }
            return Mono.just(ResponseMessage.error(400, "illegal_argument", cause.getMessage()));
        }
        return Mono.just(ResponseMessage.error(400, "illegal_argument", decodingException.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseMessage<?>> handleException(DuplicateKeyException e) {
        List<String> columns = e
            .getColumns()
            .stream()
            .map(RDBColumnMetadata::getAlias)
            .collect(Collectors.toList());
        return Mono.just(ResponseMessage.error(400, "duplicate_key", "存在重复的数据").result(columns));
    }
}
