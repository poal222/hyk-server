package org.hswebframework.isdp.sns.web;

import org.hswebframework.isdp.hyk.sns.service.SnsFollowService;
import org.hswebframework.isdp.sns.entity.SnsComment;
import org.hswebframework.isdp.sns.entity.SnsFollow;
import org.hswebframework.isdp.sns.service.SnsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 评论管理
 * 1、获取某条微博的评论列表
 * 2、获取@到我的评论
 * 3、评论一条微博
 * 4、回复一条我收到的评论
 * 5、删除评论
 * 6、屏蔽评论
 */
@RestController
@RequestMapping("/sns/follow")
public class SnsCommentCmd {

    @Autowired
    private SnsCommentService snsCommentService;
    /**
     * 1、获取某条微博的评论列表 只查看有效状态的评论
     * @param articleId 文章id
      */
    @GetMapping("/comments/show")
    public Flux<SnsComment> showByid(String articleId){
        return snsCommentService.createQuery().where(SnsComment::getCommentOnArticleId,articleId)
                .and(SnsComment::getCommentStatus,0)
                .fetch();
    }

    /**
     * 获取@到我的评论
     * todo 没做@我
     * @param filter_by_author 作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
     *  @param filter_by_source  来源筛选类型，0：全部、1：来自微博的评论、2：来自微群的评论，默认为0。
     * @param since_id 若指定此参数，则返回ID比since_id大的评论（即比since_id时间晚的评论），默认为0。
     * @param max_id 若指定此参数，则返回ID小于或等于max_id的评论，默认为0。
     * @return
     */
    @GetMapping("/comments/mentions")
    public Flux<SnsComment> mentions(String filter_by_author,
                                     String filter_by_source,String since_id,String max_id
   ){
        return snsCommentService.createQuery().where(SnsComment::getCommentOnArticleId,filter_by_author)
                .and(SnsComment::getCommentStatus,0)
                .fetch();
    }
    /**
     * 评论一条微博
     */
    @GetMapping("/comments/create")
    public void create(SnsComment snsComment
    ){
         snsCommentService.save(Mono.just(snsComment));
    }
    /**
     * 回复一条我收到的评论
     */
    @GetMapping("/comments/reply")
    public void reply(SnsComment snsComment
    ){
        snsCommentService.save(Mono.just(snsComment));
    }
    /**
     * 删除评论
     */
    @GetMapping("/comments/delete")
    public void reply(String id
    ){
        snsCommentService.deleteById(Mono.just(id));
    }
    /**
     * 屏蔽评论
     */
    @GetMapping("/comments/refused")
    public void refused(String id
    ){
        snsCommentService.createUpdate()
    .set(SnsComment::getCommentStatus,1);
    }
}