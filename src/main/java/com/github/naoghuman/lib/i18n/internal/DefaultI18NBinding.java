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
package com.github.naoghuman.lib.i18n.internal;

import com.github.naoghuman.lib.i18n.core.I18NBinding;
import com.github.naoghuman.lib.i18n.core.I18NFacade;
import java.util.concurrent.Callable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

/**
 * The {@code default} implementation from the interface 
 * {@link com.github.naoghuman.lib.i18n.core.I18NBinding}.
 * <p>
 * Given {@code attributes} in the methods will be checked by 
 * {@link com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NBinding
 * @see     com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator
 */
public final class DefaultI18NBinding implements I18NBinding {
    
    @Override
    public StringBinding createStringBinding(final String key) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(key);
        
        return Bindings.createStringBinding(() -> I18NFacade.getDefault().getMessage(key), I18NFacade.getDefault().actualLocaleProperty());
    }
    
    @Override
    public StringBinding createStringBinding(final String key, Object... arguments) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.requireNonNullAndNotEmpty(arguments);
        
        return Bindings.createStringBinding(() -> I18NFacade.getDefault().getMessage(key, arguments), I18NFacade.getDefault().actualLocaleProperty());
    }
    
    @Override
    public StringBinding createStringBinding(Callable<String> function) {
        DefaultI18NValidator.requireNonNull(function);
        
        return Bindings.createStringBinding(function, I18NFacade.getDefault().actualLocaleProperty());
    }
    
}
