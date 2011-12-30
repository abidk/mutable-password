package abid.password.springmvc;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseIntegrationTester {

  public static final String DEFAULT_HOST = "127.0.0.1";
  public static final int DEFAULT_PORT = 8090;

  private static Server server;

  @BeforeClass
  public static void startWebapp() throws Exception {
    server = new Server();

    Connector connector = new SelectChannelConnector();
    connector.setHost(DEFAULT_HOST);
    connector.setPort(DEFAULT_PORT);
    server.addConnector(connector);

    WebAppContext webAppContext = new WebAppContext();
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
    setBaseUrl("http://" + DEFAULT_HOST + ":" + DEFAULT_PORT);
    // setIgnoreFailingStatusCodes(ignore)
  }

  protected void login(String username, String password) {
    assertFormElementPresent("j_username");
    setTextField("j_username", username);

    assertFormElementPresent("j_password");
    setTextField("j_password", password);

    assertSubmitButtonPresent("submit");
    submit("submit");
  }
}
