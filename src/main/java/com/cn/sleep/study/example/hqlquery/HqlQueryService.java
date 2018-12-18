package com.cn.sleep.study.example.hqlquery;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

@Service
public class HqlQueryService {

    @PersistenceContext
    private EntityManager entityManager;


    public Object query(String hql) {
        return entityManager.createQuery(hql).getResultList();
    }

    public Object paramQuery(Map<String, Object> map) {
        String hql = map.get("hql").toString();
        map.remove("hql");
        Query query = entityManager.createQuery(hql);
        map.forEach(query::setParameter);
        return query.getResultList();
    }
}
