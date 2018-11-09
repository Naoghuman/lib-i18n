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
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public class DefaultI18NBinding implements I18NBinding {
    
    @Override
    public StringBinding createStringBinding(final String key) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        
        return Bindings.createStringBinding(() -> I18NFacade.getDefault().getString(key), I18NFacade.getDefault().actualLocaleProperty());
    }
    
    @Override
    public StringBinding createStringBinding(final String key, Object... arguments) {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(arguments);
        
        return Bindings.createStringBinding(() -> I18NFacade.getDefault().getString(key, arguments), I18NFacade.getDefault().actualLocaleProperty());
    }
    
    @Override
    public StringBinding createStringBinding(Callable<String> function) {
        DefaultI18NValidator.getDefault().requireNonNull(function);
        
        return Bindings.createStringBinding(function, I18NFacade.getDefault().actualLocaleProperty());
    }
    
}
