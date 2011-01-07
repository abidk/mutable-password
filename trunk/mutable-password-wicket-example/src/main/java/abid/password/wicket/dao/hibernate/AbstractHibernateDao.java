/**
 * Copyright 2011 Abid Khalil
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

package abid.password.wicket.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Could have used wasp for transaction handling. But, left it out as this is
 * just an example project.
 * 
 */
public abstract class AbstractHibernateDao<T extends Serializable> {

  private static final Logger log = LoggerFactory.getLogger(AbstractHibernateDao.class);
  private static final SessionFactory sessionFactory;

  static {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      sessionFactory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      log.error("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  private Class<T> persistentClass;

  @SuppressWarnings("unchecked")
  public AbstractHibernateDao() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(persistentClass);
      List<T> list = (List<T>) criteria.list();
      return list;
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  public List<T> get(int first, int fetchSize) {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(persistentClass);
      criteria.setFirstResult(first);
      criteria.setMaxResults(fetchSize);
      criteria.setFetchSize(fetchSize);
      criteria.setCacheable(true);

      List<T> dataList = (List<T>) criteria.list();
      return dataList;
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  public List<T> getSorted(int first, int fetchSize, String propertyName, boolean ascending) {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(persistentClass);
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
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("rawtypes")
  public int countAll() {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(persistentClass);
      criteria.setProjection(Projections.rowCount());

      List result = criteria.list();
      Long rowCount = (Long) result.get(0);
      int count = rowCount.intValue();
      // return ( Long ) criteria.uniqueResult( );
      return count;
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  protected List<T> findByCriteria(Criterion... criterion) {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(getPersistentClass());
      criteria.setCacheable(true);
      for (Criterion c : criterion) {
        criteria.add(c);
      }
      return criteria.list();
    } finally {
      session.close();
    }
  }

  public void saveOrUpdate(T entity) {
    Session session = sessionFactory.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      session.saveOrUpdate(entity);
      transaction.commit();
    } finally {
      session.close();
    }
  }

  public void delete(T entity) {
    Session session = sessionFactory.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      session.delete(entity);
      transaction.commit();
    } finally {
      session.close();
    }
  }

}
