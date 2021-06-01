package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.ezorm.rdb.mapping.defaults.SaveResult;
import org.hswebframework.isdp.sns.entity.SnsArticle;
import org.hswebframework.isdp.sns.service.SnsArticlesService;
import org.hswebframework.isdp.sns.vo.StateVo;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.annotation.SaveAction;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PatchMapping("_save")
    @SaveAction
    @Operation(summary = "保存数据", description = "如果传入了id,并且对应数据存在,则尝试覆盖,不存在则新增.")
    public Mono<SaveResult> save1(@RequestBody Mono<SnsArticle> payload) {
         return Authentication
                .currentReactive()
                .zipWith(payload)
                 .map(tp2 ->{
                     SnsArticle snsArticle= tp2.getT2();
                     snsArticle.setArticleAuthorId(tp2.getT1().getUser().getId());
                    return snsArticle;
                 })
                 .as(getRepository()::save);
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
    @GetMapping("/getArticleCounts")
    @Operation(summary = "查询某id名下的所有文章，带分类")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<Long> getArticleCounts(
            @Param(value = "id") String id,@Param(value = "type") String type) {
          return Authentication
                .currentReactive()
                .flatMap(tp2 ->{
                   return snsArticlesService
                            .createQuery()
                            .where("article_author_id",tp2.getUser().getId())
                            .and("articletype",type)
                            .fetch()
                            .count();
                });


    }


}
