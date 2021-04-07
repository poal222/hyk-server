package org.hswebframework.isdp.hyk.platform.config;


import lombok.extern.slf4j.Slf4j;
import org.hswebframework.isdp.hyk.platform.logging.service.SerializableAccessLogService;
import org.hswebframework.web.logging.AccessLoggerInfo;
import org.hswebframework.web.logging.events.AccessLoggerAfterEvent;
import org.hswebframework.isdp.hyk.platform.logging.entity.SerializableAccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ISDP 框架 总配置类
 */
@Component
@Slf4j
public class IsdpConfiguration {

	@Autowired
	private SerializableAccessLogService serializableAccessLogService;

	@EventListener
	public void handleAccessLogger(AccessLoggerAfterEvent event){
		AccessLoggerInfo logInfo =event.getLogger();
		//提取日志并记录到数据库
		SerializableAccessLog serializableAccessLog = new SerializableAccessLog();
		serializableAccessLog.setAction(logInfo.getAction());
		serializableAccessLog.setId(logInfo.getId());
		serializableAccessLog.setMethod(logInfo.getMethod().getName());
		serializableAccessLog.setTarget(logInfo.getHttpMethod());
		serializableAccessLog.setContext(logInfo.getContext());
		serializableAccessLog.setRequestTime(logInfo.getRequestTime());
		serializableAccessLog.setResponseTime(logInfo.getResponseTime());
		serializableAccessLogService.save(Mono.just(serializableAccessLog)).subscribe();
	}

//	@Bean
//	public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> webServerFactoryWebServerFactoryCustomizer() {
//		//解决请求参数最大长度问题
//		return factory -> factory
//				.addServerCustomizers(httpServer -> httpServer
//						.httpRequestDecoder(spec -> {
//							spec.maxInitialLineLength(10240);
//							spec.maxHeaderSize(10240);
//							return spec;
//						}));
//	}
}
