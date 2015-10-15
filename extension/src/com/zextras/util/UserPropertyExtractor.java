package com.zextras.util;

import org.openzal.zal.Account;

import java.util.*;

/**
 * Utility class which help to extract information from Account Object.
 */
public class UserPropertyExtractor
{
  private static String A_zimbraProxyAllowedDomains = "zimbraProxyAllowedDomains";
  private static String A_zimbraZimletUserProperties = "zimbraZimletUserProperties";
  private static String A_zimbraZimletAvailableZimlets = "zimbraZimletAvailableZimlets";

  /**
   * Get all the zimlet user properties, filtered for a specific zimlet.
   * @param account The source account
   * @param filterBy The name of the zimlet
   * @return The set of the properties of the zimlet.
   */
  public static Map<String, String> getZimletUserProperties(Account account, String filterBy)
  {
    final Map<String, Object> accountAttrs = account.getAttrs(true);
    final Map<String, String> propSet = new HashMap<String, String>();
    if (accountAttrs.get(A_zimbraZimletUserProperties) != null)
    {
      final String[] allZimletUserProperties = (String[])accountAttrs.get(A_zimbraZimletUserProperties);
      for (String property : allZimletUserProperties)
      {
        if (property.startsWith(filterBy + ":"))
        {
          String rawProperty = property.substring(filterBy.length() + 1);
          String[] strings = rawProperty.split(":", 2);
          propSet.put(strings[0], strings[1]);
        }
      }
    }
    return propSet;
  }

  /**
   *
   * @param account
   * @return
   */
  public static Set<String> getProxyAllowedDomain(Account account)
  {
    final Map<String, Object> accountAttrs = account.getAttrs(true);
    final Set<String> allowedDomains = new HashSet<String>();
    if (accountAttrs.get(A_zimbraProxyAllowedDomains) != null)
    {
      final String[] allAllowedDomains = (String[])accountAttrs.get(A_zimbraProxyAllowedDomains);
      Collections.addAll(allowedDomains, allAllowedDomains);
    }
    return allowedDomains;
  }
}