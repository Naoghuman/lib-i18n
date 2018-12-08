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
/**
 * The {@code core} package from the library {@code Lib-I18N} contains all functionalities 
 * to register a {@link java.util.ResourceBundle}, access the {@code values} from the 
 * bundle over the {@code keys} with optional {@code arguments} and bind them through 
 * a {@link javafx.beans.binding.StringBinding} to a {@link javafx.beans.property.StringProperty}.
 * <p>
 * That means switching the {@code actual} {@link java.util.Locale} (see 
 * {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty() } 
 * for more information) will update automatically all binded {@code StringProperties}.
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle#actualLocaleProperty()
 * @see     java.util.Locale
 * @see     java.util.ResourceBundle
 * @see     javafx.beans.binding.StringBinding
 * @see     javafx.beans.property.StringProperty
 */
package com.github.naoghuman.lib.i18n.core;
