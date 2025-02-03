package com.codegym.Service.App;

import com.codegym.Configuration.RedisConfig;
import com.codegym.Service.DataBase.DataBaseService;

/*
Esta clase es la responsable de iniciar y gestionar el ciclo de vida de la aplicación.
Inicializa los servicios necesarios utilizando 'AppInitializer' para configurar los componentes esenciales
de la aplicación, como los servicios de ciudad, país, Redis y pruebas de rendimiento.
Crea una instancia de 'AppLifecycleManager', que se encarga de iniciar la aplicación y garantizar que los recursos
sean gestionados adecuadamente.
El método 'startApp' se ejecuta para comenzar la aplicación, y en un bloque 'finally', se asegura de apagar la
aplicación de manera correcta mediante 'shutdown'.
*/

public class ApplicationRunner {
    public void run() {

        AppInitializer appInitializer = new AppInitializer();
        AppService appService = appInitializer.initializeApp();

        AppLifecycleManager lifecycleManager =
                new AppLifecycleManager(appService, new DataBaseService(), new RedisConfig());

        try {
            lifecycleManager.startApp();
        } finally {
            lifecycleManager.shutdown();
        }
    }
}

