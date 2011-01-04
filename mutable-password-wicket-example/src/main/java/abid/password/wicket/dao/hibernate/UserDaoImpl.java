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
import org.hibernate.criterion.Restrictions;

import abid.password.wicket.dao.UserDao;
import abid.password.wicket.model.User;

public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

  public UserDaoImpl() {
  }

  public void saveUser(User user) {
    saveOrUpdate(user);
  }

  public User getUser(String username) {
    Session session = getSessionFactory().openSession();
    try {
      session.beginTransaction();
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("username", username));
      User user = (User) criteria.uniqueResult();
      // Query query = session.createQuery("from User u where u.username = ?");
      // query.setString(0, username);
      // User user = (User) query.uniqueResult();
      return user;
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  public List<User> getUsers() {
    return getAll();
  }

}
