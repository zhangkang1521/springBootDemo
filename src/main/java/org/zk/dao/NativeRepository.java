package org.zk.dao;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class NativeRepository {

	@Autowired
	private EntityManager entityManager;

	public <E> List<E> queryList(String sql, List<Object> params, Class<E> cls) {
		SQLQuery sqlQuery = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class);
		org.hibernate.Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(cls));
		if (null != params) {
			int position = 0;
			for (Object param : params) {
				query.setParameter(position, param);
				position++;
			}
		}
		return query.list();
	}
}
