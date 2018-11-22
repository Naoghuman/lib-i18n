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
package com.github.naoghuman.lib.i18n.core;

import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the fluent builder {@link com.github.naoghuman.lib.i18n.core.I18NMessageBuilder}.
 * 
 * @since  0.6.0
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NMessageBuilder
 */
public class I18NMessageBuilderTest {
    
    private static final String RESOURCE_BUNDLE = "com.github.naoghuman.lib.i18n.internal.resourcebundle"; // NOI18N
    
    public I18NMessageBuilderTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = NullPointerException.class)
    public void firstStepThrowsNullPointerException() {
//        I18NResourceBundleBuilder.configure()
//                .baseBundleName(null)
//                .supportedLocales(Locale.ITALY)
//                .defaultLocale(Locale.ITALY)
//                .actualLocale(Locale.ITALY)
//                .build();
        I18NMessageBuilder.message()
                .key(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstStepThrowsIllegalArgumentException() {
        I18NMessageBuilder.message()
                .key("")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepArrayThrowsNullPointerException() {
        final Object[] objects = null;
        I18NMessageBuilder.message()
                .key("imaginary.key")
                .arguments(objects)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepArrayThrowsIllegalArgumentException() {
        final Object[] objects = {};
        I18NMessageBuilder.message()
                .key("imaginary.key")
                .arguments(objects)
                .build();
    }
    
    @Test
    public void lastStepWithoutArguments() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.GERMAN)
                .build();
        
        String result = I18NMessageBuilder.message()
                .key("resourcebundle.title")
                .build();
        assertEquals("RB: Test Titel", result);
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        result = I18NMessageBuilder.message()
                .key("resourcebundle.title")
                .build();
        assertEquals("RB: Test title", result);
    }
    
    @Test
    public void lastStepWithArguments() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.GERMAN)
                .build();
        
        String result = I18NMessageBuilder.message()
                .key("resourcebundle.label.with.parameter")
                .arguments(2)
                .build();
        assertEquals("RB: Text mit Parameter: 2", result);
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        result = I18NMessageBuilder.message()
                .key("resourcebundle.label.with.parameter")
                .arguments(123)
                .build();
        assertEquals("RB: Text with parameter: 123", result);
    }
    
}
