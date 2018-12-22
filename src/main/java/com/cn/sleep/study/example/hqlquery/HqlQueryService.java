package com.cn.sleep.study.example.hqlquery;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Map;

@Service
public class HqlQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    public Object query(String hql) {
        return getSession().createQuery(hql).getResultList();
    }

    public Object paramQuery(Map<String, Object> map) {
        String hql = map.get("hql").toString().trim();
        map.remove("hql");
        Query query = entityManager.createQuery(hql);
        map.forEach(query::setParameter);
        return query.getResultList();
    }

  /*  public Object tupleQuery(Map<String, Object> map) {
        String hql = map.get("hql").toString().trim();
        map.remove("hql");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        criteria.multiselect(

        );
    }*/
}
