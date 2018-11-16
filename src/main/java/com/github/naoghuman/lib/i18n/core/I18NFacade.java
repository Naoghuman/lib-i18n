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
 * Over the facade {@code I18NFacade} the developer have access to all methods 
 * from the interfaces {@link com.github.naoghuman.lib.i18n.core.I18NBinding} 
 * and {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle}.
 * <p>
 * The usage from the builders
 * <ul>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}: 
 *     Allowed the developer to configure the {@link java.util.ResourceBundle}.</li>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder}: 
 *     Allowed the developer to access the messages from the bundle.</li>
 * <li>{@link com.github.naoghuman.lib.i18n.core.I18NBindingBuilder}: 
 *     Allowed the developer to create a {@link javafx.beans.binding.StringBinding}.</li>
 * </ul>
 * is preferred.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NBinding
 * @see     com.github.naoghuman.lib.i18n.core.I18NBindingBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleMessageBuilder
 * @see     java.util.ResourceBundle
 * @see     javafx.beans.binding.StringBinding
 */
public final class I18NFacade implements I18NBinding, I18NResourceBundle {
    
    private static final Optional<I18NFacade> INSTANCE = Optional.of(new I18NFacade());
    
    /**
     * Returns a {@code singleton} instance from this facade.
     * 
     * @return  a {@code singleton} instance from this facade.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     */
    public static I18NFacade getDefault() {
        return INSTANCE.get();
    }
    
    private I18NBinding        i18NBinding;
    private I18NResourceBundle i18NResourceBundle;
    
    private I18NFacade() {
        this.init();
    }
    
    private void init() {
        i18NBinding        = new DefaultI18NBinding();
        i18NResourceBundle = new DefaultI18NResourceBundle();
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        return i18NResourceBundle.getSupportedLocales();
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        i18NResourceBundle.setSupportedLocales(locales);
    }

    @Override
    public Locale getDefaultLocale() {
        return i18NResourceBundle.getDefaultLocale();
    }
    
    @Override
    public void setDefaultLocale(final Locale locale) {
        i18NResourceBundle.setDefaultLocale(locale);
    }

    @Override
    public Locale getActualLocale() {
        return i18NResourceBundle.getActualLocale();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        i18NResourceBundle.setActualLocale(locale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        return i18NResourceBundle.actualLocaleProperty();
    }
    
    @Override
    public StringBinding createStringBinding(final Callable<String> function) {
        return i18NBinding.createStringBinding(function);
    }
    @Override
    public StringBinding createStringBinding(final String key) {
        return i18NBinding.createStringBinding(key);
    }
    
    @Override
    public StringBinding createStringBinding(final String key, Object... args) {
        return i18NBinding.createStringBinding(key, args);
    }

    @Override
    public String getBaseBundleName() {
        return i18NResourceBundle.getBaseBundleName();
    }

    @Override
    public void setBaseBundleName(final String baseName) {
        i18NResourceBundle.setBaseBundleName(baseName);
    }
    
    @Override
    public String getMessage(final String key) {
        return i18NResourceBundle.getMessage(key);
    }

    @Override
    public String getMessage(final String key, final Object... args) {
        return i18NResourceBundle.getMessage(key, args);
    }

}
