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
import com.github.naoghuman.lib.i18n.core.I18NBindingBuilder;
import com.github.naoghuman.lib.i18n.core.I18NFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
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
public final class DemoI18NController extends FXMLController implements Initializable {

    @FXML private Button bFrench;
    @FXML private Button bGerman;
    @FXML private Button bItalian;
    @FXML private Button bEnglish;
    @FXML private Text tFrom;
    @FXML private Text tHello;
    @FXML private Text tLand;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "DemoI18NController#initialize(URL, ResourceBundle)"); // NOI18N
    
        // Buttons
        this.bind("demo.i18n.language.french",  bFrench.textProperty()); // NOI18N
        this.bind("demo.i18n.language.german",  bGerman.textProperty()); // NOI18N
        this.bind("demo.i18n.language.italian", bItalian.textProperty()); // NOI18N
        this.bind("demo.i18n.language.english", bEnglish.textProperty()); // NOI18N
        
        // Text
        this.bind("demo.i18n.from",      tFrom.textProperty()); // NOI18N
        this.bind("demo.i18n.greetings", tHello.textProperty()); // NOI18N
        this.bind("demo.i18n.land",      tLand.textProperty()); // NOI18N
    }
    
    private void bind(final String key, final StringProperty stringProperty) {
        LoggerFacade.getDefault().debug(this.getClass(), String.format(
                "DemoI18NController#bind(String='%s', StringProperty)", key)); // NOI18N
        
        final Optional<StringBinding> optStringBinding = I18NBindingBuilder.bind().key(key).build();
        optStringBinding.ifPresent(stringBinding -> {
            stringProperty.bind(stringBinding);
        });
    }
    
    public void onActionSwitchToLanguageFrench() {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NController#onActionSwitchToLanguageFrench()"); // NOI18N
        
        I18NFacade.getDefault().setActualLocale(Locale.FRENCH);
    }
    
    public void onActionSwitchToLanguageGerman() {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NController#onActionSwitchToLanguageGerman()"); // NOI18N
        
        I18NFacade.getDefault().setActualLocale(Locale.GERMAN);
    }
    
    public void onActionSwitchToLanguageItalian() {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NController#onActionSwitchToLanguageItalian()"); // NOI18N
        
        I18NFacade.getDefault().setActualLocale(Locale.ITALIAN);
    }
    
    public void onActionSwitchToLanguageEnglish() {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NController#onActionSwitchToLanguageEnglish()"); // NOI18N
        
        I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    }
    
}
