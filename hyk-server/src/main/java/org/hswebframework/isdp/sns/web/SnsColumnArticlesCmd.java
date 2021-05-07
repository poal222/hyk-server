package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.executor.SyncSqlExecutor;
import org.hswebframework.ezorm.rdb.executor.wrapper.MapResultWrapper;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsColumnArticles;
import org.hswebframework.isdp.sns.service.SnsArticlesService;
import org.hswebframework.isdp.sns.service.SnsColumnArticlesService;
import org.hswebframework.isdp.sns.vo.ColumnArticlesVo;
import org.hswebframework.web.api.crud.entity.PagerResult;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @Autowired
    SyncSqlExecutor syncSqlExecutor;

    @Override
    public ReactiveRepository<SnsColumnArticles, String> getRepository() {
        return snsColumnArticlesService.getRepository();
    }

    @GetMapping("/changeState")
    @Operation(summary = "文章状态:0：审核不通过。1：审核通过，2：待审核，3：已保存，", description = "专文章状态:0：审核不通过。1：审核通过，2：待审核，3：已保存")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<Integer> changeState(@Parameter(name = "id", description = "文章id")@Param(value = "id") String id, @Parameter(name = "id", description = "要修改的状态")@Param(value = "state") Integer state) {
        return snsArticlesService
                .createUpdate()
                .set("articlestatus", state)
                .where("id", id)
                .execute()
                .defaultIfEmpty(1);
    }

    @GetMapping("/queryColumnArticlesState")
    @Operation(summary = "查询某状态的专栏文章，", description = "查询某状态的专栏文章:0：审核不通过。1：审核通过，2：待审核，3：已保存")
    public Mono<PagerResult<ColumnArticlesVo>>   queryColumnArticlesState(@Parameter(name = "id", description = "专栏 id") @Param(value = "id") String id, @Parameter(name = "state", description = "要查询的某专栏的某类型的文章列表") @Param(value = "state") Integer state) {
        // 包装结果集
        MapResultWrapper wrapper = MapResultWrapper.defaultInstance();

        List<Map<String, Object>> resultList =   syncSqlExecutor.select("select " +
                "column_id,article_Id,is_Purchase,article_author_id,article_tags,article_title,articlestatus " +
                "from sns_article t1,sns_column_aritcles t2\n" +
                "where t1.id=t2.article_Id " +
                "and t2.column_id=? " +
                "and t1.articlestatus=? " +
                "ORDER BY t1.article_update_time DESC",id,state);
        List<ColumnArticlesVo> templist = new ArrayList<ColumnArticlesVo>();
        //转成vo对象
        for (Map<String, Object> map:resultList){
            ColumnArticlesVo deviceAlterData = new ColumnArticlesVo();
            deviceAlterData.setColumnId((String) map.get("column_id"));
            deviceAlterData.setArticleId((String) map.get("article_Id"));
            deviceAlterData.setIsPurchase((String) map.get("is_Purchase"));
            deviceAlterData.getSnsArticle().setArticleAuthorId((String) map.get("article_author_id"));
            deviceAlterData.getSnsArticle().setArticleTags((String) map.get("article_tags"));
            deviceAlterData.getSnsArticle().setArticleTitle((String) map.get("article_title"));
            deviceAlterData.getSnsArticle().setArticlestatus((String) map.get("articlestatus"));
            templist.add(deviceAlterData);
        }
        PagerResult pagerResult = new PagerResult();
        pagerResult.setData(resultList);
        pagerResult.setPageIndex(1);
        pagerResult.setPageSize(10);
        pagerResult.setTotal(1000);
        return Mono.just(pagerResult);
    }

}