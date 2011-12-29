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

package abid.password.springmvc.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import abid.password.springmvc.dao.UserDao;
import abid.password.springmvc.model.User;

@Repository
public class UserDaoHibernateImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional(readOnly = true)
  public User getUser(String username) {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("username", username));
    User user = (User) criteria.uniqueResult();
    return user;
  }

  @Transactional
  public void saveUser(User user) {
    sessionFactory.getCurrentSession().saveOrUpdate(user);
  }

  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public List<User> getUsers() {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
    List<User> userList = (List<User>) criteria.list();
    return userList;
  }

}
