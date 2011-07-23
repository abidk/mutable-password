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
package abid.password.swing.form;

import java.awt.Color;

import javax.swing.JLabel;

import abid.password.swing.Application;

public abstract class AbstractForm extends AbstractComponent {

  private JLabel feedbackLabel;

  public AbstractForm(Application application, String formName) {
    super(application, formName);
  }

  protected void setFeedbackLabel(JLabel feedbackLabel) {
    this.feedbackLabel = feedbackLabel;
  }

  public void setError(String text) {
    setFeedbackMessage(text, Color.RED);
  }

  public void setInfo(String text) {
    setFeedbackMessage(text, Color.BLACK);
  }

  private void setFeedbackMessage(String text, Color feedbackColor) {
    if (feedbackLabel == null) {
      System.out.println(text);
      return;
    }
    if (text == null) {
      feedbackLabel.setText(" ");
      return;
    }
    feedbackLabel.setForeground(feedbackColor);
    feedbackLabel.setText(text);
  }

}
