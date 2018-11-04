/**
 * Copyright (c) 2016 sothawo
 *
 * http://www.sothawo.com
 */
package com.github.naoghuman.app.i18n.demo.prototype4.core;

import com.github.naoghuman.app.i18n.demo.prototype4.internal.DefaultI18NBinding4;
import com.github.naoghuman.app.i18n.demo.prototype4.internal.DefaultI18NResourceBundle4;
import com.github.naoghuman.app.i18n.demo.prototype4.internal.DefaultI18NValidator4;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import javafx.collections.ObservableList;

/**
 * 
 */
public final class I18NFacade4 implements I18NBinding4, I18NResourceBundle4 {
    
    private static final Optional<I18NFacade4> INSTANCE = Optional.of(new I18NFacade4());
    
    public static I18NFacade4 getDefault() {
        return INSTANCE.get();
    }
    
    private I18NBinding4 i18NBinding4;
    private I18NResourceBundle4 i18NResourceBundle4;
    
    private I18NFacade4() {
        this.init();
    }
    
    private void init() {
        i18NBinding4        = new DefaultI18NBinding4();
        i18NResourceBundle4 = new DefaultI18NResourceBundle4();
    }
    
    @Override
    public ObservableList<Locale> getSupportedLocales() {
        return i18NResourceBundle4.getSupportedLocales();
    }

    @Override
    public void setSupportedLocales(final ObservableList<Locale> locales) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(locales);
        
        i18NResourceBundle4.setSupportedLocales(locales);
    }
    
    @Override
    public void setDefaultLocale(final Locale locale) {
        DefaultI18NValidator4.getDefault().requireNonNull(locale);
        
        i18NResourceBundle4.setDefaultLocale(locale);
    }

    @Override
    public Locale getActualLocale() {
        return i18NResourceBundle4.getActualLocale();
    }

    @Override
    public void setActualLocale(final Locale locale) {
        DefaultI18NValidator4.getDefault().requireNonNull(locale);
        
        i18NResourceBundle4.setActualLocale(locale);
    }

    @Override
    public ObjectProperty<Locale> actualLocaleProperty() {
        return i18NResourceBundle4.actualLocaleProperty();
    }

    /**
     * creates a String Binding to a localized String that is computed by calling the given function
     *
     * @param function
     *         function called on every change
     * @return StringBinding
     */
    @Override
    public StringBinding createStringBinding(final Callable<String> function) {
        DefaultI18NValidator4.getDefault().requireNonNull(function);
        
        return i18NBinding4.createStringBinding(function);
    }
    @Override
    public StringBinding createStringBinding(final String key) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        
        return i18NBinding4.createStringBinding(key);
    }

    /**
     * creates a String binding to a localized String for the given getString bundle key
     *
     * @param key key
     * @param args
     * @return String binding
     */
    @Override
    public StringBinding createStringBinding(final String key, Object... args) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(args);
        
        return i18NBinding4.createStringBinding(key, args);
    }

    @Override
    public void setBaseName(final String baseName) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(baseName);
        
        i18NResourceBundle4.setBaseName(baseName);
    }
    
    @Override
    public String getString(final String key) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        
        return i18NResourceBundle4.getString(key);
    }

    @Override
    public String getString(final String key, final Object... args) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(args);
        
        return i18NResourceBundle4.getString(key, args);
    }

}