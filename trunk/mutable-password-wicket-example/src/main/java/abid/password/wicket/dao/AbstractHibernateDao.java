/**
 * Copyright 2012 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package abid.password.wicket.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.TransactionType;
import com.wideplay.warp.persist.Transactional;

public abstract class AbstractHibernateDao<T extends Serializable> {

  @Inject
  private Provider<Session> session;
  private Class<T> persistentClass;

  public AbstractHibernateDao(Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  public Provider<Session> getSession() {
    return session;
  }

  @Transactional(type = TransactionType.READ_ONLY)
  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Criteria criteria = session.get().createCriteria(persistentClass);
    List<T> list = (List<T>) criteria.list();
    return list;
  }

  @Transactional(type = TransactionType.READ_ONLY)
  @SuppressWarnings("unchecked")
  public List<T> get(int first, int fetchSize) {
    Criteria criteria = session.get().createCriteria(persistentClass);
    criteria.setFirstResult(first);
    criteria.setMaxResults(fetchSize);
    criteria.setFetchSize(fetchSize);
    criteria.setCacheable(true);

    List<T> dataList = (List<T>) criteria.list();
    return dataList;
  }

  @Transactional(type = TransactionType.READ_ONLY)
  @SuppressWarnings("unchecked")
  public List<T> getSorted(int first, int fetchSize, String propertyName, boolean ascending) {
    Criteria criteria = session.get().createCriteria(persistentClass);
    criteria.setFirstResult(first);
    criteria.setMaxResults(fetchSize);
    criteria.setFetchSize(fetchSize);
    criteria.setCacheable(true);

    if (ascending) {
      criteria.addOrder(Order.asc(propertyName));
    } else {
      criteria.addOrder(Order.desc(propertyName));
    }

    List<T> dataList = (List<T>) criteria.list();
    return dataList;
  }

  @Transactional(type = TransactionType.READ_ONLY)
  @SuppressWarnings("rawtypes")
  public int countAll() {
    Criteria criteria = session.get().createCriteria(persistentClass);
    criteria.setProjection(Projections.rowCount());

    List result = criteria.list();
    Long rowCount = (Long) result.get(0);
    int count = rowCount.intValue();
    // return ( Long ) criteria.uniqueResult( );
    return count;

  }

  @Transactional(type = TransactionType.READ_ONLY)
  @SuppressWarnings("unchecked")
  protected List<T> findByCriteria(Criterion... criterion) {
    Criteria criteria = session.get().createCriteria(getPersistentClass());
    criteria.setCacheable(true);
    for (Criterion c : criterion) {
      criteria.add(c);
    }
    return criteria.list();
  }

  @Transactional
  public void saveOrUpdate(T entity) {
    session.get().saveOrUpdate(entity);
  }

  @Transactional
  public void delete(T entity) {
    session.get().delete(entity);
  }

}
