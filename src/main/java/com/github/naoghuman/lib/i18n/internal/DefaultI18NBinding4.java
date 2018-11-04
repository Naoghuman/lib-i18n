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
package com.github.naoghuman.app.i18n.demo.prototype4.internal;

import com.github.naoghuman.app.i18n.demo.prototype4.core.I18NBinding4;
import com.github.naoghuman.app.i18n.demo.prototype4.core.I18NFacade4;
import java.util.concurrent.Callable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

/**
 *
 * @author PRo
 */
public class DefaultI18NBinding4 implements I18NBinding4 {
    
//    public static void bind(StringProperty property, Callable<String> func) {
//        property.bind(DefaultI18NBinding4.createStringBinding(func));
//    }
//    
//    public static void bind(StringProperty property, final String key, final Object... args) {
//        property.bind(DefaultI18NBinding4.createStringBinding(key, args));
//    }
    @Override
    public StringBinding createStringBinding(final String key) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        
        return Bindings.createStringBinding(
                () -> I18NFacade4.getDefault().getString(key), I18NFacade4.getDefault().actualLocaleProperty());
    }
    
    /**
     * creates a String binding to a localized String for the given getString bundle key
     *
     * @param key key
     * @param args
     * @return String binding
     */
    @Override
    public StringBinding createStringBinding(final String key, Object... args) {
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(key);
        DefaultI18NValidator4.getDefault().requireNonNullAndNotEmpty(args);
        
        return Bindings.createStringBinding(
                () -> I18NFacade4.getDefault().getString(key, args), I18NFacade4.getDefault().actualLocaleProperty());
    }
    
    /**
     * creates a String Binding to a localized String that is computed by calling the given func
     *
     * @param function
     *         function called on every change
     * @return StringBinding
     */
    @Override
    public StringBinding createStringBinding(Callable<String> function) {
        DefaultI18NValidator4.getDefault().requireNonNull(function);
        
        return Bindings.createStringBinding(function, I18NFacade4.getDefault().actualLocaleProperty());
    }
    
}
