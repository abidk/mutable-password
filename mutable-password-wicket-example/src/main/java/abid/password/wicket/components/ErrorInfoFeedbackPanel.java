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
package abid.password.wicket.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Splits the error and info messages to allow for different stylings
 * 
 */
public class ErrorInfoFeedbackPanel extends Panel implements IFeedback {

  private static final long serialVersionUID = 1L;

  public ErrorInfoFeedbackPanel(String id) {
    super(id);

    final FeedbackPanel infoFeedbackPanel = new FeedbackPanel("infoMessages", new IFeedbackMessageFilter() {
      private static final long serialVersionUID = 1L;

      public boolean accept(FeedbackMessage message) {
        return message.getLevel() == FeedbackMessage.INFO;
      }
    });
    infoFeedbackPanel.setEscapeModelStrings(false);

    WebMarkupContainer infoMessagesContainer = new WebMarkupContainer("infoContainer") {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isVisible() {
        return infoFeedbackPanel.anyMessage(FeedbackMessage.INFO);
      }
    };
    infoMessagesContainer.add(infoFeedbackPanel);

    final FeedbackPanel errorFeedbackPanel = new FeedbackPanel("errorMessages", new IFeedbackMessageFilter() {
      private static final long serialVersionUID = 1L;

      public boolean accept(FeedbackMessage message) {
        return message.getLevel() == FeedbackMessage.ERROR;
      }
    });
    errorFeedbackPanel.setEscapeModelStrings(false);

    WebMarkupContainer errorMessagesContainer = new WebMarkupContainer("errorContainer") {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isVisible() {
        return errorFeedbackPanel.anyMessage(FeedbackMessage.ERROR);
      }
    };
    errorMessagesContainer.add(errorFeedbackPanel);

    add(infoMessagesContainer);
    add(errorMessagesContainer);
  }
}
