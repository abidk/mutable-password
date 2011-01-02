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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T> {

  private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);
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

  private Class<T> domainClass;

  public AbstractDao(Class<T> domainClass) {
    this.domainClass = domainClass;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public int countAll() {
    Session session = sessionFactory.openSession();
    try {
      session.beginTransaction();
      Criteria criteria = session.createCriteria(domainClass);
      criteria.setProjection(Projections.rowCount());
      return (Integer) criteria.uniqueResult();
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Session session = sessionFactory.openSession();
    try {
      session.beginTransaction();
      Criteria criteria = session.createCriteria(domainClass);
      List<T> list = (List<T>) criteria.list();
      return list;
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }
}
