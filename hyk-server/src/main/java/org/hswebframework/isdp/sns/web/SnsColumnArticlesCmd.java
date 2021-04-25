package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsColumnArticles;
import org.hswebframework.isdp.sns.service.SnsArticlesService;
import org.hswebframework.isdp.sns.service.SnsColumnArticlesService;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 收费专栏-文章管理
 */
@RestController
@RequestMapping("/sns/column/articles")
@Resource(id = "column-articles", name = "收费专栏-文章管理")
@Authorize
@Tag(name = "收费专栏-文章管理")
public class SnsColumnArticlesCmd implements ReactiveCrudController<SnsColumnArticles, String> {


    @Autowired
    private SnsColumnArticlesService snsColumnArticlesService;
    @Autowired
    private SnsArticlesService snsArticlesService;

    @Override
    public ReactiveRepository<SnsColumnArticles, String> getRepository() {
        return snsColumnArticlesService.getRepository();
    }

    @GetMapping("/changeState")
    @Operation(summary = "文章状态:0：审核不通过。1：审核通过，2：待审核，3：已保存，", description = "专文章状态:0：审核不通过。1：审核通过，2：待审核，3：已保存")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<Integer> changeState(@Param(value = "id") String id, @Param(value = "state") Integer state) {
        return snsArticlesService
                .createUpdate()
                .set("status", state)
                .where("id", id)
                .execute()
                .defaultIfEmpty(1);
    }

    @GetMapping("/queryColumnArticlesState")
    @Operation(summary = "查询某状态的专栏文章，", description = "查询某状态的专栏文章:0：审核不通过。1：审核通过，2：待审核，3：已保存")
    public Mono<Integer> queryColumnArticlesState(@Parameter(name = "id", description = "专栏 id") @Param(value = "id") String id, @Parameter(name = "state", description = "要查询的某专栏的某类型的文章列表") @Param(value = "state") Integer state) {
        return snsColumnArticlesService.createQuery()
				.where("columnId",id)
				.fetch()
				.zipWithIterable()

				snsArticlesService
                .createUpdate()
                .set("status", state)
                .where("id", id)
                .execute()
                .defaultIfEmpty(1);
    }

}