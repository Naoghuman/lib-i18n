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
package com.github.naoghuman.app.i18n.demo.prototype4.internal;

import com.github.naoghuman.app.i18n.demo.prototype4.core.I18NResourceBundle4;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PRo
 */
public class DefaultI18NResourceBundle4 implements I18NResourceBundle4 {
    
    /** the current selected Locale. */
    private ObjectProperty<Locale> actualLocaleProperty;
    private ObservableList<Locale> supportedLocales;
    
    private Locale defaultLocale;
    private String baseName;
    
    public DefaultI18NResourceBundle4() {
        this.init();
    }
    
    private void init() {
        defaultLocale        = Locale.ENGLISH;
        actualLocaleProperty = new SimpleObjectProperty<>(defaultLocale);
        supportedLocales     = FXCollections.observableArrayList();
    }
    
    private String getBaseName() {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(baseName);
        
        return baseName;
    }

    @Override
    public void setBaseName(final String baseName) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(baseName);
        
        this.baseName = baseName;
    }
    
    @Override
    public String getString(final String key) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseName(), this.getActualLocale());
        
        return bundle.getString(key);
    }
    
    @Override
    public String getString(final String key, final Object... args) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(args);
        
        final ResourceBundle bundle = ResourceBundle.getBundle(this.getBaseName(), this.getActualLocale());
        
        return MessageFormat.format(bundle.getString(key), args);
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        DefaultI18NValidator4.getDefault().requireNonNull(supportedLocales);
        
        return supportedLocales;
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(locales);
        
        supportedLocales.clear();
        supportedLocales.addAll(locales);
    }

    @Override
    public void setDefaultLocale(final Locale locale) {
        DefaultI18NValidator4.getDefault().requireNonNull(locale);
        
        defaultLocale = this.getSupportedLocales().contains(locale) ? locale : Locale.ENGLISH;
    }

    @Override
    public Locale getActualLocale() {
        DefaultI18NValidator4.getDefault().requireNonNull(actualLocaleProperty.get());
        
        return actualLocaleProperty.get();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        DefaultI18NValidator4.getDefault().requireNonNull(locale);
        
        actualLocaleProperty.set(this.getSupportedLocales().contains(locale) ? locale : defaultLocale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        DefaultI18NValidator4.getDefault().requireNonNull(actualLocaleProperty);
        
        return actualLocaleProperty;
    }
    
}
