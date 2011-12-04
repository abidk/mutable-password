package abid.password.springmvc.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

  private RequestCache requestCache = new HttpSessionRequestCache();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws ServletException, IOException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest == null) {
      super.onAuthenticationSuccess(request, response, authentication);
      return;
    }

    if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
      requestCache.removeRequest(request, response);
      super.onAuthenticationSuccess(request, response, authentication);
      return;
    }

    clearAuthenticationAttributes(request);

    String targetUrl = savedRequest.getRedirectUrl();
    log.debug("Redirecting to Url '{}'", targetUrl);
    log.debug("Default Url '{}'", getDefaultTargetUrl());

    
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  public void setRequestCache(RequestCache requestCache) {
    this.requestCache = requestCache;
  }
}
