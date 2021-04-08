package org.hswebframework.isdp.hyk.tenant.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.isdp.hyk.tenant.Tenant;
import org.hswebframework.isdp.hyk.tenant.TenantMember;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class DefaultTenantMember implements TenantMember {

    @Getter
    private final String userId;

    @Getter
    private final Tenant tenant;


    @Getter
    private final boolean admin;

    @Getter
    private final boolean main;


}
