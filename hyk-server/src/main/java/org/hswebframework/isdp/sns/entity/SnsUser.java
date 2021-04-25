package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

/**
 * sns 自媒体用户，类似头条号，
 * 用来对自媒体的管理
 */
//@Table(name = "sns_users")
public class SnsUser extends GenericEntity<String> {

	/**
	 * 关联的实际账号
	 */
	private String userId;
	/**
	 * 实际姓名
	 */
	private String actualName;
	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 头像地址
	 */
	private String userAvatarURL;
	/**
	 * 用户自评标签
	 */
	private String userTags;
	/**
	 * 用户是否在线
	 */
	private String userOnlineFlag;
	/**
	 * 发帖计数
	 */
	private String userArticleCount;
	/**
	 * 回帖计数
	 */
	private String userCommentCount;
	/**
	 * 用户标签计数
	 */
	private String userTagCount;
	/**
	 * 0：正常，1：封禁
	 */
	private String userStatus;
	/** 积分余额 */
	private String userpoint;
	/** 累计消费积分 */
	private String userusedpoint;
	/** 0：参与积分余额排行榜，1：不参与积分余额排行榜 */
	private String userjoinpointrank;
	/** 0：参与积分消费排行榜，1：不参与积分消费排行榜 */
	private String userjoinusedpointrank;
	/**
	 * 最近一次发帖时间
	 */
	private String userLatestArticleTime;
	/**
	 * 最近一次回帖时间
	 */
	private String userLatestCmtTime;
	/**
	 * 最近一次登录时间
	 */
	private String userLatestLoginTime;
	/**
	 * 最近一次登录 IP
	 */
	private String userLatestLoginIP;
	/**
	 *最近一次签到时间
	 */
	private String userCheckinTime;
	/**
	 * 最长连续签到开始日期
	 */
	private String userLongestCheckinStreakStart;
	/**
	 * 最长连续签到结束日期
	 */
	private String userLongestCheckinStreakEnd;

	/**
	 * 当前连续签到开始日期
	 */
	private String userCurrentCheckinStreakStart;
	/**
	 * 当前连续签到结束日期
	 */
	private String userCurrentCheckinStreakEnd;

	/**
	 * 最长连续签到天数
	 */
	private String userLongestCheckinStreak;
	/**
	 * 当前连续签到天数
	 */
	private String userCurrentCheckinStreak;

	/** 用户皮肤 */
	private String userskin;
	/** 用户移动端皮肤 */
	private String usermobileskin;


	/**
	 * 用户所在国家
	 */
	private String userCountry;
	/**
	 * 用户所在省份
	 */
	private String userProvince;

	/**
	 * 用户所在城市
	 */
	private String userCity;
	/**
	 * 0：公开地理位置，1：不公开地理位置
	 */
	private String userGeoStatus;


	/** 0：公开 User-Agent，1：不公开 User-Agent */
	private String useruastatus;
	/** 0：启用 Web 通知，1：禁用 Web 通知 */
	private String usernotifystatus;
	/** 0：公开积分列表，1：不公开积分列表 */
	private String userpointstatus;
	/** 0：公开关注者列表，1：不公开关注者列表 */
	private String userfollowerstatus;
	/** 0：公开收藏帖子列表，1：不公开收藏帖子列表 */
	private String userfollowingarticlestatus;
	/** 0：公开关注帖子列表，1：不公开关注帖子列表 */
	private String userwatchingarticlestatus;
	/** 0：公开关注标签列表，1：不公开关注标签列表 */
	private String userfollowingtagstatus;
	/** 0：公开关注用户列表，1：不公开关注用户列表 */
	private String userfollowinguserstatus;
	/** 0：公开回帖列表，1：不公开回帖列表 */
	private String usercommentstatus;
	/** 0：公开帖子列表，1：不公开帖子列表 */
	private String userarticlestatus;
	/** 0：公开在线状态，1：不公开在线状态 */
	private String useronlinestatus;
	/** 0：启用邮件订阅，1：禁用邮件订阅 */
	private String usersubmailstatus;
	/** 用户订阅邮件最近推送时间 */
	private String usersubmailsendtime;
	/** 0：启用键盘快捷键，1：禁用键盘快捷键 */
	private String userkeyboardshortcutsstatus;
	/** 0：回帖后自动关注帖子，1：回帖后不自动关注帖子 */
	private String userreplywatcharticlestatus;
	/** 用户所选语言 */
	private String userlanguage;
	/** 用户所在时区 */
	private String usertimezone;

}
