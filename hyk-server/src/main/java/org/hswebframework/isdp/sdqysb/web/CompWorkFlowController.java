package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.core.param.QueryParam;
import org.hswebframework.ezorm.core.param.TermType;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.organization.UserDetailService;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.isdp.organization.organ.service.OrgExtendService;
import org.hswebframework.isdp.sdqysb.entity.CompAuditing;
import org.hswebframework.isdp.sdqysb.entity.CompBasicInfo;
import org.hswebframework.isdp.sdqysb.service.*;
import org.hswebframework.isdp.sdqysb.vo.CompWfInfoVo;
import org.hswebframework.isdp.sdqysb.vo.NodeInfo;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.system.authorization.defaults.service.DefaultDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/sdsb/workflow")
@RestController
@Resource(id = "sdsb.CompWorkFlowController", name = "企业信息上报审批流程管理")
@Tag(name = "企业信息上报审批流程管理")
public class CompWorkFlowController implements ReactiveCrudController<CompBasicInfo, String> {
    /**
     * 企业基本信息表
     **/
    @Autowired
    private CompBasicInfoService compBasicInfoService;
    /**
     * 企业业务构成表
     **/
    @Autowired
    private CompBusinessInfoService compBusinessInfoService;
    /**
     * 企业审核表
     **/
    @Autowired
    private CompAuditingService compAuditingService;
    /**
     * 企业财务表
     **/
    @Autowired
    private CompFinanceService compFinanceService;
    /**
     * 企业荣誉表
     **/
    @Autowired
    private CompHonorInfoService compHonorInfoService;
    /**
     * 企业人员信息表
     **/
    @Autowired
    private CompPersonInfoServicee compPersonInfoServicee;
    /**
     * 企业用户-详情表
     **/
    @Autowired
    private CompUserService compUserService;
    /**
     * 企业上市计划表
     **/
    @Autowired
    private CompIpoInfoService compIpoInfoService;


    @Autowired
    private DefaultDimensionService dimensionService;
    /**
     * 机构扩展表
     **/
    @Autowired
    private OrgExtendService orgExtendService;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public ReactiveRepository<CompBasicInfo, String> getRepository() {
        return compBasicInfoService.getRepository();
    }

    /**
     * 根据登陆id查询他的审批待办
     *
     * @return 用户详情
     */
    @PostMapping(value = "/todoList")
    @Operation(summary = "查询当前用户所在单位的待办")
    public Flux<CompAuditing> queryTodoList() {
        return Authentication
                .currentReactive()
                .flatMapMany(auth -> {
                    String userId = auth.getUser().getId();
                    Mono<UserDetail> userDetail =  userDetailService.findUserDetail(userId);
                    return userDetail.flatMapMany(userDetail1 -> {
                        String coid = userDetail1.getCoId();
//                        // 查询待办
                        return  getTodoListByCoId(coid);
                });
                });
   }

    /**
     * 根据企业id查询当前组织需要审核的待办信息
     * @Param coid 企业id
     * @return
     */
    private Flux<CompAuditing> getTodoListByCoId(String coid){

        return  compAuditingService.createQuery()
                .where("this_auditing",coid)
                .fetch()
                .switchIfEmpty(Flux.empty());
    }

    /**
     * 根据登陆id查询他的审批待办
     *
     * @return 用户详情
     */
    @PostMapping(value = "/startWorkFlowInstance")
    @Operation(summary = "发起流程")
    public Flux<Integer> startWorkFlowInstance(@RequestBody CompWfInfoVo compWfInfoVo) {
        CompAuditing compAuditing = compWfInfoVo.getCompAuditing();
        return Flux.merge(
                compBasicInfoService.save(Mono.just(compWfInfoVo.getCompBasicInfo())),
                compBusinessInfoService.save(Mono.just(compWfInfoVo.getCompBusinessInfo())),
                compFinanceService.save(Mono.just(compWfInfoVo.getCompFinance())),
                compHonorInfoService.save(Mono.just(compWfInfoVo.getCompHonorInfo())),
                compIpoInfoService.save(Mono.just(compWfInfoVo.getCompIpoInfo())),
                compPersonInfoServicee.save(Mono.just(compWfInfoVo.getCompPersonInfo())),
                compAuditingService.save(createAuditing(compWfInfoVo,compAuditing))
        ).map(saveResult -> saveResult.getTotal());
    }

    /**
     * 逐级驳回
     *
     * @return
     */
    @PostMapping(value = "/reject")
    @Operation(summary = "逐级驳回")
    public Mono<Integer> reject(@RequestBody CompAuditing compAuditing, NodeInfo nodeInfo) {

        // 需要比对当前是在第几节点
        String thisNode = compAuditing.getThisAuditing();
        Boolean nodeFinsined = false;
        // 第一节点 驳回
        if(thisNode.equals(compAuditing.getFirstAuditing())){
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            compAuditing.setFirstAuditingResult(nodeInfo.getResult());
            compAuditing.setFirstAuditingView(nodeInfo.getViewinfo());
            nodeFinsined = true;
        }
        // 第二节点驳回 且无第三级
        if(thisNode.equals(compAuditing.getSecondAuditing())){
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
            //驳回到下级
            compAuditing.setThisAuditing(compAuditing.getFirstAuditing());
            nodeFinsined = true;
        }
        // 第三节点驳回
        if(thisNode.equals(compAuditing.getThirdAuditing())){
            compAuditing.setThirdAuditingResult(nodeInfo.getResult());
            compAuditing.setThirdAuditingView(nodeInfo.getViewinfo());
            //驳回到下级 拨回到第二级
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            nodeFinsined = true;
        }
        if(nodeFinsined){
            return Mono.zip(
                    compAuditingService.save(Mono.just(compAuditing)),
                    compBasicInfoService.createUpdate()
                            .where("comp_id",compAuditing.getCompId())
                            .and("version",compAuditing.getVersion())
                            .set("status",nodeInfo.getStatus())
                            .execute(),
                    (count, count1) -> count1
            );
        }
        return  compAuditingService.save(Mono.just(compAuditing)).thenReturn(1);
    }


