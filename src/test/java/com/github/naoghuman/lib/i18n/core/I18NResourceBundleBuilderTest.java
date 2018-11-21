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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the fluent builder {@link com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder}.
 * 
 * @since  0.6.0
 * @author Naoghuman
 * @see    com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder
 */
public class I18NResourceBundleBuilderTest {
    
    private static final String RESOURCE_BUNDLE = "com.github.naoghuman.lib.i18n.internal.resourcebundle"; // NOI18N
    
    public I18NResourceBundleBuilderTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = NullPointerException.class)
    public void firstStepThrowsNullPointerException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(null)
                .supportedLocales(Locale.ITALY)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstStepThrowsIllegalArgumentException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("")
                .supportedLocales(Locale.ITALY)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepArrayThrowsNullPointerException() {
        final Locale[] locales = null;
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepArrayThrowsIllegalArgumentException() {
        final Locale[] locales = {};
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepObservableListThrowsNullPointerException() {
        final ObservableList<Locale> locales = null;
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepObservableListThrowsIllegalArgumentException() {
        final ObservableList<Locale> locales = FXCollections.observableArrayList();
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void thirdStepThrowsNullPointerException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(Locale.ITALY, Locale.CANADA)
                .defaultLocale(null)
                .actualLocale(Locale.ITALY)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void forthStepThrowsNullPointerException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(Locale.ITALY, Locale.CANADA)
                .defaultLocale(Locale.ITALY)
                .actualLocale(null)
                .build();
    }

    @Test
    public void lastSTep() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName(RESOURCE_BUNDLE)
                .supportedLocales(Locale.ITALY, Locale.CANADA)
                .defaultLocale(Locale.ITALY)
                .actualLocale(Locale.CANADA)
                .build();
        
        assertEquals(RESOURCE_BUNDLE, I18NFacade.getDefault().getBaseBundleName());
        assertEquals(Locale.ITALY,    I18NFacade.getDefault().getDefaultLocale());
        assertEquals(Locale.CANADA,   I18NFacade.getDefault().getActualLocale());
        assertEquals(2,               I18NFacade.getDefault().getSupportedLocales().size());
    }
    
}
