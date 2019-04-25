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

import com.github.naoghuman.lib.fxml.core.FXMLView;
import com.github.naoghuman.lib.i18n.core.I18NResourceBundleBuilder;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @since   0.8.0
 * @version 0.8.0
 * @author  Naoghuman
 */
public final class DemoI18NStart extends Application {
    
    /**
     * 
     * @param   args 
     * @since   0.3.0-PRERELEASE
     * @version 0.3.0-PRERELEASE
     * @author  Naoghuman
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        super.init();
        
        LoggerFacade.getDefault().info(this.getClass(), "DemoI18NStart#init()"); // NOI18N
        
        I18NResourceBundleBuilder.configure()
                .baseBundleName("com.github.naoghuman.lib.i18n.demo_i18n") // NOI18N
                .supportedLocales(Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN, Locale.ITALIAN)
                .defaultLocale(Locale.ENGLISH)
                .actualLocale(Locale.ENGLISH)
                .build();
    }
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "DemoI18NStart#start(Stage)"); // NOI18N
    
        primaryStage.setTitle("Lib-I18N Demo: v0.8.0"); // NOI18N
        
        final FXMLView view = FXMLView.create(DemoI18NController.class);
        final Scene scene = new Scene(view.getRoot().orElse(new AnchorPane()), 960, 540);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
}
