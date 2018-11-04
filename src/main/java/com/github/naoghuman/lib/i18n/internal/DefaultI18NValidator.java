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

import com.github.naoghuman.lib.i18n.core.I18NValidator;
import java.util.Optional;


/**
 * An implementation from the {@code Interface} 
 * {@link com.github.naoghuman.lib.i18n.core.I18NValidator} for the validation.
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NValidator
 */
public final class DefaultI18NValidator implements I18NValidator {
    
    private static final Optional<DefaultI18NValidator> INSTANCE = Optional.of(new DefaultI18NValidator());

    /**
     * 
     * @return 
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static final DefaultI18NValidator getDefault() {
        return INSTANCE.get();
    }

    private DefaultI18NValidator() {

    }
    
}
