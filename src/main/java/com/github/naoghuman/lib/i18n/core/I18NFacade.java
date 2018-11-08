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

import com.github.naoghuman.lib.i18n.internal.DefaultI18NBinding;
import com.github.naoghuman.lib.i18n.internal.DefaultI18NResourceBundle;
import com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import javafx.collections.ObservableList;

/**
 * 
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public final class I18NFacade implements I18NBinding, I18NResourceBundle {
    
    private static final Optional<I18NFacade> INSTANCE = Optional.of(new I18NFacade());
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static I18NFacade getDefault() {
        return INSTANCE.get();
    }
    
    private I18NBinding i18NBinding4;
    private I18NResourceBundle i18NResourceBundle4;
    
    private I18NFacade() {
        this.init();
    }
    
    private void init() {
        i18NBinding4        = new DefaultI18NBinding();
        i18NResourceBundle4 = new DefaultI18NResourceBundle();
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        return i18NResourceBundle4.getSupportedLocales();
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(locales);
        
        i18NResourceBundle4.setSupportedLocales(locales);
    }

    @Override
    public Locale getDefaultLocale() {
        return i18NResourceBundle4.getDefaultLocale();
    }
    
    @Override
    public void setDefaultLocale(final Locale locale) {
        DefaultI18NValidator.getDefault().requireNonNull(locale);
        
        i18NResourceBundle4.setDefaultLocale(locale);
    }

    @Override
    public Locale getActualLocale() {
        return i18NResourceBundle4.getActualLocale();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        DefaultI18NValidator.getDefault().requireNonNull(locale);
        
        i18NResourceBundle4.setActualLocale(locale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        return i18NResourceBundle4.actualLocaleProperty();
    }
    
    @Override
    public StringBinding createStringBinding(final Callable<String> function) {
        DefaultI18NValidator.getDefault().requireNonNull(function);
        
        return i18NBinding4.createStringBinding(function);
    }
    @Override
    public StringBinding createStringBinding(final String key) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        
        return i18NBinding4.createStringBinding(key);
    }
    
    @Override
    public StringBinding createStringBinding(final String key, Object... args) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(args);
        
        return i18NBinding4.createStringBinding(key, args);
    }

    @Override
    public String getBaseName() {
        return i18NResourceBundle4.getBaseName();
    }

    @Override
    public void setBaseName(final String baseName) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(baseName);
        
        i18NResourceBundle4.setBaseName(baseName);
    }
    
    @Override
    public String getString(final String key) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        
        return i18NResourceBundle4.getString(key);
    }

    @Override
    public String getString(final String key, final Object... args) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(args);
        
        return i18NResourceBundle4.getString(key, args);
    }

}