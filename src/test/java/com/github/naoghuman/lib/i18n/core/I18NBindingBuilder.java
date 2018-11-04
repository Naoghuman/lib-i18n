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
package com.github.naoghuman.lib.i18n.core;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.concurrent.Callable;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Labeled;

/*
    I18NBindingBuilder.bind()
       .control(Labeled)
       .text(Callable<String>)
       .text(String)
       .text(String, Object... args)
       .tooltip(String)//optional
       .build()
*/
/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public final class I18NBindingBuilder {
    
    public static final FirstStep bind() {
        return new I18NBindingBuilderImpl();
    }
    
    public interface FirstStep {
        public SecondStep control(final Labeled control);
    }
    
    public interface SecondStep {
        public ThirdStep text(final Callable<String> func);
        public ThirdStep text(final String key);
        public ThirdStep text(final String key, final Object... args);
    }
    
    public interface ThirdStep {
        public void build();
        public LastStep tooltip(final String tooltip);
    }
    
    public interface LastStep {
        public void build();
    }
    
    private static final class I18NBindingBuilderImpl implements
            FirstStep, LastStep, SecondStep,
            ThirdStep
    {
        private static final String ATTR__ARGUMENTS = "arguments"; // NOI18N
        private static final String ATTR__CALLABLE  = "callable";  // NOI18N
        private static final String ATTR__CONTROL   = "control";   // NOI18N
        private static final String ATTR__KEY       = "key";       // NOI18N
        private static final String ATTR__TOOLTIP   = "tooltip";   // NOI18N
        
        @SuppressWarnings("rawtypes")
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
        
        private Function choosenFunction;
        
        private I18NBindingBuilderImpl() {
            this.initialize();
        }

        private void initialize() {
            LoggerFacade.getDefault().info(I18NBindingBuilder.class, "I18NBindingBuilder.initialize()"); // NOI18N
            
            choosenFunction = Function.NO_FUNCTION;
        
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty());
            properties.put(ATTR__CALLABLE,  new SimpleObjectProperty());
            properties.put(ATTR__CONTROL,   new SimpleObjectProperty());
            
            properties.put(ATTR__KEY,     new SimpleStringProperty());
            properties.put(ATTR__TOOLTIP, new SimpleStringProperty());
        }
        
        @Override
        public SecondStep control(final Labeled control) {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.control(Labeled)"); // NOI18N
            
//            DefaultI18NValidator.getDefault().requireNonNull(control);
            properties.put(ATTR__CONTROL, new SimpleObjectProperty(control));
            
            return this;
        }

        @Override
        public ThirdStep text(final Callable<String> callable) {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.text(Callable<String>)"); // NOI18N
            
//            DefaultI18NValidator.getDefault().requireNonNull(callable);
            
            choosenFunction = Function.CALLABLE;
            properties.put(ATTR__CALLABLE, new SimpleObjectProperty(callable));
            
            return this;
        }

        @Override
        public ThirdStep text(final String key) {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.text(String)"); // NOI18N
            
//            DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
            
            choosenFunction = Function.SIMPLE_MESSAGE;
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public ThirdStep text(final String key, final Object... argumtents) {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.text(String, Object...)"); // NOI18N
            
//            DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
//            DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(argumtents);
            
            choosenFunction = Function.EXTENDED_MESSAGE;
            properties.put(ATTR__KEY,       new SimpleStringProperty(key));
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty(argumtents));
            
            return this;
        }

        @Override
        public LastStep tooltip(final String tooltip) {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.tooltip(String)"); // NOI18N
            
//            DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(tooltip);
            properties.put(ATTR__TOOLTIP, new SimpleStringProperty(tooltip));
            
            return this;
        }

        @Override
        public void build() {
            LoggerFacade.getDefault().debug(I18NBindingBuilder.class, "I18NBindingBuilder.build()"); // NOI18N
            
            // Catch data
            final ObjectProperty arguments = (ObjectProperty) properties.get(ATTR__ARGUMENTS);
            final ObjectProperty control   = (ObjectProperty) properties.get(ATTR__CONTROL);
            final ObjectProperty callable  = (ObjectProperty) properties.get(ATTR__CALLABLE);
            
            final StringProperty key     = (StringProperty) properties.get(ATTR__KEY);
            final StringProperty tooltip = (StringProperty) properties.get(ATTR__TOOLTIP);
            
            // Bind
//            switch(choosenFunction) {
//                case CALLABLE: {
//                    final StringBinding stringBinding = I18NFacade.getDefault().create(
//                            I18NFacade.getDefault().localeProperty(),
//                            (Callable<String>) callable.getValue());
//                    ((Labeled) control.getValue()).textProperty().bind(stringBinding);
//                    break;
//                }
//                case SIMPLE_MESSAGE: {
//                    final StringBinding stringBinding = I18NFacade.getDefault().create(
//                            I18NFacade.getDefault().localeProperty(),
//                            I18NFacade.getDefault().message(
//                                    I18NFacade.getDefault().getResourceBundle(),
//                                    key.getValue()));
//                    ((Labeled) control.getValue()).textProperty().bind(stringBinding);
//                    break;
//                }
//                case EXTENDED_MESSAGE: {
//                    final StringBinding stringBinding = I18NFacade.getDefault().create(
//                            I18NFacade.getDefault().localeProperty(),
//                            I18NFacade.getDefault().message(
//                                    I18NFacade.getDefault().getResourceBundle(),
//                                    key.getValue(),
//                                    (Object[]) arguments.getValue()));
//                    ((Labeled) control.getValue()).textProperty().bind(stringBinding);
//                    break;
//                }
//                default: { }
//            }
            
            // And reset
//            this.initialize();
        }
        
        enum Function {
            
            CALLABLE,
            NO_FUNCTION,
            SIMPLE_MESSAGE,
            EXTENDED_MESSAGE;
            
        }
        
    }
    
}
