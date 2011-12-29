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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class FeedbackInterceptor implements WebRequestInterceptor {

  public static final String FEEDBACK_MESSAGE_ATT = "feedbackMessages";

  private FeedbackMessage feedbackMessage;

  @Autowired
  public void setFeedbackMessage(FeedbackMessage feedbackMessage) {
    this.feedbackMessage = feedbackMessage;
  }

  public void preHandle(WebRequest request) throws Exception {
    request.setAttribute(FEEDBACK_MESSAGE_ATT, feedbackMessage.getMessages(), RequestAttributes.SCOPE_REQUEST);
    feedbackMessage.reset();
  }

  public void postHandle(WebRequest request, ModelMap model) throws Exception {
  }

  public void afterCompletion(WebRequest request, Exception ex) throws Exception {

  }

}
