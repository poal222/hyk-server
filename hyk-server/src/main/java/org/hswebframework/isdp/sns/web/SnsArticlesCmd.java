package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsArticle;
import org.hswebframework.isdp.sns.service.SnsArticlesService;
import org.hswebframework.isdp.sns.vo.StateVo;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Payload;
import java.io.Serializable;

/**
 * 图文，短讯息和长文的管理
 */
@Tag(name = "文章管理", description = "文章管理")
@RestController
@RequestMapping("/sns/articles")
@Resource(id = "sns-articles", name = "文章管理")
@Authorize
public class SnsArticlesCmd implements ReactiveCrudController<SnsArticle, String> {

    @Autowired
    private SnsArticlesService snsArticlesService;

    @Override
    public ReactiveRepository<SnsArticle, String> getRepository() {
        return snsArticlesService.getRepository();
    }

    @PostMapping("/changeState")
    @Operation(summary = "逻辑删除，更改状态，1：可用，0：不可用", description = "逻辑删除，更改状态，1：可用，0：不可用")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<Integer> changeState(
            @RequestBody StateVo stateVo) {

        return snsArticlesService
                .createUpdate()
                .set("articlestatus", stateVo.getArticlestatus())
                .where("id", stateVo.getId())
                .execute()
                .defaultIfEmpty(1);
    }


}
