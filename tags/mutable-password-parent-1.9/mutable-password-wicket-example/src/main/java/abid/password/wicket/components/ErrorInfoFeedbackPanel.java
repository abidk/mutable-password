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
package abid.password.wicket.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Splits the error and info messages so different stylings can be applied.
 * 
 * @author Abid Khalil
 * 
 */
public class ErrorInfoFeedbackPanel extends Panel implements IFeedback {

  private static final long serialVersionUID = 1L;

  public ErrorInfoFeedbackPanel(String id) {
    super(id);

    FeedbackPanel infoFeedbackPanel = createFeedBackFilter("infoMessages", FeedbackMessage.INFO);
    FeedbackPanel errorFeedbackPanel = createFeedBackFilter("errorMessages", FeedbackMessage.ERROR);

    add(infoFeedbackPanel);
    add(errorFeedbackPanel);
  }

  private FeedbackPanel createFeedBackFilter(String id, final int filterLevel) {
    FeedbackPanel feedbackPanel = new FeedbackPanel(id) {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isVisible() {
        return anyMessage(filterLevel);

      }
    };
    feedbackPanel.setFilter(new IFeedbackMessageFilter() {
      private static final long serialVersionUID = 1L;

      public boolean accept(FeedbackMessage message) {
        return message.getLevel() == filterLevel;
      }
    });
    feedbackPanel.setEscapeModelStrings(false);
    return feedbackPanel;
  }
}
