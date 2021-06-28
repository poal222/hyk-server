package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.Comment;
import org.hswebframework.web.api.crud.entity.GenericTreeSortSupportEntity;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;
import org.hswebframework.web.validator.CreateGroup;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.JDBCType;
import java.util.List;

@Table(
        name = "industryClasssfiy",
        indexes = {@Index(
                name = "idx_classfiy_path",
                columnList = "path"
        )}
)
@Getter
@Setter
public class IndustryClasssfiyEntity extends GenericTreeSortSupportEntity<String> {

    @Comment("行业分类名称")
    @Column(
            length = 32
    )
    @Schema(
            description = "行业分类名称"
    )
    @NotBlank(
            message = "名称不能为空",
            groups = {CreateGroup.class}
    )
    private String name;

    @Comment("描述")
    @Column(
            length = 256
    )
    @Schema(
            description = "说明"
    )
    private String describe;


    @Schema(
            description = "子节点"
    )
    private List<IndustryClasssfiyEntity> children;



}
