package org.hswebframework.isdp.platform.logging.service;


import org.hswebframework.isdp.platform.logging.entity.SerializableAccessLog;
import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.hswebframework.web.logging.AccessLoggerInfo;
import org.springframework.stereotype.Service;

/**
 * @see AccessLoggerInfo
 */
@Service
public class SerializableAccessLogService extends GenericReactiveCrudService<SerializableAccessLog,String> {


}
