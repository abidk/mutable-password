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

package abid.password.wicket.pages;

import org.apache.wicket.markup.html.WebPage;

import abid.password.wicket.components.ErrorInfoFeedbackPanel;

public abstract class BasePage extends WebPage {

  private static final long serialVersionUID = 1L;

  public BasePage() {
    ErrorInfoFeedbackPanel feedbackPanel = new ErrorInfoFeedbackPanel("feedbackPanel");
    add(feedbackPanel);
  }
}
