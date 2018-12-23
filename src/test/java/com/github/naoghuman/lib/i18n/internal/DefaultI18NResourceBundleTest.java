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

import com.github.naoghuman.lib.i18n.core.I18NResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * UnitTests to test the {@code Interface} {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundle}
 * with its default implementation {@link com.github.naoghuman.lib.i18n.internal.DefaultI18NResourceBundle}.
 *
 * @since   0.2.0-PRERELEASE
 * @version 0.2.0-PRERELEASE
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NResourceBundle
 * @see     com.github.naoghuman.lib.i18n.internal.DefaultI18NResourceBundle
 */
public class DefaultI18NResourceBundleTest {
    
    private static final String RESOURCE_BUNDLE = "com.github.naoghuman.lib.i18n.internal.resourcebundle"; // NOI18N
    
    public DefaultI18NResourceBundleTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getBaseBundleName() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName(RESOURCE_BUNDLE);
        
        assertEquals(RESOURCE_BUNDLE, rb.getBaseBundleName());
    }

    @Test(expected = NullPointerException.class)
    public void getBaseBundleNameThrowsNullPointerException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.getBaseBundleName();
    }

    @Test
    public void setBaseBundleName() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName(RESOURCE_BUNDLE);
    }

    @Test(expected = NullPointerException.class)
    public void setBaseBundleNameThrowsNullPointerException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBaseBundleNameThrowsIllegalArgumentException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName("");
    }
    
    @Test(expected = MissingResourceException.class)
    public void getMessageThrowsMissingResourceException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName("com.github.naoghuman.lib.i18n.internal.not-existing-resourcebundle"); // NOI18N
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        rb.setSupportedLocales(supportedLocales);
        
        rb.setActualLocale(Locale.ENGLISH);
        
        rb.getMessage("resourcebundle.title");
    }

    @Test
    public void getMessage_String() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName(RESOURCE_BUNDLE);
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        rb.setSupportedLocales(supportedLocales);
        
        rb.setActualLocale(Locale.ENGLISH);
        assertEquals("RB: Test title", rb.getMessage("resourcebundle.title"));
        
        rb.setActualLocale(Locale.GERMAN);
        assertEquals("RB: Test Titel", rb.getMessage("resourcebundle.title"));
    }

    @Test(expected = NullPointerException.class)
    public void getMessage_String_ThrowsNullPointerException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.getMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMessage_String_ThrowsIllegalArgumentException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.getMessage("");
    }

    @Test
    public void getMessage_String_ObjectArr() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setBaseBundleName(RESOURCE_BUNDLE);
        
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        rb.setSupportedLocales(supportedLocales);
        
        rb.setActualLocale(Locale.ENGLISH);
        assertEquals("RB: Text with parameter: 2", rb.getMessage("resourcebundle.label.with.parameter", 2));
        
        rb.setActualLocale(Locale.GERMAN);
        assertEquals("RB: Text mit Parameter: 5", rb.getMessage("resourcebundle.label.with.parameter", 5));
    }

    @Test(expected = NullPointerException.class)
    public void getMessage_String_ObjectArr_StringThrowsNullPointerException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.getMessage(null, "hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMessage_String_ObjectArr_StringThrowsIllegalArgumentException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.getMessage("", "hello");
    }

    @Test(expected = NullPointerException.class)
    public void getMessage_String_ObjectArr_ObjectArrThrowsNullPointerException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        String parameter = null;
        rb.getMessage("key", parameter);
    }

    @Test
    public void getSupportedLocales() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        
        assertTrue(rb.getSupportedLocales().isEmpty());
    }

    @Test
    public void getSupportedLocalesEmpty() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        
        assertTrue(rb.getSupportedLocales().isEmpty());
    }

    @Test
    public void setSupportedLocales() {
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ENGLISH, Locale.GERMAN);
        
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setSupportedLocales(supportedLocales);
        
        assertEquals(2, rb.getSupportedLocales().size());
        assertEquals(Locale.ENGLISH, rb.getSupportedLocales().get(0));
        assertEquals(Locale.GERMAN, rb.getSupportedLocales().get(1));
    }

    @Test(expected = NullPointerException.class)
    public void setSupportedLocalesThrowsNullPointerExceptionWithArray() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        final Locale[] locales = null;
        rb.setSupportedLocales(locales);
    }

    @Test(expected = NullPointerException.class)
    public void setSupportedLocalesThrowsNullPointerExceptionWithObservableList() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        final ObservableList<Locale> locales = null;
        rb.setSupportedLocales(locales);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setSupportedLocalesThrowsIllegalArgumentException() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setSupportedLocales(FXCollections.observableArrayList());
    }

    @Test
    public void getDefaultLocaleIsEnglish() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        
        assertEquals(Locale.ENGLISH, rb.getDefaultLocale());
    }

    @Test
    public void setGetDefaultLocaleSetItalyButIsEnglish() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setDefaultLocale(Locale.ITALY);
        
        assertEquals(Locale.ENGLISH, rb.getDefaultLocale());
    }

    @Test
    public void setGetDefaultLocaleSetItalyAndItIsItaly() {
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ITALY, Locale.GERMAN);
        
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setSupportedLocales(supportedLocales);
        rb.setDefaultLocale(Locale.ITALY);
        
        assertEquals(Locale.ITALY, rb.getDefaultLocale());
    }

    @Test
    public void getActualLocaleIsEnglish() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        
        assertEquals(Locale.ENGLISH, rb.getActualLocale());
    }

    @Test
    public void setGetActualLocaleSetItalyButIsEnglish() {
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setActualLocale(Locale.ITALY);
        
        assertEquals(Locale.ENGLISH, rb.getActualLocale());
    }

    @Test
    public void setGetActualLocaleSetItalyAndItIsItaly() {
        ObservableList<Locale> supportedLocales = FXCollections.observableArrayList();
        supportedLocales.addAll(Locale.ITALY, Locale.GERMAN);
        
        I18NResourceBundle rb = new DefaultI18NResourceBundle();
        rb.setSupportedLocales(supportedLocales);
        rb.setActualLocale(Locale.ITALY);
        
        assertEquals(Locale.ITALY, rb.getActualLocale());
    }
    
}
