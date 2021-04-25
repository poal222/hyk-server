package org.hswebframework.isdp.sns.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

/**
 * sns 广告表
 */
@Table(name = "SNS_AD")
@Getter
@Setter
public class SnsAd extends GenericEntity<String> {

	/** 文章主题id */
	@Column(length = 64, nullable = false)
	@Schema(description = "投放广告的文章主题id")
	private String articleId;

	/** 广告位置 */
	@Column(length = 3, nullable = false)
	@Schema(description = "广告位置：0：下方；1：上方，2：左方；4：上方")
	private String adPosition;

	/** 广告内容 */
	@Column
	@ColumnType(jdbcType = JDBCType.LONGVARCHAR)
	@Schema(description = "广告投放内容")
	private String cotent;

}
