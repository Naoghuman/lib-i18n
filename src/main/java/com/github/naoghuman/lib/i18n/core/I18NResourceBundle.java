/**
 * Copyright (C) 2018 Naoghuman's dream
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.i18n.core;

import java.util.Locale;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 * This {@code Interface} allowed the developer to managed following tasks:<br>
 * Configuration from the {@link java.util.ResourceBundle}:
 * <ul>
 * <li>Setting the {@code base bundle name} for the {@code ResourceBundle}.</li>
 * <li>Setting the {@code supported} {@link java.util.Locale}s.</li>
 * <li>Setting the {@code default} {@code Locale}.</li>
 * <li>and last setting the {@code actual} {@code Locale}.</li>
 * </ul>
 * <p>
 * Access to the {@code ResourceBundle}:
 * <ul>
 * <li>Return directly the {@code value} from a given {@code key} as {@link java.lang.String}.</li>
 * <li>or inject additional {@code arguments} into the message with 
 *     {@link java.text.MessageFormat#format(java.lang.String, java.lang.Object...)}.</li>
 * </ul>
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.0
 * @author  Naoghuman
 * @see     java.lang.Object
 * @see     java.lang.String
 * @see     java.text.MessageFormat#format(java.lang.String, java.lang.Object...)
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public interface I18NResourceBundle {
    
    /**
     * Returns the {@code baseBundleName} from the associated {@link java.util.ResourceBundle}.
     * 
     * @return  the {@code baseBundleName} from the {@code ResourceBundle}.
     * @since   0.1.0-PRERELEASE
     * @version 0.7.0
     * @author  Naoghuman
     * @see     java.util.ResourceBundle
     */
    public String getBaseBundleName();
    
    /**
     * Sets the {@code baseBundleName} which will be used to load the associated 
     * {@link java.util.ResourceBundle}.
     * <p>
     * Format from the {@code baseBundleName} should be:<br>
     * <ul>
     * <li>Package name to the bundle,  '.' (point) separated.</li>
     * <li>Base name from the bundles.</li>
     * </ul>
     * <p>
     * Example:
     * <ul>
     * <li>Package: com.github.naoghuman.app.i18n.demo</li>
     * <li>Bundles: message_de.properties, message_en.properties</li>
     * <li>Base bundle name: com.github.naoghuman.app.i18n.demo.message</li>
     * </ul>
     * 
     * @param   baseBundleName the base bundle name.
     * @throws  NullPointerException     if {@code baseBundleName} is NULL.
     * @throws  IllegalArgumentException if {@code baseBundleName} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     */
    public void setBaseBundleName(final String baseBundleName);
    
    /**
     * Returns the associated {@code value} from the given {@code key} depending 
     * from the {@code actual} {@link java.util.Locale}.
     * <p>
     * Before the access to the value will be performed an internal check will be done if 
     * a {@link java.util.ResourceBundle} with the acutal parameters {@code baseBundleName} 
     * and {@code actualLocale} can be loaded. If not a {@link java.util.MissingResourceException} 
     * will be thrown.
     * <p>
     * Hint:<br>
     * If the {@code key} can't be found in the existing {@code ResourceBundle} then 
     * the pattern '&lt;key&gt;' will be returned.
     * 
     * @param   key which {@code value} should be loaded.
     * @return  the associated {@code value}.
     * @throws  IllegalArgumentException if {@code key} is EMPTY.
     * @throws  NullPointerException     if {@code key} is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.7.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     java.util.Locale
     * @see     java.util.MissingResourceException
     * @see     java.util.ResourceBundle
     */
    public String getMessage(final String key);
    
    /**
     * Returns the associated {@code value} (with the injected {@code arguments}) 
     * from the given {@code key} depending from the {@code actual} {@link java.util.Locale}.
     * <p>
     * Before the access to the value will be performed an internal check will be done if 
     * a {@link java.util.ResourceBundle} with the acutal parameters {@code baseBundleName} 
     * and {@code actualLocale} can be loaded. If not a {@link java.util.MissingResourceException} 
     * will be thrown.
     * <p>
     * For the injecting from the {@code arguments} into the {@code value} from the 
     * {@code ResourceBundle} the class {@link java.text.MessageFormat#format(java.lang.String, java.lang.Object...) } 
     * will be used to format the returned {@code message}.
     * <p>
     * Hint:<br>
     * If the {@code key} can't be found in the existing {@code ResourceBundle} then 
     * the pattern '&lt;key&gt;' will be returned.
     * 
     * @param   key which {@code value} should be loaded.
     * @param   arguments  which should be injected into the associated {@code value}.
     * @return  the associated {@code value}.
     * @throws  IllegalArgumentException if ({@code key} || {@code arguments}) is EMPTY.
     * @throws  NullPointerException     if ({@code key} || {@code arguments}) is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.7.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     java.text.MessageFormat#format(java.lang.String, java.lang.Object...)
     * @see     java.util.Locale
     * @see     java.util.MissingResourceException
     * @see     java.util.ResourceBundle
     */
    public String getMessage(final String key, final Object... arguments);
    
    /**
     * Returns the {@code default} {@link java.util.Locale}.
     * 
     * @return  the {@code default} {@code Locale}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     */
    public Locale getDefaultLocale();
    
    /**
     * Sets the {@code default} {@link java.util.Locale}.
     * <p>
     * If the {@code supported} Locales doesn't contained the {@code default} Locale 
     * then the {@link java.util.Locale#ENGLISH} will be used instead.
     * 
     * @param   locale the new {@code default} {@code Locale}.
     * @throws  NullPointerException     if {@code locale} is NULL.
     * @throws  IllegalArgumentException if {@code locale} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setSupportedLocales(javafx.collections.ObservableList) 
     * @see     java.util.Locale
     * @see     java.util.Locale#ENGLISH
     */
    public void setDefaultLocale(final Locale locale);
    
    /**
     * Returns the {@code actual} {@link java.util.Locale}.
     * 
     * @return  the {@code actual} {@code Locale}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     */
    public Locale getActualLocale();
    
    /**
     * Sets the {@code actual} {@link java.util.Locale}.
     * <p>
     * If the {@code supported} Locales doesn't contained the {@code actual} Locale
     * then the {@code default} Locale will be used instead.
     * 
     * @param   locale the new {@code actual} {@code Locale}.
     * @throws  NullPointerException     if {@code locale} is NULL.
     * @throws  IllegalArgumentException if {@code locale} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#getDefaultLocale() 
     * @see     java.util.Locale
     */
    public void setActualLocale(final Locale locale);
    
    /**
     * Returns the {@code actual} {@link java.util.Locale} as a {@link javafx.beans.property.ObjectProperty}.
     * <p>
     * The {@code ObjectProperty} allowed during the update from the {@code actual} 
     * {@code Locale} that all binded texts will be automatically updated.
     * 
     * @return  the {@code actual} Locale as a {@code ObjectProperty}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     javafx.beans.property.ObjectProperty
     */
    public ObjectProperty<Locale> actualLocaleProperty();
    
    /**
     * Returns all {@code supported} {@link java.util.Locale}s as a {@code observable} list.
     * 
     * @return  all {@code supported} {@code Locales} as a {@code observable} list.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     */
    public ObservableList<Locale> getSupportedLocales();
    
    /**
     * Sets the {@code supported} {@link java.util.Locale}s.
     * 
     * @param   locales contains all {@code supported} {@code Locale}s.
     * @throws  NullPointerException     if {@code locales} is NULL.
     * @throws  IllegalArgumentException if {@code locales} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     */
    public void setSupportedLocales(final Locale... locales);
    
    /**
     * Sets the {@code supported} {@link java.util.Locale}s.
     * 
     * @param   locales contains all {@code supported} {@code Locale}s.
     * @throws  NullPointerException     if {@code locales} is NULL.
     * @throws  IllegalArgumentException if {@code locales} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.Locale
     */
    public void setSupportedLocales(final ObservableList<Locale> locales);
    
}
