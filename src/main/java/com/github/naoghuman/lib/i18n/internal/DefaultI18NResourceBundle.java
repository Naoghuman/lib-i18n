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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The {@code default} implementation from the interface 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle}.
 * <p>
 * Given {@code attributes} in the methods will be checked by 
 * {@link com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle
 * @see     com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator
 */
public final class DefaultI18NResourceBundle implements I18NResourceBundle {
    
    private ObjectProperty<Locale> actualLocaleProperty;
    private ObservableList<Locale> supportedLocales;
    
    private Locale defaultLocale;
    private String baseBundleName;
    
    /**
     * Default constructor from the is class {@code DefaultI18NResourceBundle}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
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
    public String getBaseBundleName() {
        DefaultI18NValidator.requireNonNullAndNotEmpty(baseBundleName);
        
        return baseBundleName;
    }

    @Override
    public void setBaseBundleName(final String baseBundleName) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(baseBundleName);
        
        this.baseBundleName = baseBundleName;
    }
    
    @Override
    public String getMessage(final String key) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.requireResourceBundleExists(this.getBaseBundleName(), this.getActualLocale());
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseBundleName(), this.getActualLocale());
        
        return bundle.getString(key);
    }
    
    @Override
    public String getMessage(final String key, final Object... arguments) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.requireNonNullAndNotEmpty(arguments);
        DefaultI18NValidator.requireResourceBundleExists(this.getBaseBundleName(), this.getActualLocale());
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseBundleName(), this.getActualLocale());
        
        return MessageFormat.format(bundle.getString(key), arguments);
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        DefaultI18NValidator.requireNonNull(supportedLocales);
        
        return supportedLocales;
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(locales);
        
        supportedLocales.clear();
        supportedLocales.addAll(locales);
    }

    @Override
    public void setSupportedLocales(final Locale... locales) {
        final List<Locale> list = Arrays.asList(locales);
        DefaultI18NValidator.requireNonNullAndNotEmpty(list);
        
        supportedLocales.clear();
        supportedLocales.addAll(list);
    }

    @Override
    public Locale getDefaultLocale() {
        DefaultI18NValidator.requireNonNull(defaultLocale);
        
        return defaultLocale;
    }

    @Override
    public void setDefaultLocale(final Locale locale) {
        DefaultI18NValidator.requireNonNull(locale);
        
        defaultLocale = this.getSupportedLocales().contains(locale) ? locale : Locale.ENGLISH;
    }

    @Override
    public Locale getActualLocale() {
        DefaultI18NValidator.requireNonNull(actualLocaleProperty.get());
        
        return actualLocaleProperty.get();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        DefaultI18NValidator.requireNonNull(locale);
        
        actualLocaleProperty.set(this.getSupportedLocales().contains(locale) ? locale : defaultLocale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        DefaultI18NValidator.requireNonNull(actualLocaleProperty);
        
        return actualLocaleProperty;
    }
    
}
