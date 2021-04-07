//package org.hswebframework.example.crud.platform.token;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hswebframework.web.authorization.basic.web.UserTokenParser;
//import org.hswebframework.web.authorization.token.ParsedToken;
//import org.springframework.stereotype.Component;
//
//@Component
//public class IsdpPlatformUserTokenParser implements UserTokenParser {
//	@Override
//	public ParsedToken parseToken(javax.servlet.http.HttpServletRequest request) {
//		return null;
//	}
//	//	@Override
////	public ParsedToken parseToken(HttpServletRequest request) {
////		//获取参数中的token
////		String token = request.getParameter("token");
////
////		if(StringUtils.isEmpty(token))return null;
////
////		return () -> token;
////	}
//}
