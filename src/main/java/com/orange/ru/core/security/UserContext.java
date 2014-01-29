package com.orange.ru.core.security;

import com.orange.ru.core.userdetails.OrangeUser;

/**
 * Manages the current {@link OrangeUser}. This demonstrates how in larger applications it is good to abstract out
 * accessing the current user to return the application specific user rather than interacting with Spring Security
 * classes directly.
 */
public interface UserContext {
  /** Gets the currently logged in {@link OrangeUser} or null if there is no authenticated user. */
  OrangeUser getCurrentUser();
  /**
   * Sets the currently logged in {@link OrangeUser}.
   * @param user the logged in {@link OrangeUser}. Cannot be null.
   * @throws IllegalArgumentException if the {@link OrangeUser} is null.
   */
  void setCurrentUser(OrangeUser user);
}