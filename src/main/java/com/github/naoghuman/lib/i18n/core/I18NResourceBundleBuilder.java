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
    I18NResourceBundleBuilder.configure()
        .baseName(String)
        .defaultLocale(Locale)
        .actualLocale(Locale)
        .supportedLocales(Locale...)
        .build()
*/
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NResourceBundleBuilder {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static final FirstStep configure() {
        return new I18NResourceBundleBuilderImpl();
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface FirstStep {
        
        /**
         * 
         * @param  baseName
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public SecondStep baseName(final String baseName);
        
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface SecondStep {
        
        /**
         * 
         * @param  locales
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public ThirdStep supportedLocales(final ObservableList<Locale> locales);
    
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface ThirdStep {
        
        /**
         * 
         * @param  locale
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public ForthStep defaultLocale(final Locale locale);
        
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface ForthStep {
        
        /**
         * 
         * @param  locale
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public LastStep actualLocale(final Locale locale);
        
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface LastStep {
        
        /**
         * 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public void build();
        
    }
    
    private static final class I18NResourceBundleBuilderImpl implements
            FirstStep,  ForthStep, LastStep, 
            SecondStep, ThirdStep
    {
        private static final String ATTR__BASENAME          = "baseName"; // NOI18N
        private static final String ATTR__ACTUAL_LOCALE     = "actualLocale";   // NOI18N
        private static final String ATTR__DEFAULT_LOCALE    = "defaultLocale";   // NOI18N
        private static final String ATTR__SUPPORTED_LOCALES = "supportedLocales"; // NOI18N
    
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
        
        private I18NResourceBundleBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(I18NResourceBundleBuilder.class, "I18NResourceBundleBuilderImpl.initialize()"); // NOI18N
            
            properties.put(ATTR__BASENAME,          new SimpleStringProperty());
            properties.put(ATTR__ACTUAL_LOCALE,     new SimpleObjectProperty());
            properties.put(ATTR__DEFAULT_LOCALE,    new SimpleObjectProperty());
            properties.put(ATTR__SUPPORTED_LOCALES, new SimpleObjectProperty());
        }

        @Override
        public SecondStep baseName(final String baseName) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleBuilderImpl.baseName(String)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(baseName);
            properties.put(ATTR__BASENAME, new SimpleStringProperty(baseName));
            
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
            final StringProperty baseName         = (StringProperty) properties.get(ATTR__BASENAME);
            final ObjectProperty supportedLocales = (ObjectProperty) properties.get(ATTR__SUPPORTED_LOCALES);
            final ObjectProperty defaultLocale    = (ObjectProperty) properties.get(ATTR__DEFAULT_LOCALE);
            final ObjectProperty actualLocale     = (ObjectProperty) properties.get(ATTR__ACTUAL_LOCALE);
            
            // Configure
            I18NFacade.getDefault().setBaseName(baseName.getValue());
            I18NFacade.getDefault().setSupportedLocales((ObservableList<Locale>) supportedLocales.getValue());
            I18NFacade.getDefault().setDefaultLocale((Locale) defaultLocale.getValue());
            I18NFacade.getDefault().setActualLocale((Locale) actualLocale.getValue());
            
            // And reset TODO ?
//            this.initialize();
        }
        
    }
    
}
