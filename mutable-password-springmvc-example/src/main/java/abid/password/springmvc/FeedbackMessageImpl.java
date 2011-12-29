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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class FeedbackMessageImpl implements FeedbackMessage {

  private List<MessageSourceResolvable> messages = new ArrayList<MessageSourceResolvable>();

  public void info(String message, Serializable... arguments) {
    messages.add(new DefaultMessageSourceResolvable(new String[] { message }, arguments));
  }

  public void error(String message, Serializable... arguments) {
    messages.add(new DefaultMessageSourceResolvable(new String[] { message }, arguments));
  }

  public MessageSourceResolvable[] getMessages() {
    return messages.toArray(new MessageSourceResolvable[messages.size()]);
  }

  public void reset() {
    messages.clear();
  }
}
