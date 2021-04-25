package org.hswebframework.isdp.sns.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.dict.EnumDict;

@Getter
@AllArgsConstructor
public enum SnsEnmus implements EnumDict<String> {
	/** 状态 **/
	enabled("正常"), disabled("已禁用"), /** 点赞，差评 **/
	goodCnt("点赞"), badCnt("差评"), /** 匿名访问 **/
	anonymousEnabled("允许匿名访问"), anonymouseDisabled("不允许匿名访问"), /** 转发 **/
	commentreplycntDisabled("未转发"), commentreplycntEnabled("已转发"), /** 打赏 **/
	prepayDisabled("不能打赏"), prepayEnabled("能打赏");


	private final String text;

	@Override
	public String getValue() {
		return name();
	}

}
