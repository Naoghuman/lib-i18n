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
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 */
public interface I18NBinding {
    
    /**
     * creates a String Binding to a localized String that is computed by calling the given function
     * 
     * @param  function
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public StringBinding createStringBinding(final Callable<String> function);
    
    /**
     * 
     * @param  key
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public StringBinding createStringBinding(final String key);
    
    /**
     * creates a String binding to a localized String for the given getString bundle key
     * 
     * @param  key
     * @param  arguments
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public StringBinding createStringBinding(final String key, final Object... arguments);
    
}
