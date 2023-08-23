package com.zhouyi.ddd.application.query.core;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;

@Service
public class uniteQueryService {

    private final JpaQueryFactory jpaQueryFactory;

    private final EntityManager em;



    public uniteQueryService(EntityManager em, PlatformTransactionManager transactionManager) {
        jpaQueryFactory = new JpaQueryFactory(em, transactionManager);
        this.em = em;
    }

}
