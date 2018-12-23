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

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * An implementation from different {@code validation} methods to check preconditions 
 * in the topic from this library {@code Lib-I18N}.
 *
 * @since   0.1.0-PRERELEASE
 * @version 0.6.1
 * @author  Naoghuman
 */
public final class DefaultI18NValidator {
    
    /**
     * Delegates to {@link java.util.Objects#isNull(java.lang.Object)}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate}, 
     * {@code filter(Objects::isNull)}.
     * 
     * @param   obj a reference which will be checked against {@code NULL}.
     * @return  {@code TRUE} if the provided reference is {@code NULL} otherwise {@code FALSE}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     * @see     java.util.function.Predicate
     */
    public static boolean isNull(final Object obj) {
        return Objects.isNull(obj);
    }
    
    /**
     * Delegates to {@link java.util.Objects#nonNull(java.lang.Object)}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate},
     * {@code filter(Objects::nonNull)}.
     * 
     * @param   obj a reference which will be checked against {@code NULL}.
     * @return  {@code TRUE} if the provided reference is {@code NON-NULL} otherwise {@code FALSE}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     * @see     java.util.function.Predicate
     */
    public static boolean nonNull(final Object obj) {
        return Objects.nonNull(obj);
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL}.
     * <p>
     * An additional error message will be added to the error stack:
     * <ul>
     * <li>The attribute [value] can't be NULL.</li>
     * </ul>
     *
     * @param   <T>   the type of the reference.
     * @param   value the attribute which should be validated.
     * @throws  NullPointerException if {@code (value == NULL)}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     */
    public static <T> void requireNonNull(final T value) throws NullPointerException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL."); // NOI18N
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL} and not {@code EMPTY}.
     * <p>
     * Adds following additional error messages depending from the error to the error stack:
     * <ul>
     * <li>The attribute [value] can't be NULL.</li>
     * <li>The attribute [value] can't be EMPTY.</li>
     * </ul>
     *
     * @param   value the attribute which should be validated.
     * @throws  NullPointerException     if {@code (value        == NULL)}.
     * @throws  IllegalArgumentException if {@code (value.trim() == EMPTY)}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     */
    public static void requireNonNullAndNotEmpty(final String value) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL."); // NOI18N
        
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("The attribute [value] can't be EMPTY."); // NOI18N
        }
    }
    
    /**
     * Validates if the attribute {@code elements} isn't {@code NULL} and not {@code EMPTY}.
     * <p>
     * Adds following additional error messages depending from the error to the error stack:
     * <ul>
     * <li>The attribute [elements] can't be NULL.</li>
     * <li>The attribute [elements] can't be EMPTY.</li>
     * </ul>
     * 
     * @param   elements the attribute which should be validated.
     * @throws  NullPointerException     if {@code (elements == NULL)}.
     * @throws  IllegalArgumentException if {@code (elements == EMPTY)}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     */
    public static void requireNonNullAndNotEmpty(final Object... elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL."); // NOI18N
        
        final ObservableList<Object> elements2 = FXCollections.observableArrayList();
        elements2.addAll(Arrays.asList(elements));
        
        if (elements2.isEmpty()) {
            throw new IllegalArgumentException("The attribute [elements] can't be EMPTY."); // NOI18N
        }
    }
    
    /**
     * Validates if the attribute {@code elements} isn't {@code NULL} and not {@code EMPTY}.
     * <p>
     * Adds following additional error messages depending from the error to the error stack:
     * <ul>
     * <li>The attribute [elements] can't be NULL.</li>
     * <li>The attribute [elements] can't be EMPTY.</li>
     * </ul>
     * 
     * @param   <T>      the type of the reference.
     * @param   elements the attribute which should be validated.
     * @throws  NullPointerException     if {@code (elements == NULL)}.
     * @throws  IllegalArgumentException if {@code (elements == EMPTY)}.
     * @since   0.1.0-PRERELEASE
     * @version 0.6.1
     * @author  Naoghuman
     */
    public static <T> void requireNonNullAndNotEmpty(final ObservableList<T> elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL"); // NOI18N
        
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("The attribute [elements] can't be EMPTY"); // NOI18N
        }
    }
    
    /**
     * Checks if a {@link java.util.ResourceBundle} with the given parameters can be loaded.
     * <p>
     * If the {@code ResourceBundle} can't be found a {@link java.util.MissingResourceException} 
     * will be thrown.
     * 
     * @param   baseBundleName which should be used to load the {@code ResourceBundle}.
     * @param   actualLocale which should be used to load the {@code ResourceBundle}.
     * @since   0.7.0
     * @version 0.7.0
     * @author  Naoghuman
     * @see     java.util.MissingResourceException
     * @see     java.util.ResourceBundle
     */
    public static void requireResourceBundleExists(final String baseBundleName, final Locale actualLocale) {
        DefaultI18NValidator.requireNonNullAndNotEmpty(baseBundleName);
        DefaultI18NValidator.requireNonNull(actualLocale);
        
        ResourceBundle.getBundle(baseBundleName, actualLocale);
    }
    
}
