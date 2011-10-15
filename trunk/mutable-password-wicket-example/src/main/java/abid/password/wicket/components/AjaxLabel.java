package abid.password.wicket.components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;

public class AjaxLabel extends Label {

  private static final long serialVersionUID = 1L;

  public AjaxLabel(final String id, final String ajaxText, String ajaxDisabledText) {
    super(id, ajaxDisabledText);

    setOutputMarkupPlaceholderTag(true);

    add(new Behavior() {

      private static final long serialVersionUID = 1L;

      @Override
      public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.renderOnLoadJavaScript(String.format("document.getElementById('%s').innerHTML='%s'", getMarkupId(), ajaxText));
      }
    });
  }

}
