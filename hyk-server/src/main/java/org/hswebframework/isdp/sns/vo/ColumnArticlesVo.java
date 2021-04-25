package org.hswebframework.isdp.sns.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.isdp.sns.entity.SnsArticle;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.JDBCType;

@Getter
@Setter
public class ColumnArticlesVo implements Serializable {
    /**
     * 专栏id
     */
    private String columnId;


    /**
     * 文章id
     */
    private String articleId;

    /**
     * 价格设置
     */
    private String isPurchase;
    /**
     * 文章内容
     */
    private SnsArticle snsArticle;
}
