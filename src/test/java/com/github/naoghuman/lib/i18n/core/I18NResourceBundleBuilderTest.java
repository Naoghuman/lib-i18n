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
                .supportedLocales(Locale.ITALIAN)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstStepThrowsIllegalArgumentException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("")
                .supportedLocales(Locale.ITALIAN)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepArrayThrowsNullPointerException() {
        final Locale[] locales = null;
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepArrayThrowsIllegalArgumentException() {
        final Locale[] locales = {};
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void secondStepObservableListThrowsNullPointerException() {
        final ObservableList<Locale> locales = null;
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void secondStepObservableListThrowsIllegalArgumentException() {
        final ObservableList<Locale> locales = FXCollections.observableArrayList();
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void thirdStepThrowsNullPointerException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(Locale.ITALIAN, Locale.JAPANESE)
                .defaultLocale(null)
                .actualLocale(Locale.ITALIAN)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void forthStepThrowsNullPointerException() {
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
                .supportedLocales(Locale.ITALIAN, Locale.JAPANESE)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(null)
                .build();
    }

    @Test
    public void lastStepWithSupportedLocalesAsArray() {
        String resourcbundle = "com.github.naoghuman.lib.i18n.internal.resourcebundle";
        I18NResourceBundleBuilder.configure()
                .baseBundleName(resourcbundle)
                .supportedLocales(Locale.ITALIAN, Locale.JAPANESE)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.JAPANESE)
                .build();
        
        assertEquals(resourcbundle,   I18NFacade.getDefault().getBaseBundleName());
        assertEquals(Locale.ITALIAN,  I18NFacade.getDefault().getDefaultLocale());
        assertEquals(Locale.JAPANESE, I18NFacade.getDefault().getActualLocale());
        assertEquals(2,               I18NFacade.getDefault().getSupportedLocales().size());
    }

    @Test
    public void lastStepWithSupportedLocalesAsObservableList() {
        String resourcbundle = "com.github.naoghuman.lib.i18n.internal.resourcebundle";
        final ObservableList<Locale> locales = FXCollections.observableArrayList();
        locales.addAll(Locale.ITALIAN, Locale.JAPANESE, Locale.FRENCH);
        I18NResourceBundleBuilder.configure()
                .baseBundleName(resourcbundle)
                .supportedLocales(locales)
                .defaultLocale(Locale.ITALIAN)
                .actualLocale(Locale.JAPANESE)
                .build();
        
        assertEquals(resourcbundle,  I18NFacade.getDefault().getBaseBundleName());
        assertEquals(Locale.ITALIAN, I18NFacade.getDefault().getDefaultLocale());
        assertEquals(Locale.JAPANESE,I18NFacade.getDefault().getActualLocale());
        assertEquals(3,              I18NFacade.getDefault().getSupportedLocales().size());
    }
    
}
