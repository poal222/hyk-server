package org.hswebframework.isdp.organization;

import lombok.AllArgsConstructor;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.isdp.organization.entity.UserDetailEntity;
import org.hswebframework.web.bean.FastBeanCopier;
import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.validator.ValidatorUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserDetailService extends GenericReactiveCrudService<UserDetailEntity, String> {

    private final ReactiveUserService userService;


    private final static UserDetailEntity emptyDetail = new UserDetailEntity();

    public Mono<UserDetail> findUserDetail(String userId) {
        return Mono
            .zip(
                userService.findById(userId), // 基本信息
                this.findById(userId).defaultIfEmpty(emptyDetail) // 详情
            )
            .map(tp4 -> UserDetail.of(tp4.getT1())
                .with(tp4.getT2()));
    }


    public Mono<Void> saveUserDetail(String userId, UserDetail request) {
        ValidatorUtils.tryValidate(request);
        UserDetailEntity entity = FastBeanCopier.copy(request, new UserDetailEntity());
        entity.setId(userId);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName(request.getName());

        return save(Mono.just(entity))
            .then(userService.saveUser(Mono.just(userEntity)))
            .then();
    }

}
