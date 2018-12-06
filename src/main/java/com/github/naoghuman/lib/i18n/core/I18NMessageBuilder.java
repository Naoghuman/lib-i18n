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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/*
 * Specification: 'Usage of I18NMessageBuilder'
 *
 * 1) Starts the message process.
 * 2) Defines the key which value will be loaded.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the message process and returns a String.
 * I18NMessageBuilder.message() // 1
 *        .key(String)          // 2
 *        .arguments(Object...) // 3
 *        .build();             // 4
 */
/**
 * With the fluent builder {@code I18NMessageBuilder} the developer can easily receive 
 * the message from the previous defined {@link java.util.ResourceBundle} in 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in association 
 * to the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.ResourceBundle
 */
public final class I18NMessageBuilder {
    
    /**
     * Starting point from this fluent builder to received a message (associated {@code value}) 
     * from the previous configure {@link java.util.ResourceBundle} in 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in dependency from 
     * the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
     * 
     * @return  the first step in this fluent builder.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     * @see     java.util.ResourceBundle
     */
    public static final FirstStep message() {
        
        return new I18NMessageBuilderImpl();
        
    }
    
    /**
     * First mandory step to received a message (associated {@code value}) 
     * from the previous configure {@link java.util.ResourceBundle} in 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} in dependency from 
     * the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     * @see     java.util.ResourceBundle
     */
    public interface FirstStep {
        
        /**
         * Setter for the {@code key} which {@code value} will be loaded from the previous configure 
         * {@link java.util.ResourceBundle} in {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder} 
         * in dependency from the {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}.
         * 
         * @param   key which {@code value} should be loaded.
         * @return  the second step in this fluent builder.
         * @throws  NullPointerException     if {@code key} is NULL.
         * @throws  IllegalArgumentException if {@code key} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
         * @see     java.util.ResourceBundle
         */
        public SecondStep key(final String key);
        
    }
    
    /**
     * Second optional step in this fluent builder.
     * <p>
     * The developer have 2 possibilities in this step:
     * <ul>
     * <li>First complete the message process with the method {@code build()} which 
     *     will then return the {@code value} and</li>
     * <li>second add additional {@code arguments} which will then injected into the 
     *     {@code value} from the key.</li>
     * </ul>
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     */
    public interface SecondStep {
        
        /**
         * Completes the previous configuration steps and returned the corresponding {@code value}.
         * <p>
         * Hint:<br>
         * The {@code value} will be loaded in dependency from the 
         * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
         * from the previous configure {@link java.util.ResourceBundle} in 
         * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
         * 
         * @return  the loaded {@code value}.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
         */
        public String build();
        
        /**
         * Setter for additional {@code arguments} which will injected into the 
         * {@code value} from the previous definet {@code key}.
         * 
         * @param   arguments which should be injected into the {@code value}.
         * @return  The last step in this fluent builder.
         * @throws  NullPointerException     if {@code arguments} is NULL.
         * @throws  IllegalArgumentException if {@code arguments} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         */
        public LastStep arguments(final Object... arguments);
        
    }
    
    /**
     * The last step in this fluent builder which completes the previous configuration 
     * steps and returned the corresponding {@code value}.
     * <p>
     * Hint:<br>
     * The {@code value} will be loaded in dependency from the 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
     * from the previous configure {@link java.util.ResourceBundle} in 
     * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
     * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
     */
    public interface LastStep {
        
        /**
         * Completes the previous configuration steps and returned the corresponding {@code value}.
         * <p>
         * Hint:<br>
         * The {@code value} will be loaded in dependency from the 
         * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()}
         * from the previous configure {@link java.util.ResourceBundle} in 
         * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
         * 
         * @return  the loaded {@code value}.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
         */
        public String build();
        
    }
    
    private static final class I18NMessageBuilderImpl implements
            FirstStep, LastStep, SecondStep
    {
        private static final String ATTR__ARGUMENTS = "arguments"; // NOI18N
        private static final String ATTR__KEY       = "key"; // NOI18N
        
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
        
        private boolean messageHasArguments = Boolean.FALSE;
        
        private I18NMessageBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(this.getClass(), "I18NMessageBuilderImpl.initialize()"); // NOI18N
            
            messageHasArguments = Boolean.FALSE;
            
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty());
            properties.put(ATTR__KEY,       new SimpleStringProperty());
        }

        @Override
        public SecondStep key(final String key) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NMessageBuilderImpl.key(String)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(key);
            
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public LastStep arguments(final Object... arguments) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NMessageBuilderImpl.arguments(Object...)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(arguments);
            
            messageHasArguments = Boolean.TRUE;
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty(arguments));
            
            return this;
        }

        @Override
        public String build() {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NMessageBuilderImpl.build()"); // NOI18N
            
            // Catch data
            final ObjectProperty arguments = (ObjectProperty) properties.get(ATTR__ARGUMENTS);
            final StringProperty key       = (StringProperty) properties.get(ATTR__KEY);
            
            // Create
            String message;
            if (!messageHasArguments) {
                message = I18NFacade.getDefault().getMessage(key.getValue());
            }
            else {
                message = I18NFacade.getDefault().getMessage(key.getValue(), (Object[]) arguments.getValue());
            }
            
            return message;
        }
        
    }
    
}
