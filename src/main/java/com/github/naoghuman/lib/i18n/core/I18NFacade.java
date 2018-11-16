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
        i18NResourceBundle4.setSupportedLocales(locales);
    }

    @Override
    public Locale getDefaultLocale() {
        return i18NResourceBundle4.getDefaultLocale();
    }
    
    @Override
    public void setDefaultLocale(final Locale locale) {
        i18NResourceBundle4.setDefaultLocale(locale);
    }

    @Override
    public Locale getActualLocale() {
        return i18NResourceBundle4.getActualLocale();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        i18NResourceBundle4.setActualLocale(locale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        return i18NResourceBundle4.actualLocaleProperty();
    }
    
    @Override
    public StringBinding createStringBinding(final Callable<String> function) {
        return i18NBinding4.createStringBinding(function);
    }
    @Override
    public StringBinding createStringBinding(final String key) {
        return i18NBinding4.createStringBinding(key);
    }
    
    @Override
    public StringBinding createStringBinding(final String key, Object... args) {
        return i18NBinding4.createStringBinding(key, args);
    }

    @Override
    public String getBaseBundleName() {
        return i18NResourceBundle4.getBaseBundleName();
    }

    @Override
    public void setBaseBundleName(final String baseName) {
        i18NResourceBundle4.setBaseBundleName(baseName);
    }
    
    @Override
    public String getMessage(final String key) {
        return i18NResourceBundle4.getMessage(key);
    }

    @Override
    public String getMessage(final String key, final Object... args) {
        return i18NResourceBundle4.getMessage(key, args);
    }

}
