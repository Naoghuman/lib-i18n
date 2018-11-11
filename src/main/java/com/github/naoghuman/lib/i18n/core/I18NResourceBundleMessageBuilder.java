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
    I18NResourceBundleMessageBuilder.getString()
           .key(String)
           .arguments(Object...) // optional
           .build()
*/
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NResourceBundleMessageBuilder {
    
    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static final FirstStep getString() {
        return new I18NResourceBundleMessageBuilderImpl();
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface FirstStep {
        
        /**
         * 
         * @param  key
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public SecondStep key(final String key);
        
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface SecondStep {
        
        /**
         * 
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public String build();
        
        /**
         * 
         * @param  arguments
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public LastStep arguments(final Object... arguments);
        
    }
    
    /**
     * 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public interface LastStep {
        
        /**
         * 
         * @return 
         * @since  0.1.0-PRERELEASE
         * @author Naoghuman
         */
        public String build();
        
    }
    
    private static final class I18NResourceBundleMessageBuilderImpl implements
            FirstStep, LastStep, SecondStep
    {
        private static final String ATTR__ARGUMENTS = "arguments"; // NOI18N
        private static final String ATTR__KEY       = "key"; // NOI18N
        
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
        
        private boolean messageHasArguments = Boolean.FALSE;
        
        private I18NResourceBundleMessageBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(this.getClass(), "I18NResourceBundleMessageBuilderImpl.initialize()"); // NOI18N
            
            messageHasArguments = Boolean.FALSE;
            
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty());
            properties.put(ATTR__KEY,       new SimpleStringProperty());
        }

        @Override
        public SecondStep key(final String key) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleMessageBuilderImpl.key(String)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(key);
            
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public LastStep arguments(final Object... arguments) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleMessageBuilderImpl.arguments(Object...)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(arguments);
            
            messageHasArguments = Boolean.TRUE;
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty(arguments));
            
            return this;
        }

        @Override
        public String build() {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NResourceBundleMessageBuilderImpl.build()"); // NOI18N
            
            // Catch data
            final ObjectProperty arguments = (ObjectProperty) properties.get(ATTR__ARGUMENTS);
            final StringProperty key       = (StringProperty) properties.get(ATTR__KEY);
            
            // Create
            String message;
            if (!messageHasArguments) {
                message = I18NFacade.getDefault().getString(key.getValue());
            }
            else {
                message = I18NFacade.getDefault().getString(key.getValue(), (Object[]) arguments.getValue());
            }
            
            // And reset TODO ?
//            this.initialize();
            
            return message;
        }
        
    }
    
}
