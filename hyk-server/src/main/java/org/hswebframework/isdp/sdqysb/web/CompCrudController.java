package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sdqysb.entity.*;
import org.hswebframework.isdp.sdqysb.service.*;
import org.hswebframework.isdp.sdqysb.vo.CompInfoVo;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/sdsb/CompInfo")
@RestController
@Resource(id = "sdsb.CompCrudController", name = "企业填报信息管理")
@Tag(name = "企业填报信息管理")
public class CompCrudController implements ReactiveCrudController<CompBasicInfo, String> {
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

    @Override
    public ReactiveRepository<CompBasicInfo, String> getRepository() {
        return compBasicInfoService.getRepository();
    }
//            2、查询最新版本的上报信息
//3、保存填报信息

    /**
     * 查询某版本的企业上报信息（审批等）
     *
     * @param version 版本hao
     * @return 用户详情
     */
    @GetMapping(value = "/queryCompInfoByVersion")
    @Operation(summary = "查询某版本的企业上报信息")
    public Mono<CompInfoVo> queryCompInfoByVersion(
            @Parameter(name = "compId", description = "企业ID") @RequestParam(name = "compId") String compId,
            @Parameter(name = "version", description = "版本") @RequestParam(name = "version") String version) {
        CompInfoVo compInfoVo = new CompInfoVo();
        return Mono.zip(
                compBasicInfoService.createQuery()
                        .where("version", version)
                        .and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompBasicInfo())),
                compBusinessInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompBusinessInfo())),
                compFinanceService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompFinance())),
                compHonorInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompHonorInfo())),
                compIpoInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompIpoInfo())),
                compPersonInfoServicee.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompPersonInfo())),
                compAuditingService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompAuditing())))
                .map(objects -> {
                    CompBasicInfo compBasicInfo = objects.getT1();
                    compInfoVo.setCompId(compBasicInfo.getCompId());
                    compInfoVo.setUsccId(compBasicInfo.getUsccId());
                    compInfoVo.setRegTime(compBasicInfo.getRegTime());
                    compInfoVo.setRegCapital(compBasicInfo.getRegCapital());
                    compInfoVo.setRegCity(compBasicInfo.getRegCity());
                    compInfoVo.setRegCounty(compBasicInfo.getRegCounty());
                    compInfoVo.setRegAddress(compBasicInfo.getRegAddress());
                    compInfoVo.setCompNature(compBasicInfo.getCompNature());
                    compInfoVo.setIndustry(compBasicInfo.getIndustry());
                    compInfoVo.setCompIntroduction(compBasicInfo.getCompIntroduction());
                    compInfoVo.setStandardTypeId(compBasicInfo.getStandardTypeId());
                    compInfoVo.setStandardType(compBasicInfo.getStandardType());
                    compInfoVo.setIsGuidance(compBasicInfo.getIsGuidance());
                    compInfoVo.setIsOpen(compBasicInfo.getIsOpen());
                    compInfoVo.setIsDelete(compBasicInfo.getIsDelete());
                    compInfoVo.setCreateUserId(compBasicInfo.getCreateUserId());
                    compInfoVo.setCreateTime(compBasicInfo.getCreateTime());
                    compInfoVo.setLastUpdateTime(compBasicInfo.getLastUpdateTime());
                    compInfoVo.setVersion(compBasicInfo.getVersion());
                    compInfoVo.setStatus(compBasicInfo.getStatus());

                    CompBusinessInfo compBusinessInfoMono = objects.getT2();
                    compInfoVo.setMajorBussiness(compBusinessInfoMono.getMajorBussiness());
                    compInfoVo.setOwnStatus(compBusinessInfoMono.getOwnStatus());
                    compInfoVo.setIncomePercent(compBusinessInfoMono.getIncomePercent());
                    return compInfoVo;
                });
    }

}
