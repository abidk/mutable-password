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
  public static final int DEFAULT_PORT = 8090;
  public static final String MESSAGE_SOURCE_BUNDLE = "message";

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
