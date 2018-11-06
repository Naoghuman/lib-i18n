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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the {@code Interface} {@link com.github.naoghuman.lib.i18n.core.I18NValidator}
 * with its default implementation {@link com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator}.
 *
 * @since  0.2.0-PRERELEASE
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NValidator
 * @see    com.github.naoghuman.lib.i18n.internal.DefaultI18NValidator
 */
public class DefaultI18NValidatorTest {
    
    public DefaultI18NValidatorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getDefault() {
        assertNotNull(DefaultI18NValidator.getDefault());
    }
    
    @Test
    public void isNullTrue() {
        String hello = null;
        assertTrue(DefaultI18NValidator.getDefault().isNull(hello));
    }
    
    @Test
    public void isNullFalse() {
        String hello = "hello";
        assertFalse(DefaultI18NValidator.getDefault().isNull(hello));
    }
    
    @Test
    public void nonNullTrue() {
        String hello = "hello";
        assertTrue(DefaultI18NValidator.getDefault().nonNull(hello));
    }
    
    @Test
    public void nonNullFalse() {
        String hello = null;
        assertFalse(DefaultI18NValidator.getDefault().nonNull(hello));
    }
    
    @Test
    public void requireNonNull() {
        String hello = "hello";
        DefaultI18NValidator.getDefault().requireNonNull(hello);
    }
    
    @Test(expected = NullPointerException.class)
    public void requireNonNullThrowsNullPointerException() {
        String hello = null;
        DefaultI18NValidator.getDefault().requireNonNull(hello);
    }
    
    @Test
    public void requireNonNullAndNotEmptyString() {
        String hello = "hello";
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(hello);
    }
    
    @Test(expected = NullPointerException.class)
    public void requireNonNullAndNotEmptyStringThrowsNullPointerException() {
        String hello = null;
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(hello);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void requireNonNullAndNotEmptyStringThrowsIllegalArgumentException() {
        String hello = "";
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(hello);
    }
    
    @Test
    public void requireNonNullAndNotEmptyObjectArray() {
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty("hello", "hi");
    }
    
    @Test
    public void requireNonNullAndNotEmptyObservableList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("hello", "hi");
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(list);
    }
    
    @Test(expected = NullPointerException.class)
    public void requireNonNullAndNotEmptyObservableListThrowsNullPointerException() {
        ObservableList<String> list = null;
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(list);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void requireNonNullAndNotEmptyObservableListThrowsIllegalArgumentException() {
        ObservableList<String> list = FXCollections.observableArrayList();
        DefaultI18NValidator.getDefault().requireNonNullAndNotEmpty(list);
    }
    
}
