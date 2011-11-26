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
package abid.password.springmvc;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FlashScopeInterceptor implements HandlerInterceptor {

  public static final String FEEDBACK_MESSAGE_ATT = "feedbackMessage";

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  }

  /**
   * Get feedback message from model and put it into the session
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    // move flash object into session
    HttpSession httpSession = request.getSession(false);
    for (Entry<String, Object> map : modelAndView.getModelMap().entrySet()) {
      String key = map.getKey();
      Object value = map.getValue();
      
      if (key.equals(FEEDBACK_MESSAGE_ATT)) {
        httpSession.setAttribute(FEEDBACK_MESSAGE_ATT, value);
      }
    }

    // remove feedback object from model
    modelAndView.getModelMap().remove(FEEDBACK_MESSAGE_ATT);
  }

  /**
   * Get session flash and put it in request
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession httpSession = request.getSession(false);
    Object flashObject = httpSession.getAttribute(FEEDBACK_MESSAGE_ATT);
    if (flashObject != null) {
      request.setAttribute(FEEDBACK_MESSAGE_ATT, flashObject);
      httpSession.removeAttribute(FEEDBACK_MESSAGE_ATT);
    }
    return true;
  }
}
