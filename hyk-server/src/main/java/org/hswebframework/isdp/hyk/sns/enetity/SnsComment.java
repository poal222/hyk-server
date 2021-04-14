package org.hswebframework.isdp.hyk.sns.enetity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.isdp.hyk.sns.enmus.SnsEnmus;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;
import java.util.Date;

@Table(name = "SNS_COMMENT")
@Getter
@Setter
public class SnsComment extends GenericEntity<String> {

    /**
     * 评论内容
     */
    @Column
    @Schema(description = "评论内容")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String commentContent ;
    /**
     * 创建时间
     */
    @Column(updatable = false)
    @Schema(description = "回帖创建时间", accessMode = Schema.AccessMode.READ_ONLY)
    @DefaultValue(generator = Generators.CURRENT_TIME)//逻辑默认值
    private Date commentCreateTime ;
    /**
     * 评论者id
     */
    @Column(length = 64)
    @Schema(description = "评论者id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String commentAuthorId ;
    /**
     * 评论主题id
     */
    @Column(length = 64)
    @Schema(description = "评论主题id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String commentOnArticleId ;
    /**
     * 回帖带锚点 URL
     */
    @Column
    @Schema(description = "回帖带锚点 URL")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String commentSharpURL ;
    /**
     *回复针对回帖 id，即 父回帖 id
     */
    private String commentOriginalCommentId ;
    /**
     *
     */
    @Column(length = 15)
    @Schema(description = "评论状态 0：正常，1：封禁")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentStatus ;
    /**
     * 评论者ip
     */
    @Column(length = 64)
    @Schema(description = "评论者ip")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String commentIP ;
    /**
     * 客户端信息
     */
    @Column(length = 128)
    @Schema(description = "客户端信息")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String commentUA ;
    /**
     * 匿名访问
     */
    @Column(length = 64)
    @Schema(description = "匿名访问")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentAnonymous ;
    /**
     * 回帖感谢计数
     */
    @Column(length = 64)
    @Schema(description = "回帖感谢计数")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentThankCnt ;

    /**
     * 回帖点赞计数
     */
    @Column(length = 64)
    @Schema(description = "回帖点赞计数")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentGoodCnt ;
    /**
     * 回帖点踩计数
     */
    @Column(length = 64)
    @Schema(description = "回帖点踩计数")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentBadCnt ;
    /**
     * 回帖 Reddit 算法评分
     */
    @Column(length = 64)
    @Schema(description = "回帖 Reddit 算法评分")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    private String commentScore ;
    /**
     * 回复计数
     */
    @Column(length = 64)
    @Schema(description = "回复计数")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @EnumCodec
    private SnsEnmus commentReplyCnt ;

    /**
     * 回帖语音文件 URL
     */
    @Column
    @Schema(description = "回帖语音文件 URL")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String commentAudioURL ;
    /**
     * 0：回帖未被采纳，1：回帖已被采纳（仅作用于问答帖）
     */
    @Column
    @Schema(description = "0：回帖未被采纳，1：回帖已被采纳（仅作用于问答帖）")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String commentQnAOffered  ;
    /**
     * 0：所有人可见，1：仅楼主和自己可见
     */
    @Column
    @Schema(description = "0：所有人可见，1：仅楼主和自己可见")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    private String commentVisible  ;
}
