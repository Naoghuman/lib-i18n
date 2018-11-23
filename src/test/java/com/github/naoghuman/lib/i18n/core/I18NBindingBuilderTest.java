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
import java.util.Optional;
import javafx.beans.binding.StringBinding;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the fluent builder {@link com.github.naoghuman.lib.i18n.core.I18NBindingBuilder}.
 * 
 * @since  0.6.0
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NBindingBuilder
 */
public class I18NBindingBuilderTest {
    
    public I18NBindingBuilderTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected = NullPointerException.class)
    public void firstStepCallableThrowsNullPointerException() {
        I18NBindingBuilder.bind()
                .callable(null)
                .build();
    }
    
    @Test(expected = NullPointerException.class)
    public void firstStepKeyThrowsNullPointerException() {
        I18NBindingBuilder.bind()
                .key(null)
                .build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void firstStepKeyThrowsIllegalArgumentException() {
        I18NBindingBuilder.bind()
                .key("")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepArgumentsThrowsNullPointerException() {
        final Object[] objects = null;
        I18NBindingBuilder.bind()
                .key("imaginary.key")
                .arguments(objects)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepArgumentsThrowsIllegalArgumentException() {
        final Object[] objects = {};
        I18NBindingBuilder.bind()
                .key("imaginary.key")
                .arguments(objects)
                .build();
    }
    
    @Test
    public void lastStepCallable() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.GERMAN)
                .build();
        
        Optional<StringBinding> result = I18NBindingBuilder.bind()
                .callable(() -> I18NMessageBuilder.message()
                        .key("resourcebundle.title")
                        .build()
                )
                .build();
        assertTrue(result.isPresent());
        assertEquals("RB: Test Titel", result.get().get());
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        assertEquals("RB: Test title", result.get().get());
    }
    
    @Test
    public void lastStepKeyWithoutArguments() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.GERMAN)
                .build();
        
        Optional<StringBinding> result = I18NBindingBuilder.bind()
                .key("resourcebundle.title")
                .build();
        assertTrue(result.isPresent());
        assertEquals("RB: Test Titel", result.get().get());
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        assertEquals("RB: Test title", result.get().get());
    }
    
    @Test
    public void lastStepKeyWithArguments() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.GERMAN)
                .build();
        
        Optional<StringBinding> result = I18NBindingBuilder.bind()
                .key("resourcebundle.label.with.parameter")
                .arguments(123)
                .build();
        assertTrue(result.isPresent());
        assertEquals("RB: Text mit Parameter: 123", result.get().get());
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
        assertEquals("RB: Text with parameter: 123", result.get().get());
    }
    
}
