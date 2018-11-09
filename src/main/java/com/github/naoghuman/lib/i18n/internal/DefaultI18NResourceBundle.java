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
package com.github.naoghuman.lib.i18n.internal;

import com.github.naoghuman.lib.i18n.core.I18NResourceBundle;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public class DefaultI18NResourceBundle implements I18NResourceBundle {
    
    private ObjectProperty<Locale> actualLocaleProperty;
    private ObservableList<Locale> supportedLocales;
    
    private Locale defaultLocale;
    private String baseName;
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public DefaultI18NResourceBundle() {
        this.init();
    }
    
    private void init() {
        defaultLocale        = Locale.ENGLISH;
        actualLocaleProperty = new SimpleObjectProperty<>(defaultLocale);
        supportedLocales     = FXCollections.observableArrayList();
    }
    
    @Override
    public String getBaseName() {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(baseName);
        
        return baseName;
    }

    @Override
    public void setBaseName(final String baseName) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(baseName);
        
        this.baseName = baseName;
    }
    
    @Override
    public String getString(final String key) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseName(), this.getActualLocale());
        
        return bundle.getString(key);
    }
    
    @Override
    public String getString(final String key, final Object... arguments) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(arguments);
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseName(), this.getActualLocale());
        
        return MessageFormat.format(bundle.getString(key), arguments);
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        DefaultI18NValidator.getDefault().requireNonNull(supportedLocales);
        
        return supportedLocales;
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(locales);
        
        supportedLocales.clear();
        supportedLocales.addAll(locales);
    }

    @Override
    public Locale getDefaultLocale() {
        DefaultI18NValidator.getDefault().requireNonNull(defaultLocale);
        
        return defaultLocale;
    }

    @Override
    public void setDefaultLocale(final Locale locale) {
        DefaultI18NValidator.getDefault().requireNonNull(locale);
        
        defaultLocale = this.getSupportedLocales().contains(locale) ? locale : Locale.ENGLISH;
    }

    @Override
    public Locale getActualLocale() {
        DefaultI18NValidator.getDefault().requireNonNull(actualLocaleProperty.get());
        
        return actualLocaleProperty.get();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        DefaultI18NValidator.getDefault().requireNonNull(locale);
        
        actualLocaleProperty.set(this.getSupportedLocales().contains(locale) ? locale : defaultLocale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        DefaultI18NValidator.getDefault().requireNonNull(actualLocaleProperty);
        
        return actualLocaleProperty;
    }
    
}
