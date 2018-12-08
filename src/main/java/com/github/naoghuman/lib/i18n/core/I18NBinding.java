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

import java.util.concurrent.Callable;
import javafx.beans.binding.StringBinding;

/**
 * This {@code Interface} gives the developer the possibilities to create a 
 * {@link javafx.beans.binding.StringBinding} which is associated with a 
 * {@code key} / value} pair from a {@link java.util.ResourceBundle}.
 * <p>
 * To associated a {@code key} with a {@code StringBinding} the developer can use 
 * on the one side the methods which will expected directly a {@code key} (with 
 * optional {@code arguments}) or on the other side a {@link java.util.concurrent.Callable} 
 * which computes then the message.
 * <p>
 * The preferred way to used the methods from this interface is the usage from the 
 * builder {@link com.github.naoghuman.lib.i18n.core.I18NBindingBuilder}.<br>
 * An other option for advanced developers is the facade 
 * {@link com.github.naoghuman.lib.i18n.core.I18NFacade}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NBindingBuilder
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade
 * @see     java.util.ResourceBundle
 * @see     java.util.concurrent.Callable
 * @see     javafx.beans.binding.StringBinding
 */
public interface I18NBinding {
    
    /**
     * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
     * that is computed by calling the given {@code function}.
     * <p>
     * Internal the {@code StringBinding} will be created with 
     * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
     * where the {@code Observable} is a {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
     * 
     * @param   function which should be used to create the {@code StringBinding}.
     * @return  the created {@code StringBinding}.
     * @throws  NullPointerException if {@code function} is NULL.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.Observable
     * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
     * @see     javafx.beans.binding.StringBinding
     */
    public StringBinding createStringBinding(final Callable<String> function);
    
    /**
     * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
     * that is computed by calling the given {@code key}.
     * <p>
     * Internal the {@code StringBinding} will be created with 
     * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
     * where the {@code Observable} is a {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
     * 
     * @param   key which should be used to load the associated {@code value}.
     * @return  the created {@code StringBinding}.
     * @throws  NullPointerException     if {@code key} is NULL.
     * @throws  IllegalArgumentException if {@code key} is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.Observable
     * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
     * @see     javafx.beans.binding.StringBinding
     */
    public StringBinding createStringBinding(final String key);
    
    /**
     * Creates a {@link javafx.beans.binding.StringBinding} to a localized String 
     * that is computed by calling the given {@code key} and the {@code arguments}.
     * <p>
     * Internal the {@code StringBinding} will be created with 
     * {@link javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...) } 
     * where the {@code Observable} is a {@link com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty() }.
     * 
     * @param   key       which should be used to load the associated {code value}.
     * @param   arguments which should be injected into the associated {code value}.
     * @return  the created {@code StringBinding}.
     * @throws  NullPointerException     if ({@code key} || {@code arguments}) is NULL.
     * @throws  IllegalArgumentException if ({@code key} || {@code arguments}) is EMPTY.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     * @see     com.github.naoghuman.lib.i18n.core.I18NFacade#actualLocaleProperty()
     * @see     java.util.concurrent.Callable
     * @see     javafx.beans.Observable
     * @see     javafx.beans.binding.Bindings#createStringBinding(java.util.concurrent.Callable, javafx.beans.Observable...)
     * @see     javafx.beans.binding.StringBinding
     */
    public StringBinding createStringBinding(final String key, final Object... arguments);
    
}
