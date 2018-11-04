/*
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
package com.github.naoghuman.app.i18n.demo.prototype4.core;

import java.util.Locale;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author PRo
 */
public interface I18NResourceBundle4 {
    
    public void setBaseName(final String baseName);
    
    // I18NMessageBuilder4
    public String getString(final String key);
    // I18NMessageBuilder4
    public String getString(final String key, final Object... args);
    
    public void setDefaultLocale(final Locale locale);
    public Locale getActualLocale();
    public void setActualLocale(final Locale locale);
    public ObjectProperty<Locale> actualLocaleProperty();
    
    public ObservableList<Locale> getSupportedLocales();
    public void setSupportedLocales(final ObservableList<Locale> locales);
    // public void setSupportedLocales(final Locale... locales);
    // + I18NResourceBundleBuilder4
    
}
