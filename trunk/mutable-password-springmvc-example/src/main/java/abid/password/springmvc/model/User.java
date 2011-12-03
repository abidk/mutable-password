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

package abid.password.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.evaluator.ParseException;
import abid.password.types.PasswordFactory;
import abid.password.types.PasswordInstantiationException;

@Entity
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  private String username;
  private String password;

  @Id
  @Column(name = "username", nullable = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Transient
  public String getEvaluatedPassword() throws PasswordInstantiationException, ParseException {
    String evalatedPassword = password;
    Password passwordObj = PasswordFactory.getInstance(password);
    if (passwordObj instanceof MutablePassword) {
      MutablePassword mutablePassword = (MutablePassword) passwordObj;
      evalatedPassword = mutablePassword.getEvaluatedPassword();
    }
    return evalatedPassword;
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof User)) {
      return false;
    }

    User other = (User) obj;
    return username.equals(other.username);
  }

}
