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
package com.github.naoghuman.app.i18n.demo.prototype4.core;

import java.util.Arrays;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 * @since  0.1.0-PRERELEASE
 */
public interface I18NValidator4 {
    
    /**
     * Delegates to {@link java.util.Objects#isNull(java.lang.Object)}. Returns 
     * {@code TRUE} if the provided reference is {@code NULL} otherwise {@code FALSE}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate}, 
     * {@code filter(Objects::isNull)}.
     * 
     * @author Naoghuman
     * @since  0.1.0-PRERELEASE
     * @param  obj a reference which will be checked against {@code NULL}.
     * @return {@code TRUE} if the provided reference is {@code NULL} otherwise
     *         {@code FALSE}.
     */
    public default boolean isNull(final Object obj) {
        return Objects.isNull(obj);
    }
    
    /**
     * Delegates to {@link java.util.Objects#nonNull(java.lang.Object)}. Returns 
     * {@code TRUE} if the provided reference is {@code NON-NULL} otherwise {@code FALSE}.
     * <p>
     * This method exists to be used as a {@link java.util.function.Predicate},
     * {@code filter(Objects::nonNull)}.
     * 
     * @author Naoghuman
     * @since  0.1.0-PRERELEASE
     * @param  obj a reference which will be checked against {@code NULL}.
     * @return {@code TRUE} if the provided reference is {@code NON-NULL} otherwise
     *         {@code FALSE}.
     */
    public default boolean nonNull(final Object obj) {
        return Objects.nonNull(obj);
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL}.
     *
     * @author Naoghuman
     * @since  0.1.0-PRERELEASE
     * @param  value the attribute which should be validated.
     * @param  <T>   the type of the reference.
     * @throws NullPointerException if {@code (value == NULL)}.
     */
    public default <T> void requireNonNull(final T value) throws NullPointerException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL"); // NOI18N
    }
    
    /**
     * Validates if the attribute {@code value} isn't {@code NULL} and not {@code EMPTY}.
     *
     * @author Naoghuman
     * @since  0.1.0-PRERELEASE
     * @param  value the attribute which should be validated.
     * @throws NullPointerException     if {@code (value        == NULL)}.
     * @throws IllegalArgumentException if {@code (value.trim() == EMPTY)}.
     */
    public default void requireNonNullAndNotEmpty(final String value) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(value, "The attribute [value] can't be NULL"); // NOI18N
        
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("The attribute [value] can't be EMPTY"); // NOI18N
        }
    }
    
    public default void requireNonNullAndNotEmpty(final Object... elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL"); // NOI18N
        
        final ObservableList<Object> elements2 = FXCollections.observableArrayList();
        elements2.addAll(Arrays.asList(elements));
        
        if (elements2.isEmpty()) {
            throw new IllegalArgumentException("The Object[] shouldn't be EMPTY"); // NOI18N
        }
    }
    
    public default <T> void requireNonNullAndNotEmpty(final ObservableList<T> elements) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(elements, "The attribute [elements] can't be NULL"); // NOI18N
        
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("The [ObservableList] shouldn't be EMPTY"); // NOI18N
        }
    }
    
}
