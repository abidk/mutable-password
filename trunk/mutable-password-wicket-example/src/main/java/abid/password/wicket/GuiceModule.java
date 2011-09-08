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

package abid.password.wicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.hibernate.cfg.Configuration;

import abid.password.wicket.dao.UserDao;
import abid.password.wicket.dao.UserDaoHibernateImpl;
import abid.password.wicket.service.UserService;
import abid.password.wicket.service.UserServiceImpl;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    install(PersistenceService.usingHibernate().across(UnitOfWork.REQUEST).buildModule());

    Configuration configuration = new Configuration();
    configuration.configure();

    bind(Configuration.class).toInstance(configuration);
    bind(UserService.class).to(UserServiceImpl.class);
    bind(UserDao.class).to(UserDaoHibernateImpl.class);
    bind(WebApplication.class).to(WicketApplication.class);
  }

}