//package org.hswebframework.example.crud.platform.token;
//
//import org.hswebframework.web.authorization.Authentication;
//import org.hswebframework.web.authorization.basic.web.GeneratedToken;
//import org.hswebframework.web.authorization.basic.web.UserTokenGenerator;
//import org.hswebframework.web.id.IDGenerator;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Map;
//
///**
// * @author 王岗
// */
//@Component
//public class IsdpPlatformTokenGenerator implements UserTokenGenerator {
//
//	private int timeout = 30 * 60 * 1000;
//
//	@Override
//	public String getSupportTokenType() {
//		// 登录时,请求参数为 token_type = token 时,此配置生效
//		return "Isdp-Token";
//	}
//
//	@Override
//	public GeneratedToken generate(Authentication authentication) {
//		String token = IDGenerator.MD5.generate();
//
//		return new GeneratedToken() {
//			@Override
//			public Map<String, Object> getResponse() {
//				//返回给前端的参数,前端拿到此参数后自行处理
//				return Collections.singletonMap("token", token);
//			}
//
//			@Override
//			public String getToken() {
//				return token;
//			}
//
//			@Override
//			public String getType() {
//				return "Isdp-Token";
//			}
//
//			@Override
//			public int getTimeout() {
//				// token 超时时间,超过时间没有请求时,自动失效
//				return timeout;
//			}
//		};
//	}
//}
