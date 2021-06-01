package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sdqysb.entity.*;
import org.hswebframework.isdp.sdqysb.service.*;
import org.hswebframework.isdp.sdqysb.vo.CompWfInfoVo;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/sdsb/CompInfo")
@RestController
@Resource(id = "sdsb-CompCrudController", name = "企业填报信息管理")
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
    private CompIpoInfoService compIpoInfoService;
    /**
     * 企业上市计划表
     **/
    @Autowired
    private CompStockService compStockService;

    @Override
    public ReactiveRepository<CompBasicInfo, String> getRepository() {
        return compBasicInfoService.getRepository();
    }

    /**
     * 保存填报信息
     *
     * @return 保存填报信息
     */
    @PostMapping(value = "/saveCompInfo")
    @Operation(summary = "保存填报信息")
    @Transactional
    public Flux<Object> saveCompInfo(@RequestBody CompWfInfoVo compWfInfoVo) {
        String version = String.valueOf(System.currentTimeMillis());
        if (compWfInfoVo.getCompBasicInfo().getVersion() != null && !"".equals(compWfInfoVo.getCompBasicInfo().getVersion())) {
            version = compWfInfoVo.getCompBasicInfo().getVersion();
        }

        String compId =compWfInfoVo.getCompBasicInfo().getCompId();

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
                compStockService.save(Mono.just(compWfInfoVo.getCompStock()))
        );

    }

    /**
     * 查询某版本的企业上报信息（审批等）
     *
     * @param version 版本hao
     * @return 用户详情
     */
    @GetMapping(value = "/queryCompInfoByVersion")
    @Operation(summary = "查询某版本的企业上报信息")
    public Mono<CompWfInfoVo> queryCompInfoByVersion(
            @Parameter(name = "compId", description = "企业ID") @RequestParam(name = "compId") String compId,
            @Parameter(name = "version", description = "版本") @RequestParam(name = "version") String version) {
        CompWfInfoVo compInfoVo = new CompWfInfoVo();
        return Mono.zip(
                compBasicInfoService.createQuery()
                        .where("version", version)
                        .and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompBasicInfo())),
                compBusinessInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetch().switchIfEmpty(Mono.just(new CompBusinessInfo())).collectList(),
                compFinanceService.createQuery().where("version", version).and("comp_id", compId)
                        .fetch().switchIfEmpty(Mono.just(new CompFinance())).collectList(),
                compHonorInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompHonorInfo())),
                compIpoInfoService.createQuery().where("version", version).and("comp_id", compId)
                        .fetch().switchIfEmpty(Mono.just(new CompIpoInfo())).collectList(),
                compPersonInfoServicee.createQuery().where("version", version).and("comp_id", compId)
                        .fetch().switchIfEmpty(Mono.just(new CompPersonInfo())).collectList(),
                compAuditingService.createQuery().where("version", version).and("comp_id", compId)
                        .fetchOne().switchIfEmpty(Mono.just(new CompAuditing())))
                .map(objects -> {
                    compInfoVo.setCompBasicInfo(objects.getT1());
                    compInfoVo.setCompBusinessInfoList(objects.getT2());
                    compInfoVo.setCompFinanceList(objects.getT3());
                    compInfoVo.setCompHonorInfo(objects.getT4());
                    compInfoVo.setCompIpoInfoList(objects.getT5());
                    compInfoVo.setPersonInfoList(objects.getT6());
                    compInfoVo.setCompAuditing(objects.getT7());

                    return compInfoVo;
                }).zipWith(
                        compQualityService.createQuery().where("version", version).and("comp_id", compId)
                                .fetch().switchIfEmpty(Mono.just(new CompQuality())).collectList(),
                        ((compWfInfoVo, compQuality) -> {
                            compWfInfoVo.setCompQualityList(compQuality);
                            return compWfInfoVo;
                        })
                ).zipWith(
                        compStandardService.createQuery().where("version", version).and("comp_id", compId)
                                .fetch().switchIfEmpty(Mono.just(new CompStandard())).collectList(),
                        ((compWfInfoVo, compStandard) -> {
                            compWfInfoVo.setCompStandardList(compStandard);
                            return compWfInfoVo;
                        })
                ).zipWith(
                        compStockService.createQuery().where("version", version).and("comp_id", compId)
                                .fetchOne().switchIfEmpty(Mono.just(new CompStock())),
                        ((compWfInfoVo, compStock) -> {
                            compWfInfoVo.setCompStock(compStock);
                            return compWfInfoVo;
                        })
                );
    }

}
