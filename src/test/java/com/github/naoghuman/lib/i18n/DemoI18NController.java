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
package com.github.naoghuman.lib.i18n;

import com.github.naoghuman.lib.fxml.core.FXMLController;
import com.github.naoghuman.lib.fxml.core.FXMLRegisterable;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @since   0.8.0
 * @version 0.8.0
 * @author  Naoghuman
 */
public final class DemoI18NController extends FXMLController implements FXMLRegisterable, Initializable {

    @FXML private Button bFrance;
    @FXML private Button bGermany;
    @FXML private Button bItaly;
    @FXML private Button bUnitedKingdom;
    @FXML private Text tFrom;
    @FXML private Text tHello;
    @FXML private Text tLand;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "DemoI18NController#initialize(URL, ResourceBundle)"); // NOI18N
    
    }

    @Override
    public void configure() {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NController#configure()"); // NOI18N
        // ?
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().info(this.getClass(), "DemoI18NController.register()"); // NOI18N
        
    }
    
}
