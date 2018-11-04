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

import com.github.naoghuman.app.i18n.demo.prototype4.core.I18NValidator4;
import java.util.Optional;


/**
 * An implementation from the {@code Interface} 
 * {@link com.github.naoghuman.lib.i18n.core.I18NValidator} for the validation.
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 * @see    com.github.naoghuman.lib.i18n.core.I18NValidator
 */
public final class DefaultI18NValidator4 implements I18NValidator4 {
    
    private static final Optional<DefaultI18NValidator4> INSTANCE = Optional.of(new DefaultI18NValidator4());

    /**
     * Returns a singleton instance from the class {@code DefaultI18NValidator}.
     *
     * @return a singleton instance from this class {@code DefaultI18NValidator}.
     */
    public static final DefaultI18NValidator4 getDefault() {
        return INSTANCE.get();
    }

    private DefaultI18NValidator4() {

    }
    
}
