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
package abid.password.springmvc;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import javax.servlet.ServletContext;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class IntegrationTestCase {

  public static final String DEFAULT_HOST = "127.0.0.1";
  public static final int DEFAULT_PORT = 9999;
  public static final String MESSAGE_SOURCE_BUNDLE = "messages";

  private static Server server;
  private static WebAppContext webAppContext;

  @BeforeClass
  public static void startWebapp() throws Exception {
    server = new Server();

    Connector connector = new SelectChannelConnector();
    connector.setHost(DEFAULT_HOST);
    connector.setPort(DEFAULT_PORT);
    server.addConnector(connector);

    webAppContext = new WebAppContext();
    webAppContext.setContextPath("/");
    webAppContext.setWar("src/main/webapp");
    server.setHandler(webAppContext);
    server.start();
  }

  @AfterClass
  public static void stopWebapp() throws Exception {
    server.stop();
  }

  @Before
  public void prepare() {
    setBaseUrl(String.format("http://%s:%s/", DEFAULT_HOST, DEFAULT_PORT));
    // setIgnoreFailingStatusCodes(ignore)
  }

  protected WebApplicationContext getApplicationContext() {
    ServletContext servletContext = webAppContext.getServletContext();
    return (WebApplicationContext) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
  }

  protected ResourceBundleMessageSource getResourceBundle() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename(MESSAGE_SOURCE_BUNDLE);
    return messageSource;
  }

  protected void login(String username, String password) {
    assertFormElementPresent("j_username");
    setTextField("j_username", username);

    assertFormElementPresent("j_password");
    setTextField("j_password", password);

    assertSubmitButtonPresent("submit");
    submit("submit");
  }

  protected void logout() {
    assertLinkPresentWithText("Logout");
    clickLinkWithExactText("Logout");
  }
  
  protected void assertAndClickLinkWithText( String linkText ) {
    assertLinkPresentWithText( linkText );
    clickLinkWithText( linkText );
  }
}
