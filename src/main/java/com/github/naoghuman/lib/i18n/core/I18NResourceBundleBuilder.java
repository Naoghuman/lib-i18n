/**
 * Copyright (C) Naoghuman's dream
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

import com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.Locale;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/*
 * Examples 'Usage from I18NResourceBundleBuilder'
 *
 * 1) Starts the configuration process.
 * 2) Defines the path and name from the .properties file.
 * 3) Sets all supported Locales.
 * 4) Sets the default Locale.
 * 5) Sets the actual Locale.
 * 6) Completes the configuration process.
 * I18NResourceBundleBuilder.configure() // 1
 *        .baseName(String)              // 2
 *        .supportedLocales(ObservableList<Locale>) // 3
 *        .defaultLocale(Locale)         // 4
 *        .actualLocale(Locale)          // 5
 *        .build();                      // 6
 */
/**
 * With the fluent builder {@code I18NResourceBundleBuilder} the developer can easily configure 
 * the {@link java.util.ResourceBundle} and the different relevant {@link java.util.Locale}s.
 * <p>
 * All steps in this fluent builder are mandory so simple follow the way :) .
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.5.0
 * @author  Naoghuman
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 */
public final class I18NResourceBundleBuilder {
    
    /**
     * Starting point from this fluent builder to configure a {@link java.util.ResourceBundle} 
     * which {@code key/value} pairs will be bind to a {@link java.util.Locale}.
     * 
     * @return  the first step to configure the {@code ResourceBundle}.
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public static final FirstStep configure() {
        
        return new I18NResourceBundleBuilderImpl();
        
    }
    
    /**
     * First mandory step to configure the {@link java.util.ResourceBundle}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.ResourceBundle
     */
    public interface FirstStep {
        
        /**
         * Setter for the {@code path} and {@code base} name from the {@link java.util.ResourceBundle}.
         * <p>
         * The format from {@code baseBundleName} is the package name and the base 
         * name from the bundle {@code point} ('.') separated.<br>
         * For example:
         * <ul>
         * <li>com.github.naoghuman.app.i18n.demo.message</li>
         * </ul>
         * where {@code com.github.naoghuman.app.i18n.demo} is the package and 
         * {@code message} the base bundle name.
         * 
         * @param   baseBundleName which defines the path and base name from the {@code ResourceBundle}.
         * @return  the second step in this fluent builder.
         * @throws  NullPointerException     if {@code baseBundleName} is NULL.
         * @throws  IllegalArgumentException if {@code baseBundleName} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.5.0
         * @author  Naoghuman
         * @see     java.util.ResourceBundle
         */
        public SecondStep baseBundleName(final String baseBundleName);
        
    }
    
