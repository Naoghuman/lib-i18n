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
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * An implementation from the {@code Interface} 
 * {@link com.github.naoghuman.lib.i18n.core.I18NValidator} for the validation.
 *
 * @since  0.1.0-PRERELEASE
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NValidator
 */
public final class DefaultI18NValidator {
    
    /**
     * Delegates to {@link java.util.Objects#isNull(java.lang.Object)}. Returns 
     * {@code TRUE} if the provided reference is {@code NULL} otherwise {@code FALSE}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate}, 
     * {@code filter(Objects::isNull)}.
     * 
     * @param  obj a reference which will be checked against {@code NULL}.
     * @return {@code TRUE} if the provided reference is {@code NULL} otherwise
     *         {@code FALSE}.
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static boolean isNull(final Object obj) {
        return Objects.isNull(obj);
    }
    
    /**
     * Delegates to {@link java.util.Objects#nonNull(java.lang.Object)}. Returns 
     * {@code TRUE} if the provided reference is {@code NON-NULL} otherwise {@code FALSE}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate},
     * {@code filter(Objects::nonNull)}.
     * 
     * @param  obj a reference which will be checked against {@code NULL}.
     * @return {@code TRUE} if the provided reference is {@code NON-NULL} otherwise
     *         {@code FALSE}.
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     */
    public static boolean nonNull(final Object obj) {
        return Objects.nonNull(obj);
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL}.
     *
     * @param  value the attribute which should be validated.
     * @param  <T>   the type of the reference.
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     * @throws NullPointerException if {@code (value == NULL)}.
     */
    public static <T> void requireNonNull(final T value) throws NullPointerException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL"); // NOI18N
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL} and not {@code EMPTY}.
     *
     * @param  value the attribute which should be validated.
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     * @throws NullPointerException     if {@code (value        == NULL)}.
     * @throws IllegalArgumentException if {@code (value.trim() == EMPTY)}.
     */
    public static void requireNonNullAndNotEmpty(final String value) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL"); // NOI18N
        
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("The attribute [value] can't be EMPTY"); // NOI18N
        }
    }
    
    /**
     * 
     * @param  elements
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     * @throws NullPointerException
     * @throws IllegalArgumentException 
     */
    public static void requireNonNullAndNotEmpty(final Object... elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL"); // NOI18N
        
        final ObservableList<Object> elements2 = FXCollections.observableArrayList();
        elements2.addAll(Arrays.asList(elements));
        
        if (elements2.isEmpty()) {
            throw new IllegalArgumentException("The Object[] shouldn't be EMPTY"); // NOI18N
        }
    }
    
    /**
     * 
     * @param  <T>
     * @param  elements
     * @since  0.1.0-PRERELEASE
     * @author Naoghuman
     * @throws NullPointerException
     * @throws IllegalArgumentException 
     */
    public static <T> void requireNonNullAndNotEmpty(final ObservableList<T> elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL"); // NOI18N
        
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("The [ObservableList] shouldn't be EMPTY"); // NOI18N
        }
    }
    
}
