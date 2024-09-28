package com.vet.users.repositories;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vet.auth_common.entities.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCustomRepository {
    private final R2dbcEntityTemplate template;
    
    public Mono<Tuple2<Long, List<User>>> advancedPagedSearchAndCount(Criteria criteria, int limit, int offset) {
        Query query = Query.query(criteria)
                            .limit(limit)
                            .offset(offset);
        Mono<Long> count = template.count(Query.query(criteria), User.class);
        Flux<User> users = template.select(query, User.class);
        return Mono.zip(count, users.collectList());

    }
}
