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
package com.github.naoghuman.app.i18n.demo.prototype4.core;

import com.github.naoghuman.app.i18n.demo.prototype4.internal.DefaultI18NValidator4;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.Optional;
import java.util.concurrent.Callable;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/*
    I18NBindingBuilder.bind()
       .callable(Callable<String>)
       .key(String)
       .arguments(Object... args) // optional
       .build()
*/
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NBindingBuilder4 {
    
    public static final FirstStep bind() {
        return new I18NBindingBuilderImpl();
    }
    
    public interface FirstStep {
        public LastStep callable(final Callable<String> callable);
        public SecondStep key(final String key);
    }
    
    public interface SecondStep {
        public Optional<StringBinding> build();
        public LastStep arguments(final Object... arguments);
    }
    
    public interface LastStep {
        public Optional<StringBinding> build();
    }
    
    private static final class I18NBindingBuilderImpl implements
            FirstStep, LastStep, SecondStep
    {
        private static final String ATTR__ARGUMENTS = "arguments"; // NOI18N
        private static final String ATTR__CALLABLE  = "callable";  // NOI18N
        private static final String ATTR__KEY       = "key";       // NOI18N
        
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();

        private Configuration choosenConfiguration;
        
        private I18NBindingBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(this.getClass(), "I18NBindingBuilderImpl.initialize()"); // NOI18N
            
            choosenConfiguration = Configuration.NO_FUNCTION;
        
            properties.put(ATTR__CALLABLE,  new SimpleObjectProperty());
            properties.put(ATTR__KEY,       new SimpleStringProperty());
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty());
        }

        @Override
        public LastStep callable(final Callable<String> callable) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.callable(Callable<String>)"); // NOI18N
            
            DefaultI18NValidator4.getDefault().requireNonNull(callable);
            choosenConfiguration = Configuration.CALLABLE;
            properties.put(ATTR__CALLABLE, new SimpleObjectProperty(callable));
            
            return this;
        }

        @Override
        public SecondStep key(final String key) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.key(String)"); // NOI18N
            
            DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
            choosenConfiguration = Configuration.KEY_ONLY;
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public LastStep arguments(final Object... argumtents) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.arguments(Object...)"); // NOI18N
            
            DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(argumtents);
            choosenConfiguration = Configuration.KEY_WITH_ARGUMENTS;
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty(argumtents));
            
            return this;
        }

        @Override
        public Optional<StringBinding> build() {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.build()"); // NOI18N
            
            // Catch data
            final ObjectProperty callable  = (ObjectProperty) properties.get(ATTR__CALLABLE);
            final StringProperty key       = (StringProperty) properties.get(ATTR__KEY);
            final ObjectProperty arguments = (ObjectProperty) properties.get(ATTR__ARGUMENTS);
            
            // Bind
            Optional<StringBinding> stringBinding = Optional.empty();
            switch(choosenConfiguration) {
                case CALLABLE: {
                    stringBinding = Optional.ofNullable(I18NFacade4.getDefault().createStringBinding((Callable<String>) callable.get()));
                    break;
                }
                case KEY_ONLY: {
                    stringBinding = Optional.ofNullable(I18NFacade4.getDefault().createStringBinding(key.get()));
                    break;
                }
                case KEY_WITH_ARGUMENTS: {
                    stringBinding = Optional.ofNullable(I18NFacade4.getDefault().createStringBinding(key.get(), (Object[]) arguments.get()));
                    break;
                }
                default: { }
            }
            
            // And reset TODO ?
//            this.initialize();

            return stringBinding;
        }
        
        enum Configuration {
            NO_FUNCTION,
            CALLABLE,
            KEY_ONLY,
            KEY_WITH_ARGUMENTS;
        }
        
    }
    
}
