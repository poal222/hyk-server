package org.hswebframework.isdp.hyk.tenant.entity;

import org.hswebframework.isdp.hyk.tenant.CurrentTenantSupplier;
import org.hswebframework.isdp.hyk.tenant.TenantMember;
import org.hswebframework.web.authorization.Authentication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

public class CurrentTenantHolder {

    private static final List<CurrentTenantSupplier> suppliers = new CopyOnWriteArrayList<>();

    public static Mono<TenantMember> current() {
        return Flux.fromIterable(suppliers)
            .flatMap(Supplier::get)
            .take(1)
            .singleOrEmpty();
    }

    public static Mono<TenantMember> fromAuth(Authentication auth){
        return Flux.fromIterable(suppliers)
            .flatMap(supplier -> supplier.fromAuth(auth))
            .take(1)
            .singleOrEmpty();
    }

    static void addSupplier(CurrentTenantSupplier supplier) {
        suppliers.add(supplier);
    }

}
