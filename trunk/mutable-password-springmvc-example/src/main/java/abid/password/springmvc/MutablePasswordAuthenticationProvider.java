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
package abid.password.springmvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import abid.password.springmvc.model.User;
import abid.password.springmvc.service.UserException;
import abid.password.springmvc.service.UserService;
import abid.password.springmvc.util.StringUtils;

public class MutablePasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  private static final Logger log = LoggerFactory.getLogger(MutablePasswordAuthenticationProvider.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
  }

  @Override
  protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    if (StringUtils.isEmpty(username)) {
      throw new BadCredentialsException(getLocalisedMessage("authentication.username.required"));
    }
    String password = String.valueOf(authentication.getCredentials());
    if (StringUtils.isEmpty(password)) {
      throw new BadCredentialsException(getLocalisedMessage("authentication.password.required"));
    }

    try {
      User authenticatedUser = userService.authenticate(username, password);
      if (authenticatedUser != null) {
        return new CustomUserDetails(authenticatedUser);
      }
    } catch (UserException e) {
      log.error("Error occurred authentication user", e);
    }

    throw new BadCredentialsException(getLocalisedMessage("authentication.failed"));
  }

  private String getLocalisedMessage(String code) {
    return messages.getMessage(code, "Missing [" + code + "]");
  }

  public static class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    public CustomUserDetails(User user) {
      this.user = user;
    }

    public Collection<GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      return authorities;
    }

    public String getPassword() {
      return user.getPassword();
    }

    public String getUsername() {
      return user.getUsername();
    }

    public boolean isAccountNonExpired() {
      return true;
    }

    public boolean isAccountNonLocked() {
      return true;
    }

    public boolean isCredentialsNonExpired() {
      return true;
    }

    public boolean isEnabled() {
      return true;
    }
  }
}
