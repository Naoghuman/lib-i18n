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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnitTests to test the facade {@link com.github.naoghuman.lib.i18n.core.I18NFacade}.
 * <p>
 * Only the static methode {@link com.github.naoghuman.lib.i18n.core.I18NFacade#getDefault()}
 * will be test here. All other methods from the interfaces are tested with the 
 * UnitTests in the internal package.
 *
 * @since   0.4.0-PRERELEASE
 * @version 0.4.0-PRERELEASE
 * @author  Naoghuman
 * @see     com.github.naoghuman.lib.i18n.core.I18NFacade
 */
public class I18NFacadeTest {
    
    public I18NFacadeTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getDefault() {
        assertNotNull(I18NFacade.getDefault());
    }
    
}
