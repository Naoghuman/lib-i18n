/*
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
package com.github.naoghuman.app.i18n.demo.prototype4.core;

import com.github.naoghuman.app.i18n.demo.prototype4.internal.DefaultI18NValidator4;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/*
    I18NMessageBuilder.getString()
       .key(String)
       .arguments(Object...) // optional
       .build()
*/
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NMessageBuilder4 {
    
    public static final FirstStep message() {
        return new I18NMessageBuilderImpl();
    }
    
    public interface FirstStep {
        public SecondStep key(final String key);
    }
    
    public interface SecondStep {
        public String build();
        public LastStep arguments(final Object... arguments);
    }
    
    public interface LastStep {
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
            
            DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
            
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public LastStep arguments(final Object... arguments) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NMessageBuilderImpl.arguments(Object...)"); // NOI18N
            
            DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(arguments);
            
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
                message = I18NFacade4.getDefault().getString(key.getValue());
            }
            else {
                message = I18NFacade4.getDefault().getString(key.getValue(), (Object[]) arguments.getValue());
            }
            
            // And reset TODO ?
//            this.initialize();
            
            return message;
        }
        
    }
    
}
