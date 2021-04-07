package org.hswebframework.isdp.hyk.platform.logging.service;

import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.hswebframework.web.logging.AccessLoggerInfo;
import org.hswebframework.isdp.hyk.platform.logging.entity.SerializableAccessLog;
import org.springframework.stereotype.Service;

/**
 * @see AccessLoggerInfo
 */
@Service
public class SerializableAccessLogService extends GenericReactiveCrudService<SerializableAccessLog,String> {


}
