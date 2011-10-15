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
