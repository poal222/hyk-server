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
 * 专栏文章
 */
@Table(name = "Sns_Column_Aritcles")
@Getter
@Setter
public class SnsColumnArticles extends GenericEntity<String> {
	/**
	 * 专栏id
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "专栏id")
	private String columnId;


	/**
	 * 文章id
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "文章id")
	private String articleId;

	/**
	 * 价格设置
	 */
	@Column(length = 3)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "价格设置：0，免费，1：付费")
	private String isPurchase;

}
