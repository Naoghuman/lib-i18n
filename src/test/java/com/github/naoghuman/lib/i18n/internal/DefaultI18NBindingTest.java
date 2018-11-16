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
package com.github.naoghuman.lib.i18n.internal;

import com.github.naoghuman.lib.i18n.core.I18NBinding;
import com.github.naoghuman.lib.i18n.core.I18NFacade;
import java.util.Locale;
import java.util.concurrent.Callable;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the {@code Interface} {@link com.github.naoghuman.lib.i18n.core.I18NBinding}
 * with its default implementation {@link com.github.naoghuman.lib.i18n.internal.DefaultI18NBinding}.
 *
 * @since  0.2.0-PRERELEASE
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NBinding
 * @see    com.github.naoghuman.lib.i18n.internal.DefaultI18NBinding
 */
public class DefaultI18NBindingTest {
    
    private static final String RESOURCE_BUNDLE = "com.github.naoghuman.lib.i18n.internal.binding"; // NOI18N
    
    public DefaultI18NBindingTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createStringBinding_String() {
        I18NFacade.getDefault().setBaseBundleName(RESOURCE_BUNDLE);
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        I18NFacade.getDefault().setSupportedLocales(supportedLocales);
        
        I18NBinding b = new DefaultI18NBinding();
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        StringBinding sb = b.createStringBinding("binding.title");
        assertNotNull(sb);
        assertEquals("B: Test title", sb.get());
        
        I18NFacade.getDefault().setActualLocale(Locale.GERMAN);
        sb = b.createStringBinding("binding.title");
        assertNotNull(sb);
        assertEquals("B: Test Titel", sb.get());
    }

    @Test(expected = NullPointerException.class)
    public void createStringBinding_String_ThrowsNullPointerException() {
        I18NBinding b = new DefaultI18NBinding();
        String key = null;
        b.createStringBinding(key);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createStringBinding_String_ThrowsIllegalArgumentException() {
        I18NBinding b = new DefaultI18NBinding();
        b.createStringBinding("");
    }

    @Test
    public void createStringBinding_String_ObjectArray() {
        I18NFacade.getDefault().setBaseBundleName(RESOURCE_BUNDLE);
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        I18NFacade.getDefault().setSupportedLocales(supportedLocales);
        
        I18NBinding b = new DefaultI18NBinding();
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        StringBinding sb = b.createStringBinding("binding.label.with.parameter", 2);
        assertNotNull(sb);
        assertEquals("B: Text with parameter: 2", sb.get());
        
        I18NFacade.getDefault().setActualLocale(Locale.GERMAN);
        sb = b.createStringBinding("binding.label.with.parameter", 5);
        assertNotNull(sb);
        assertEquals("B: Text mit Parameter: 5", sb.get());
    }

    @Test(expected = NullPointerException.class)
    public void createStringBinding_String_ObjectArr_StringThrowsNullPointerException() {
        I18NBinding b = new DefaultI18NBinding();
        b.createStringBinding(null, "hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createStringBinding_String_ObjectArr_StringThrowsIllegalArgumentException() {
        I18NBinding b = new DefaultI18NBinding();
        b.createStringBinding("", "hello");
    }

//    @Test(expected = NullPointerException.class)
//    public void createStringBinding_String_ObjectArr_ObjectArrThrowsNullPointerException() {
//        I18NBinding b = new DefaultI18NBinding();
//        String parameter = null;
//        b.createStringBinding("hello", parameter);
//    }

    @Test
    public void createStringBinding_Callable() {
        I18NFacade.getDefault().setBaseBundleName(RESOURCE_BUNDLE);
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        I18NFacade.getDefault().setSupportedLocales(supportedLocales);
        
        I18NBinding b = new DefaultI18NBinding();
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        StringBinding sb = b.createStringBinding(() -> I18NFacade.getDefault().getMessage("binding.label.with.parameter", 1));
        assertNotNull(sb);
        assertEquals("B: Text with parameter: 1", sb.get());
        
        I18NFacade.getDefault().setActualLocale(Locale.GERMAN);
        sb = b.createStringBinding(() -> I18NFacade.getDefault().getMessage("binding.label.with.parameter", 3));
        assertNotNull(sb);
        assertEquals("B: Text mit Parameter: 3", sb.get());
    }

    @Test(expected = NullPointerException.class)
    public void createStringBinding_Callable_ThrowsNullPointerException() {
        I18NBinding b = new DefaultI18NBinding();
        Callable<String> function = null;
        b.createStringBinding(function);
    }
    
}
