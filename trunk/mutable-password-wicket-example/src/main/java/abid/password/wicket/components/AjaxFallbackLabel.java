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

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Displays text inside the label when JavaScript is enabled, otherwise it will
 * default to some other text when JavaScript is disabled.
 * 
 * @author Abid Khalil
 * 
 */
public class AjaxFallbackLabel extends Label {

  private static final long serialVersionUID = 1L;

  public AjaxFallbackLabel(String id, final String ajaxEnabledText, String ajaxDisabledText) {
    super(id, ajaxDisabledText);

    setOutputMarkupPlaceholderTag(true);

    add(new Behavior() {

      private static final long serialVersionUID = 1L;

      @Override
      public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.renderOnLoadJavaScript(String.format("document.getElementById('%s').innerHTML='%s'", getMarkupId(), ajaxEnabledText));
      }
    });
  }

}
