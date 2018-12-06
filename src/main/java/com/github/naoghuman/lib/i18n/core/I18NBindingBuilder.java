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

import com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator;
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
 * Examples 'Usage from I18NBindingBuilder'
 * 
 * 1) Starts the binding process.
 * 2) Use the given function to create a StringBinding.
 * 3) Completes the binding process and returns the StringBinding.
 * I18NBindingBuilder.bind()          // 1
 *        .callable(Callable<String>) // 2
 *        .build();                   // 3
 * 
 * 1) Starts the binding process.
 * 2) Defines the key which value will be bind to the StringBinding.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the binding process and returns the StringBinding.
 * I18NBindingBuilder.bind()         // 1
 *        .key(String)               // 2
 *        .arguments(Object... args) // 3
 *        .build();                  // 4
 */
/**
 * With the fluent builder {@code I18NBindingBuilder} the developer can easily create 
 * a {@link javafx.beans.binding.StringBinding} wrapped into a {@link java.util.Optional}.
 * <p>
 * With the builder the developer have 2 ways to create a {@code StringBinding}:
 * <ul>
 * <li>First with the usage from a function from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
 * <li>second with the usage from a {@code key} with optional {@code arguments}.</li>
 * </ul>
 * Hint:<br>
 * The {@code value} from the given {@code key} will be loaded from the previous 
 * configured {@link java.util.ResourceBundle} through the fluent builder 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.0
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 * @see     java.util.Optional
 * @see     java.util.ResourceBundle
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public final class I18NBindingBuilder {
    
    /**
     * Starting point from this fluent builder to generate a {@link javafx.beans.binding.StringBinding}.
     * <p>
     * The method {@code bind()} leads to the 2 choises how the developer will create the 
     * {@code StringBinding}.
     * <ul>
     * <li>First with a function from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
     * <li>and second with the usage from a {@code key} with optional {@code arguments}.</li>
     * </ul>
     * 
     * @return  the first step in this fluent builder.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.binding.StringBinding
     */
    public static final FirstStep bind() {
        
        return new I18NBindingBuilderImpl();
        
    }
    
    /**
     * First mandory step to generate a {@link javafx.beans.binding.StringBinding}.
     * <p>
     * This {@code Interface} allowed the developer to choose one of the 2 choises:
     * <ul>
     * <li>First the usage from a funcation from type {@link java.util.concurrent.Callable}&lt;String&gt;,</li>
     * <li>or second the usage from a {@code key} with optional {@code arguments} which 
     *     will be injected into the {@code value}.</li>
     * </ul>
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.binding.StringBinding
     */
    public interface FirstStep {
        
        /**
         * Setter for the developers choose to generate a {@link javafx.beans.binding.StringBinding} 
         * with a function from type {@link java.util.concurrent.Callable}&lt;String&gt;.
         * 
         * @param   function which will be used to generate a {@code StringBinding}.
         * @return  the last step in this fluent builder.
         * @throws  NullPointerException if {@code function} is NULL.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     java.util.concurrent.Callable
         * @see     javafx.beans.binding.StringBinding
         */
        public LastStep callable(final Callable<String> function);
        
        /**
         * Setter for the developers choose to generate a {@link javafx.beans.binding.StringBinding} 
         * with a {@code key}.
         * <p>
         * Hint:<br>
         * The {@code value} from the given {@code key} will be loaded from the previous 
         * configured {@link java.util.ResourceBundle} through the fluent builder 
         * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
         * 
         * @param   key which {@code value} should be loaded from the {@code ResourceBundle}.
         * @return  the second step in this fluent builder.
         * @throws  NullPointerException     if {@code key} is NULL.
         * @throws  IllegalArgumentException if {@code key} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
         * @see     java.util.ResourceBundle
         * @see     javafx.beans.binding.StringBinding
         */
        public SecondStep key(final String key);
        
    }
    
    /**
     * Second mandory step to generate a {@link javafx.beans.binding.StringBinding} 
     * if the developer has choosen the option to create a {@code StringBinding} 
     * with a {@code key}.
     * <p>
     * This {@code Interface} allowed the developer to choose one of the 2 choises:
     * <ul>
     * <li>First use the method {@code build()} if the {@code value} doesn't need any 
     *     {@code arguments} injected.</li>
     * <li>or second use the method {@code arguments(Object...)} which will then be injected 
     *     into the {@code value}.</li>
     * </ul>
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     javafx.beans.binding.StringBinding
     */
    public interface SecondStep {
        
        /**
         * Choose this option if for the previous defined {@code key} no addtional 
         * {@code arguments} are needed to injected into the {@code value}.
         * 
         * @return  the generated {@link javafx.beans.binding.StringBinding}.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     javafx.beans.binding.StringBinding
         */
        public Optional<StringBinding> build();
        
        /**
         * Choose this option if for the previsous defined {@code key} additional 
         * {@code arguments} are needed to injected into the {@code value}.
         * 
         * @param   arguments which should be injected into the {@code value}.
         * @return  the last step in this fluent builder.
         * @throws  NullPointerException     if {@code arguments} is NULL.
         * @throws  IllegalArgumentException if {@code arguments} is EMPTY.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         */
        public LastStep arguments(final Object... arguments);
        
    }
    
    /**
     * The last step in this fluent builder.
     * <p>
     * With the option {@code build()} the developer completes the previsous 
     * definition steps and create therewith the {@link javafx.beans.binding.StringBinding}.
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.6.0
     * @author  Naoghuman
     * @see     javafx.beans.binding.StringBinding
     */
    public interface LastStep {
        
        /**
         * Creates the {@link javafx.beans.binding.StringBinding} with the previous 
         * defined parameters {@code key} and optional {@code arguments}.
         * 
         * @return  the generated {@code StringBinding}.
         * @since   0.1.0-PRERELEASE
         * @version 0.6.0
         * @author  Naoghuman
         * @see     javafx.beans.binding.StringBinding
         */
        public Optional<StringBinding> build();
        
    }
    
    private static final class I18NBindingBuilderImpl implements
            FirstStep, LastStep, SecondStep
    {
        private static final String ATTR__ARGUMENTS = "arguments"; // NOI18N
        private static final String ATTR__FUNCTION  = "function";  // NOI18N
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
        
            properties.put(ATTR__FUNCTION,  new SimpleObjectProperty());
            properties.put(ATTR__KEY,       new SimpleStringProperty());
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty());
        }

        @Override
        public LastStep callable(final Callable<String> function) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.callable(Callable<String>)"); // NOI18N
            
            DefaultI18NValidator.requireNonNull(function);
            
            choosenConfiguration = Configuration.CALLABLE;
            properties.put(ATTR__FUNCTION, new SimpleObjectProperty(function));
            
            return this;
        }

        @Override
        public SecondStep key(final String key) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.key(String)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(key);
            
            choosenConfiguration = Configuration.KEY_ONLY;
            properties.put(ATTR__KEY, new SimpleStringProperty(key));
            
            return this;
        }

        @Override
        public LastStep arguments(final Object... argumtents) {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.arguments(Object...)"); // NOI18N
            
            DefaultI18NValidator.requireNonNullAndNotEmpty(argumtents);
            
            choosenConfiguration = Configuration.KEY_WITH_ARGUMENTS;
            properties.put(ATTR__ARGUMENTS, new SimpleObjectProperty(argumtents));
            
            return this;
        }

        @Override
        public Optional<StringBinding> build() {
            LoggerFacade.getDefault().debug(this.getClass(), "I18NBindingBuilderImpl.build()"); // NOI18N
            
            // Catch data
            final ObjectProperty function  = (ObjectProperty) properties.get(ATTR__FUNCTION);
            final StringProperty key       = (StringProperty) properties.get(ATTR__KEY);
            final ObjectProperty arguments = (ObjectProperty) properties.get(ATTR__ARGUMENTS);
            
            // Bind
            Optional<StringBinding> stringBinding = Optional.empty();
            switch(choosenConfiguration) {
                case CALLABLE: {
                    stringBinding = Optional.ofNullable(I18NFacade.getDefault().createStringBinding((Callable<String>) function.get()));
                    break;
                }
                case KEY_ONLY: {
                    stringBinding = Optional.ofNullable(I18NFacade.getDefault().createStringBinding(key.get()));
                    break;
                }
                case KEY_WITH_ARGUMENTS: {
                    stringBinding = Optional.ofNullable(I18NFacade.getDefault().createStringBinding(key.get(), (Object[]) arguments.get()));
                    break;
                }
                default: { }
            }

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
