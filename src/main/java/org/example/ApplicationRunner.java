package org.example;

import com.codegym.Configuration.RedisConfig;
import com.codegym.Service.App.AppInitializer;
import com.codegym.Service.App.AppLifecycleManager;
import com.codegym.Service.App.AppService;
import com.codegym.Service.DataBase.DataBaseService;

public class ApplicationRunner {
    public void run() {

        AppInitializer appInitializer = new AppInitializer();
        AppService appService = appInitializer.initializeApp();

        AppLifecycleManager lifecycleManager = new AppLifecycleManager(appService, new DataBaseService(), new RedisConfig());

        try {
            lifecycleManager.startApp();
        } finally {
            lifecycleManager.shutdown();
        }
    }
}

