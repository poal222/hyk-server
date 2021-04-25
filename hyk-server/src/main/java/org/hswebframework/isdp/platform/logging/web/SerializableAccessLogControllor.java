package org.hswebframework.isdp.platform.logging.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.platform.logging.entity.SerializableAccessLog;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.logging.AccessLoggerInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see AccessLoggerInfo
 */
@AllArgsConstructor
@RestController
@RequestMapping("/isdp/syslog")
@Tag(name = "系统日志")
public class SerializableAccessLogControllor implements ReactiveCrudController<SerializableAccessLog, String> {


	private final ReactiveRepository<SerializableAccessLog, String> repository;
	@Override
	public ReactiveRepository<SerializableAccessLog, String> getRepository() {
		return repository;
	}
}
