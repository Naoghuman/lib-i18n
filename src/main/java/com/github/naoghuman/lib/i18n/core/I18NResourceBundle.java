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
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface I18NResourceBundle {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public String getBaseName();
    
    /**
     * 
     * @param  baseName 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public void setBaseName(final String baseName);
    
    /**
     * 
     * @param  key
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public String getString(final String key);
    
    /**
     * 
     * @param  key
     * @param  args
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public String getString(final String key, final Object... args);
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public Locale getDefaultLocale();
    
    /**
     * 
     * @param  locale 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public void setDefaultLocale(final Locale locale);
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public Locale getActualLocale();
    
    /**
     * 
     * @param  locale 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public void setActualLocale(final Locale locale);
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public ObjectProperty<Locale> actualLocaleProperty();
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public ObservableList<Locale> getSupportedLocales();
    
    /**
     * 
     * @param  locales 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public void setSupportedLocales(final ObservableList<Locale> locales);
    
    // TODO public void setSupportedLocales(final Locale... locales);
    // + I18NResourceBundleBuilder4
    
}
