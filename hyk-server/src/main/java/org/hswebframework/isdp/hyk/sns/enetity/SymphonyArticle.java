package org.hswebframework.isdp.hyk.sns.enetity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

/**
 * sns 主题表实体
 */
@Table(name="SNS_ARTICLE")
@Getter
@Setter
public class SymphonyArticle extends GenericEntity<String> {

    /** 主题名称 */
    @Column(length = 64, nullable = false)
    @Schema(description = "主题名称")
    private String articleTitle ;
    /** 帖子标签 */
    @Column
    @Schema(description = "帖子标签，英文状态逗号分隔")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String articleTags ;
    /** 作者id */
    @Column(length = 64)
    @Schema(description = "作者id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleAuthorId ;
    /** 评论数 */
    @Column
    @Schema(description = "帖子回帖计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private Integer articleCommentCount ;
    /** 浏览计数 */
    @Column
    @Schema(description = "帖子浏览计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private Integer articleViewCount ;
    /** 正文内容 */
    @Column
    @Schema(description = "正文内容")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String articlecontent ;
    /** 帖子打赏区内容 */
    @Column(length = 254)
    @Schema(description = "帖子打赏区内容")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleRewardContent ;
    /** 打赏数值 */
    @Column
    @Schema(description = "帖子打赏积分")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private Integer articleRewardPoint ;
    /** 帖子访问路径 */
    @Column(length = 255)
    @Schema(description = "帖子访问路径")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlepermalink ;
    /** 创建时间 */
    @Column(length = 64)
    @Schema(description = "创建时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleCreateTime ;
    /** 帖子更新时间 */
    @Column(length = 64)
    @Schema(description = "帖子更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleUpdateTime ;
    /**  */
    @Column(length = 64)
    @Schema(description = "帖子最新回帖时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleLatestCmtTime ;
    /**  */
    @Column(length = 20)
    @Schema(description = "帖子最新回帖者用户名")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlelatestcmtername ;

    /** 帖子随机数，用于快速选择随机帖子 */
    @Column
    @Schema(description = "帖子随机数，用于快速选择随机帖子")
    @ColumnType(jdbcType = JDBCType.DOUBLE)
    private String articleRandomDouble ;
    /** 帖子是否可回帖 */
    @Column(length = 3)
    @Schema(description = "帖子是否可回帖")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleCommentable ;
    /**  */
    @Column(length = 64)
    @Schema(description = "作者id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String syncwithsymphonyclient ;

    /** 0：CodeMirror-Markdown*/
    @Column(length = 3)
    @Schema(description = "0：CodeMirror-Markdown")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleEditorType ;
    /** 0：正常，1：封禁，2：锁定 */
    @Column(length = 64)
    @Schema(description = "0：正常，1：封禁，2：锁定")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlestatus ;
    /** 文章类型  0：普通帖子，1：机要，2：同城广播，3：思绪*/
    @Column(length = 64)
    @Schema(description = "0：普通帖子，1：机要，2：同城广播，3：思绪")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articletype ;
    /** 帖子点赞计数 */
    @Column
    @Schema(description = "帖子点赞计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private String articlegoodcnt ;
    /** 帖子点踩计数 */
    @Column
    @Schema(description = "帖子点踩计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private String articlebadcnt ;




    /** 帖子收藏计数 */
    @Column
    @Schema(description = "帖子收藏计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private String articleCollectCnt ;
    /** 帖子关注计数 */
    @Column
    @Schema(description = "帖子关注计数")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    private String articleWatchCnt ;

    /** 帖子 Reddit 算法评分 */
    @Column
    @Schema(description = "帖子 Reddit 算法评分")
    @ColumnType(jdbcType = JDBCType.DOUBLE)
    private String redditscore ;
    /** 发帖 IP 所在城市 */
    @Column(length = 32)
    @Schema(description = "发帖 IP 所在城市")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlecity ;
    /**  发送客户端ip */
    @Column(length = 64)
    @Schema(description = "发送客户端ip")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleip ;



    /** UA */
    @Column(length = 255)
    @Schema(description = "UA")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleua ;

    /**  帖子置顶时间 */
    @Column(length = 20)
    @Schema(description = "帖子置顶时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleStick ;

    /** 0：非优选，1：优选 */
    @Column(length = 3)
    @Schema(description = "0：非优选，1：优选")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlePerfect ;

    /** 0：公开，1：匿名 */
    @Column(length = 3)
    @Schema(description = "0：公开，1：匿名")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleanonymous ;

    /** 0：允许不登录浏览， 1：登录才可浏览 */
    @Column(length = 3)
    @Schema(description = "0：允许不登录浏览， 1：登录才可浏览")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleAnonymousView ;
    /**  */
    @Column(length = 255)
    @Schema(description = "帖子语音文件 URL")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleaudiourl ;

    @Column(length = 255)
    @Schema(description = "问答悬赏积分（仅作用于问答帖）")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleQnAOfferPoint ;

    @Column(length = 3)
    @Schema(description = "周/月邮件推送优先级")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articlePushOrder ;

    @Column(length = 255)
    @Schema(description = "帖子首图地址")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleImg1URL ;

    @Column(length = 3)
    @Schema(description = "帖子是否在列表展示：0 不展示，1 展示")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String articleShowInList ;
   
}