    /**
     * Second mandory step to configure the {@link java.util.ResourceBundle}.
     * <p>
     * In this step all {@code supported} {@link java.util.Locale}s from the 
     * {@code ResourceBundle} will be set.
     * <p>
     * Supported {@code Locale}s means that the list should contains for every 
     * supported language (language_xy.properties) the corresponding {@code Locale}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public interface SecondStep {
        
        /**
         * Setter for all {@code supported} {@link java.util.Locale}s from the 
         * {@link java.util.ResourceBundle}.
         * <p>
         * Supported {@code Locale}s means that the list should contains for every 
         * supported language (language_xy.properties) the corresponding {@code Locale}.
         * 
         * @param   locales contains all {@code supported} {@code Locale}s.
         * @return  the third step in this fluent builder.
         * @throws  NullPointerException     if {@code locales} is NULL.
         * @throws  IllegalArgumentException if {@code locales} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.5.0
         * @author  Naoghuman
         * @see     java.util.Locale
         * @see     java.util.ResourceBundle
         */
        public ThirdStep supportedLocales(final ObservableList<Locale> locales);
    
    }
    
    /**
     * Third mandory step to configure the {@link java.util.ResourceBundle}.
     * <p>
     * In this step the {@code default} {@link java.util.Locale} from the 
     * {@code ResourceBundle} will be set.
     * <p>
     * Default {@code Locale} means that this Locale should be used if the 
     * {@code actual} Locale aren't in the list of supported Locales.<br>
     * If the default Locale isn't in the list of supported Locales then 
     * {@link java.util.Locale#ENGLISH} will be used instead.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setActualLocale(java.util.Locale) 
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public interface ThirdStep {
        
        /**
         * Setter for the {@code default} {@link java.util.Locale} from the 
         * {@link java.util.ResourceBundle}.
         * <p>
         * Default {@code Locale} means that this Locale should be used if the 
         * {@code actual} Locale aren't in the list of supported Locales.<br>
         * If the default Locale isn't in the list of supported Locales then 
         * {@link java.util.Locale#ENGLISH} will be used instead.
         * 
         * @param   locale the {@code default} {@code Locale}.
         * @return  the forth step in this fluent builder.
         * @throws  NullPointerException if {@code locale} is NULL.
         * @since   0.1.0-PRERELEASE
         * @version 0.5.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setActualLocale(java.util.Locale) 
         * @see     java.util.Locale
         * @see     java.util.ResourceBundle
         */
        public ForthStep defaultLocale(final Locale locale);
        
    }
    
    /**
     * Forth mandory step to configure the {@link java.util.ResourceBundle}.
     * <p>
     * In this step the {@code actual} {@link java.util.Locale} from the 
     * {@code ResourceBundle} will be set.
     * <p>
     * Actual {@code Locale} means that this Locale should used for the message 
     * loading from the {@link java.util.ResourceBundle}.<br>
     * If the actual Locale isn't in the list of supported Locales then the 
     * {@code default} Locale will be used instead.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setDefaultLocale(java.util.Locale) 
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public interface ForthStep {
        
        /**
         * Setter for the {@code actual} {@link java.util.Locale} from the 
         * {@link java.util.ResourceBundle}.
         * <p>
         * Actual {@code Locale} means that this Locale should used for the message 
         * loading from the {@link java.util.ResourceBundle}.<br>
         * If the actual Locale isn't in the list of supported Locales then the 
         * {@code default} Locale will be used instead.
         * 
         * @param   locale the {@code actual} {@code Locale}.
         * @return  the last step in this fluent builder.
         * @throws  NullPointerException if {@code locale} is NULL.
         * @since   0.1.0-PRERELEASE
         * @version 0.5.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#setDefaultLocale(java.util.Locale) 
         * @see     java.util.Locale
         * @see     java.util.ResourceBundle
         */
        public LastStep actualLocale(final Locale locale);
        
    }
    
    /**
     * The last step in this fluent builder.
     * <p>
     * With the option {@code build()} the developer completes the previsous 
     * configuration steps from the {@link java.util.ResourceBundle} and the 
     * different {@link java.util.Locale}s.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.5.0
     * @author  Naoghuman
     * @see     java.util.Locale
     * @see     java.util.ResourceBundle
     */
    public interface LastStep {
        
        /**
         * Completes the previous configuration steps from the {@link java.util.ResourceBundle} 
         * and the different {@link java.util.Locale}s in this fluent builder.
         * 
         * @since   0.1.0-PRERELEASE
         * @version 0.5.0
         * @author  Naoghuman
         * @see     java.util.Locale
         * @see     java.util.ResourceBundle
         */
        public void build();
        
    }
    
    private static final class I18NResourceBundleBuilderImpl implements
            FirstStep,  ForthStep, LastStep, 
            SecondStep, ThirdStep
    {
        private static final String ATTR__BASE_BUNDLE_NAME  = "baseBundleName";   // NOI18N
        private static final String ATTR__ACTUAL_LOCALE     = "actualLocale";     // NOI18N
        private static final String ATTR__DEFAULT_LOCALE    = "defaultLocale";    // NOI18N
        private static final String ATTR__SUPPORTED_LOCALES = "supportedLocales"; // NOI18N
    
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
        
        private I18NResourceBundleBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(I18NResourceBundleBuilder.class, "I18NResourceBundleBuilderImpl.initialize()"); // NOI18N
            
            properties.put(ATTR__BASE_BUNDLE_NAME,  new SimpleStringProperty());
            properties.put(ATTR__ACTUAL_LOCALE,     new SimpleObjectProperty());
            properties.put(ATTR__DEFAULT_LOCALE,    new SimpleObjectProperty());
            properties.put(ATTR__SUPPORTED_LOCALES, new SimpleObjectProperty());
        }

        @Override
        public SecondStep baseBundleName(final String baseBundleName) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.baseBundleName(String)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(baseBundleName);
            properties.put(ATTR__BASE_BUNDLE_NAME, new SimpleStringProperty(baseBundleName));
            
            return this;
        }

        @Override
        public ThirdStep supportedLocales(final ObservableList<Locale> locales) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.supportedLocales(ObservableList<Locale>)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(locales);
            properties.put(ATTR__SUPPORTED_LOCALES, new SimpleObjectProperty(locales));
            
            return this;
        }

        @Override
        public ForthStep defaultLocale(final Locale locale) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.defaultLocale(Locale)"); // NOI18N
            
            DefaultI18NValidator.requireNonNull(locale);
            properties.put(ATTR__DEFAULT_LOCALE, new SimpleObjectProperty(locale));
            
            return this;
        }

        @Override
        public LastStep actualLocale(final Locale locale) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.actualLocale(Locale)"); // NOI18N
            
            DefaultI18NValidator.requireNonNull(locale);
            properties.put(ATTR__ACTUAL_LOCALE, new SimpleObjectProperty(locale));
            
            return this;
        }

        @Override
        public void build() {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.build()"); // NOI18N
            
            // Catch data
            final StringProperty baseBundleName   = (StringProperty) properties.get(ATTR__BASE_BUNDLE_NAME);
            final ObjectProperty supportedLocales = (ObjectProperty) properties.get(ATTR__SUPPORTED_LOCALES);
            final ObjectProperty defaultLocale    = (ObjectProperty) properties.get(ATTR__DEFAULT_LOCALE);
            final ObjectProperty actualLocale     = (ObjectProperty) properties.get(ATTR__ACTUAL_LOCALE);
            
            // Configure
            I18NFacade.getDefault().setBaseBundleName(baseBundleName.getValue());
            I18NFacade.getDefault().setSupportedLocales((ObservableList<Locale>) supportedLocales.getValue());
            I18NFacade.getDefault().setDefaultLocale((Locale) defaultLocale.getValue());
            I18NFacade.getDefault().setActualLocale((Locale) actualLocale.getValue());
            
            // And reset TODO ?
//            this.initialize();
        }
        
    }
    
}
