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
import org.hswebframework.isdp.sdqysb.entity.*;
import org.hswebframework.isdp.sdqysb.service.*;
import org.hswebframework.isdp.sdqysb.vo.CompWfInfoVo;
import org.hswebframework.isdp.sdqysb.vo.NodeInfo;
import org.hswebframework.isdp.sdqysb.vo.WfNodeInfo;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;
import org.hswebframework.web.system.authorization.defaults.service.DefaultDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/sdsb/workflow")
@RestController
@Resource(id = "sdsb-CompWorkFlowController", name = "企业信息上报审批流程管理")
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
     * 资质
     **/
    @Autowired
    private CompQualityService compQualityService;
    /**
     * 标准
     **/
    @Autowired
    private CompStandardService compStandardService;
    /**
     * 企业上市计划表
     **/
    @Autowired
    private CompStockService compStockService;
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
                    Mono<UserDetail> userDetail = userDetailService.findUserDetail(userId);
                    return userDetail.flatMapMany(userDetail1 -> {
                        String coid = userDetail1.getCoId();
//                        // 查询待办
                        return getTodoListByCoId(coid);
                    });
                });
    }

    /**
     * 根据企业id查询当前组织需要审核的待办信息
     *
     * @return
     * @Param coid 企业id
     */
    private Flux<CompAuditing> getTodoListByCoId(String coid) {
        CompBasicInfo compBasicInfo = new CompBasicInfo();
        compBasicInfo.setCompId("1");
        return compBasicInfoService.createQuery()
                .and("status", "1")
                .fetch()
                .switchIfEmpty(Mono.just(compBasicInfo))
                .map(CompBasicInfo::getCompId)
                .switchIfEmpty(Flux.empty())
                .collectList()
                .flatMapMany(strings -> {
                    return compAuditingService.createQuery()
                            .where("this_auditing", coid)
                            .in("comp_id", strings)
                            .fetch()
                            .switchIfEmpty(Flux.empty());
                });


    }

    /**
     * 发起流程
     *
     * @return 用户详情
     */
    @PostMapping(value = "/startWorkFlowInstance")
    @Operation(summary = "发起流程")
    @Transactional
    public Flux<Object> startWorkFlowInstance(@RequestBody CompWfInfoVo compWfInfoVo) {

        String version = String.valueOf(System.currentTimeMillis());
        if (compWfInfoVo.getCompBasicInfo().getVersion() != null && !"".equals(compWfInfoVo.getCompBasicInfo().getVersion())) {
            version = compWfInfoVo.getCompBasicInfo().getVersion();
        }
        String compId = compWfInfoVo.getCompBasicInfo().getCompId();
        compWfInfoVo.getCompBasicInfo().setVersion(version);
        compWfInfoVo.getCompHonorInfo().setVersion(version);
        compWfInfoVo.getCompStock().setVersion(version);

        String finalVersion = version;
        compWfInfoVo.getPersonInfoList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });
        Flux<CompPersonInfo> periinnfLFux = Mono.just(compWfInfoVo.getPersonInfoList()).flatMapMany(Flux::fromIterable);

        compWfInfoVo.getCompBusinessInfoList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });
        Flux<CompBusinessInfo> bussinessFlux = Mono.just(compWfInfoVo.getCompBusinessInfoList()).flatMapMany(Flux::fromIterable);


        compWfInfoVo.getCompFinanceList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });

        Flux<CompFinance> finnalLst = Mono.just(compWfInfoVo.getCompFinanceList()).flatMapMany(Flux::fromIterable);

        compWfInfoVo.getCompQualityList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });
        Flux<CompQuality> qompQuality = Mono.just(compWfInfoVo.getCompQualityList()).flatMapMany(Flux::fromIterable);

        compWfInfoVo.getCompStandardList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });
        Flux<CompStandard> qtandard = Mono.just(compWfInfoVo.getCompStandardList()).flatMapMany(Flux::fromIterable);

        compWfInfoVo.getCompIpoInfoList().stream()
                .forEach(compBusinessInfo -> {
                    compBusinessInfo.setVersion(finalVersion);
                });
        Flux<CompIpoInfo> qipo = Mono.just(compWfInfoVo.getCompIpoInfoList()).flatMapMany(Flux::fromIterable);
        CompAuditing compAuditing = compWfInfoVo.getCompAuditing();
        // 提交公司
        compAuditing.setCompId(compId);
        return Flux.merge(
                compBasicInfoService.save(Mono.just(compWfInfoVo.getCompBasicInfo())),
                compHonorInfoService.save(Mono.just(compWfInfoVo.getCompHonorInfo())),

                compBusinessInfoService.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compBusinessInfoService.insertBatch(bussinessFlux.collectList())),


                compFinanceService.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compFinanceService.insertBatch(finnalLst.collectList())),

                compQualityService.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compQualityService.insertBatch(qompQuality.collectList())),
                compStandardService.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compStandardService.insertBatch(qtandard.collectList())),
                compIpoInfoService.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compIpoInfoService.insertBatch(qipo.collectList())),
                compPersonInfoServicee.createDelete().where("version", version).and("comp_id", compId)
                        .execute()
                        .then(compPersonInfoServicee.insertBatch(periinnfLFux.collectList())),
                compStockService.save(Mono.just(compWfInfoVo.getCompStock())),
                compAuditingService.save(createAuditing(compWfInfoVo, compAuditing))
        );

    }

    /**
     * 逐级驳回
     *
     * @return
     */
    @PostMapping(value = "/reject")
    @Operation(summary = "逐级驳回")
    public Mono<Integer> reject(@RequestBody WfNodeInfo wfNodeInfo) {
        CompAuditing compAuditing = wfNodeInfo.getCompAuditing();
        NodeInfo nodeInfo = wfNodeInfo.getNodeInfo();
        // 需要比对当前是在第几节点
        String thisNode = compAuditing.getThisAuditing();
        Boolean nodeFinsined = false;
        // 第一节点 驳回
        if (thisNode.equals(compAuditing.getFirstAuditing())) {
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            compAuditing.setFirstAuditingResult(nodeInfo.getResult());
            compAuditing.setFirstAuditingView(nodeInfo.getViewinfo());
            nodeFinsined = true;
        }
        // 第二节点驳回 且无第三级
        if (thisNode.equals(compAuditing.getSecondAuditing())) {
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
            //驳回到下级
            compAuditing.setThisAuditing(compAuditing.getFirstAuditing());
            nodeFinsined = true;
        }
        // 第三节点驳回
        if (thisNode.equals(compAuditing.getThirdAuditing())) {
            compAuditing.setThirdAuditingResult(nodeInfo.getResult());
            compAuditing.setThirdAuditingView(nodeInfo.getViewinfo());
            //驳回到下级 拨回到第二级
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            nodeFinsined = true;
        }
        if (nodeFinsined) {
            if (nodeFinsined) {
                nodeInfo.setStatus("2");
            }

            return Mono.zip(
                    compAuditingService.save(Mono.just(compAuditing)),
                    compBasicInfoService.createUpdate()
                            .where("comp_id", compAuditing.getCompId())
                            .and("version", compAuditing.getVersion())
                            .set("status", nodeInfo.getStatus())
                            .execute(),
                    (count, count1) -> count1
            );
        }
        return compAuditingService.save(Mono.just(compAuditing)).thenReturn(1);
    }


    /**
     * 下一步
     *
     * @return 用户详情
     */
    @PostMapping(value = "/nextNode")
    @Operation(summary = "下一步")
    public Mono<Integer> nextNode(@RequestBody WfNodeInfo wfNodeInfo) {

        CompAuditing compAuditing = wfNodeInfo.getCompAuditing();
        NodeInfo nodeInfo = wfNodeInfo.getNodeInfo();

        // 需要比对当前是在第几节点
        String thisNode = compAuditing.getThisAuditing();
        Boolean nodeFinsined = false;
        // 第一节点审核成功
        if (thisNode.equals(compAuditing.getFirstAuditing())) {
            compAuditing.setThisAuditing(compAuditing.getSecondAuditing());
            compAuditing.setFirstAuditingResult(nodeInfo.getResult());
            compAuditing.setFirstAuditingView(nodeInfo.getViewinfo());
        }
        // 第二节点审核成功 且无第三级 流程结束
        if (thisNode.equals(compAuditing.getSecondAuditing()) && compAuditing.getThirdAuditing() == null) {
            nodeFinsined = true;
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
        }
        // 第二节点审核成功 且有第三级
        if (thisNode.equals(compAuditing.getSecondAuditing()) && compAuditing.getThirdAuditing() != null) {
            compAuditing.setThisAuditing(compAuditing.getThirdAuditing());
            compAuditing.setSecondAuditingResult(nodeInfo.getResult());
            compAuditing.setSecondAuditingView(nodeInfo.getViewinfo());
        }
        // 第三节点审核成功  流程结束
        if (thisNode.equals(compAuditing.getThirdAuditing())) {
            nodeFinsined = true;
            compAuditing.setThirdAuditingResult(nodeInfo.getResult());
            compAuditing.setThirdAuditingView(nodeInfo.getViewinfo());

        }
        if (nodeFinsined) {
            nodeInfo.setStatus("3");
            return Mono.zip(
                    compAuditingService.save(Mono.just(compAuditing)),
                    compBasicInfoService.createUpdate()
                            .where("comp_id", compAuditing.getCompId())
                            .and("version", compAuditing.getVersion())
                            .set("status", nodeInfo.getStatus())
                            .execute(),
                    (count, count1) -> count1
            );
        }
        return compAuditingService.save(Mono.just(compAuditing)).thenReturn(1);
    }

    /**
     * 创建审批动态流程信息，
     * 创建规则如下
     * 【
     * 1、第一级审批人，取comp_user的recommend_id
     * 2、第二级审批人，需要根据recommend_id的机构类型，判断是否有第三级
     * 3、第三级，写死，就是SDSDFJRJDGLJ
     * 】
     *
     * @param compWfInfoVo
     * @param compAuditing
     * @return
     */
    private Mono<CompAuditing> createAuditing(CompWfInfoVo compWfInfoVo, CompAuditing compAuditing) {
        return compUserService.findById(compWfInfoVo.getCompBasicInfo().getCompId())
                .map(compUser -> {
                    //提报公司及版本信息
                    compAuditing.setCompId(compUser.getId());
                    compAuditing.setVersion(compWfInfoVo.getCompBasicInfo().getVersion());
                    // 第一级审核和当前节点审核再发起流程时重叠
                    compAuditing.setFirstAuditing(compUser.getRecommendId());
                    compAuditing.setThisAuditing(compUser.getRecommendId());
                    return compAuditing;
                }).flatMap(compAuditing1 -> {
                    return orgExtendService.findById(compAuditing.getFirstAuditing())
                            .map(organExtendEntity -> {
                                if ("organization".equals(organExtendEntity.getOrganType())) {
                                    compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                                }
                                if ("company".equals(organExtendEntity.getOrganType())) {
                                    compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                                }
                                return compAuditing;
                            })
                            .zipWith(dimensionService.findById(compAuditing.getFirstAuditing()))
                            .map(objects -> {
                                CompAuditing compAuditing2 = objects.getT1();
                                // 判断是否是省市县三级审批
                                if ("SDSDFJRJDGLJ".equalsIgnoreCase(compAuditing2.getSecondAuditing())) {
                                    return compAuditing;
                                }
                                DimensionEntity dimensionEntity = objects.getT2();
                                if (dimensionEntity.getLevel() == 2) {
                                    compAuditing.setSecondAuditing("SDSDFJRJDGLJ");
                                }
                                if (dimensionEntity.getLevel() == 3) {
                                    // 第三级
                                    // 第二级
                                    compAuditing.setSecondAuditing(dimensionEntity.getParentId());
                                    compAuditing.setThirdAuditing("SDSDFJRJDGLJ");
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
            @Parameter(name = "comId", description = "公司id") @Param(value = "comp_id") String comp_id,
            @Parameter(name = "version", description = "提交版本") @Param(value = "version") String version) {

        QueryParam queryParam = new QueryParam();
        queryParam.and("comp_id", TermType.eq, comp_id);
        queryParam.and("version", TermType.eq, version);
        return compAuditingService.createQuery()
                .setParam(queryParam)
                .fetch()
                .singleOrEmpty();
    }


    @GetMapping("/compareVersion")
    public Mono<List<String>> compareVersion(
            @Parameter(name = "version1", description = "版本1") @Param(value = "version1") String version1,
            @Parameter(name = "version2", description = "版本2") @Param(value = "version2") String version2) {
        List list = new ArrayList();
        return Mono.zip(
                compBasicInfoService.createQuery().where("version", version1).fetchOne(),
                compBasicInfoService.createQuery().where("version", version2).fetchOne(),
                (t1, t2) -> {
                    if (!t1.getCompName().equalsIgnoreCase(t2.getCompName()))
                        list.add(t1.getCompName() + "变更为" + t2.getCompName());
                    if (!t1.getUsccId().equalsIgnoreCase(t2.getUsccId()))
                        list.add(t1.getUsccId() + "变更为" + t2.getUsccId());
                    if (!t1.getRegAddress().equalsIgnoreCase(t2.getRegAddress()))
                        list.add(t1.getRegAddress() + "变更为" + t2.getRegAddress());
                    if (!t1.getRegTime().equalsIgnoreCase(t2.getRegTime()))
                        list.add(t1.getRegTime() + "变更为" + t2.getRegTime());
                    if (!t1.getRegCapital().equalsIgnoreCase(t2.getRegCapital()))
                        list.add(t1.getRegCapital() + "变更为" + t2.getRegCapital());
                    if (!t1.getRegCity().equalsIgnoreCase(t2.getRegCity()))
                        list.add(t1.getRegCity() + "变更为" + t2.getRegCity());
                    if (!t1.getRegCounty().equalsIgnoreCase(t2.getRegCounty()))
                        list.add(t1.getRegCounty() + "变更为" + t2.getRegCounty());
                    if (!t1.getCompNature().equalsIgnoreCase(t2.getCompNature()))
                        list.add(t1.getCompNature() + "变更为" + t2.getCompNature());
                    if (!t1.getIndustry().equalsIgnoreCase(t2.getIndustry()))
                        list.add(t1.getIndustry() + "变更为" + t2.getIndustry());
                    if (!t1.getCompIntroduction().equalsIgnoreCase(t2.getCompIntroduction()))
                        list.add(t1.getCompIntroduction() + "变更为" + t2.getCompIntroduction());
                    if (!t1.getStandardTypeId().equalsIgnoreCase(t2.getStandardTypeId()))
                        list.add(t1.getStandardTypeId() + "变更为" + t2.getStandardTypeId());
                    if (!t1.getIsGuidance().equalsIgnoreCase(t2.getIsGuidance()))
                        list.add(t1.getIsGuidance() + "变更为" + t2.getIsGuidance());
                    if (!t1.getIsOpen().equalsIgnoreCase(t2.getIsOpen()))
                        list.add(t1.getIsOpen() + "变更为" + t2.getIsOpen());

                    return list;
                }
        ).zipWith(compareBussinessInfo(version1,version2),(t1,t2)->{
            t1.add(t2);
            return t1;
        });
    }


    private Mono<List<String>> compareBussinessInfo(String version1, String version2) {
        List list = new ArrayList();
        return Mono.zip(
                compBusinessInfoService.createQuery().where("version", version1).fetch().collectList(),
                compBusinessInfoService.createQuery().where("version", version2).fetch().collectList()
        ).map(tuple -> {
            tuple.getT1().stream().forEach(compBusinessInfo -> {
                tuple.getT2().stream().forEach(compBusinessInfo1 -> {
                    if (!compBusinessInfo.getIncomePercent().equalsIgnoreCase(compBusinessInfo1.getIncomePercent()))
                        list.add(compBusinessInfo.getIncomePercent() + "变更为" + compBusinessInfo1.getIncomePercent());
                    if (!compBusinessInfo.getMajorBussiness().equalsIgnoreCase(compBusinessInfo1.getMajorBussiness()))
                        list.add(compBusinessInfo.getMajorBussiness() + "变更为" + compBusinessInfo1.getMajorBussiness());
                    if (!compBusinessInfo.getOwnStatus().equalsIgnoreCase(compBusinessInfo1.getOwnStatus()))
                        list.add(compBusinessInfo.getOwnStatus() + "变更为" + compBusinessInfo1.getOwnStatus());
                });
            });
            return list;
        });
    }
}
