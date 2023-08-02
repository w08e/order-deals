package com.zhouyi.ddd.application.query.core;

import com.querydsl.jpa.impl.JPAQuery;
import com.zhouyi.ddd.infrastructure.utils.StreamUtil;

import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

public class JpaQueryFactory {

    private final SessionFactory sessionFactory;

    private final EntityManager em;

    private final PlatformTransactionManager transactionManager;

    public JpaQueryFactory(EntityManager em, PlatformTransactionManager transactionManager) {
        this.sessionFactory = null;
        this.em = em;
        this.transactionManager = transactionManager;
    }

    public <T, F> Query create(JpaQuery query) {
        return new InternalQuery(query);
    }

    class InternalQuery implements Query {

        private final JpaQuery query;

        public InternalQuery(JpaQuery query) {
            this.query = query;
        }

        @Override
        public <R> QueryResult<R> execute(QueryParameter parameter) {
            TransactionTemplate template = new TransactionTemplate(transactionManager);
            template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            return template.execute(s -> {

                JPAQuery<?> query = getJpaQuery();
                this.query.select(query, parameter);
                JpaQueryHelper helper = new JpaQueryHelper(query, this.query::columnMapping);
                QueryResult result = helper.query(parameter);

                if (this.query instanceof JpaQueryTransform) {
                    JpaQueryTransform transform = (JpaQueryTransform) this.query;
                    result = QueryResult.of(StreamUtil.map(result.getList(), transform::transform), result.getTotal());
                }
                return result;
            });
        }

    }

    private JPAQuery<?> getJpaQuery() {
        if (em != null) {
            return new JPAQuery<>(em);
        } else if (sessionFactory != null) {
            return new JPAQuery<>(sessionFactory.getCurrentSession());
        } else {
            throw new IllegalStateException("无法创建JPAQuery");
        }
    }
}