    /**
     * 下一步
     *
     * @return 用户详情
     */
    @PostMapping(value = "/nextNode")
    @Operation(summary = "下一步")
    public Mono<Integer> nextNode(@RequestBody CompAuditing compAuditing, NodeInfo nodeInfo) {

       // 需要比对当前是在第几节点
        String thisNode = compAuditing.getThisAuditing();
        Boolean nodeFinsined = false;
        // 第一节点审核成功
        if(thisNode.equals(compAuditing.getFirstAuditing())){
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            compAuditing.setFirstAuditingResult(nodeInfo.getResult());
            compAuditing.setFirstAuditingView(nodeInfo.getViewinfo());
        }
        // 第二节点审核成功 且无第三级 流程结束
        if(thisNode.equals(compAuditing.getSecondAuditing()) && compAuditing.getThirdAuditing() == null){
            nodeFinsined = true;
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
        }
        // 第二节点审核成功 且有第三级
        if(thisNode.equals(compAuditing.getSecondAuditing()) && compAuditing.getThirdAuditing() != null){
            compAuditing.setThisAuditing(compAuditing.getThirdAuditing());
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
        }
        // 第三节点审核成功  流程结束
        if(thisNode.equals(compAuditing.getThirdAuditing())){
            nodeFinsined = true;
            compAuditing.setThirdAuditingResult(nodeInfo.getResult());
            compAuditing.setThirdAuditingView(nodeInfo.getViewinfo());
        }
        if(nodeFinsined){
            return Mono.zip(
                    compAuditingService.save(Mono.just(compAuditing)),
                    compBasicInfoService.createUpdate()
                            .where("comp_id",compAuditing.getCompId())
                            .and("version",compAuditing.getVersion())
                            .set("status",nodeInfo.getStatus())
                            .execute(),
                    (count, count1) -> count1
            );
        }
            return  compAuditingService.save(Mono.just(compAuditing)).thenReturn(1);
    }
    private Mono<CompAuditing> createAuditing(CompWfInfoVo compWfInfoVo, CompAuditing compAuditing){
        return  compUserService.findById(compWfInfoVo.getCompBasicInfo().getCompId())
                .map(compUser -> {
                    compAuditing.setCompId(compUser.getId());
                    // 第一级审核和当前节点审核再发起流程时重叠
                    compAuditing.setFirstAuditing(compUser.getRecommendId());
                    compAuditing.setThisAuditing(compUser.getRecommendId());
                    return compAuditing;
                }).doOnNext(compAuditing1 -> {
            orgExtendService.findById(compAuditing1.getFirstAuditing())
                    .map(organExtendEntity -> {
                        if ("organization".equals(organExtendEntity.getOrganType().getValue())) {
                            compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                        }
                        if ("company".equals(organExtendEntity.getOrganType().getValue())) {
                            compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                        }
                        // 省市县审批
                        if ("district".equals(organExtendEntity.getOrganType().getValue())) {

                            return Mono.zip(
                                    orgExtendService.findById(compAuditing1.getFirstAuditing()),
                                    dimensionService.findById(compAuditing1.getFirstAuditing()),
                                    (p1, p2) -> {
                                        if (p2.getLevel() == 2) {
                                            compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                                        }
                                        if (p2.getLevel() == 3) {
                                            // 第三级
                                            // 第二级
                                            compAuditing.setSecondAuditing(p2.getParentId());
                                            compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                                        }
                                        return compAuditing;
                                    }
                            );
                        }
                        return compAuditing;
                    });
        });
    }

    /**
     * 查询某个版本，某个公司的审批信息
     *
     * @param comp_id 公司id
     * @param version 提交版本
     * @return 审批信息
     */
    @GetMapping(value = "/queryAuditInfoByVersionAndComId")
    @Operation(summary = "查询某个版本，某个公司的审批信息")
    public Mono<CompAuditing> startWorkFlowInstance(
            @Parameter(name = "comId",description = "公司id") @Param(value = "comp_id") String comp_id,
            @Parameter(name = "version",description = "提交版本") @Param(value = "version") String version) {

        QueryParam queryParam = new QueryParam();
        queryParam.and("comp_id", TermType.eq,comp_id);
        queryParam.and("version", TermType.eq,version);
        return compAuditingService.createQuery()
                .setParam(queryParam)
                .fetch()
                .singleOrEmpty();
    }
}
