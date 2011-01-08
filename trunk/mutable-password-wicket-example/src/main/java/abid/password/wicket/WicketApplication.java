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

import org.apache.wicket.Application;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import abid.password.wicket.pages.CreateUserPage;
import abid.password.wicket.pages.LoginPage;

public class WicketApplication extends WebApplication {

  @Override
  protected void init() {
    mountBookmarkablePage("login", LoginPage.class);
    mountBookmarkablePage("createUser", CreateUserPage.class);
    // Could use InjectionFlagCachingGuiceComponentInjector
    addComponentInstantiationListener(new GuiceComponentInjector(this, new GuiceModule()));
  }

  public Class<? extends WebPage> getHomePage() {
    return CreateUserPage.class;
  }

  public static WicketApplication get() {
    return (WicketApplication) Application.get();
  }
}
