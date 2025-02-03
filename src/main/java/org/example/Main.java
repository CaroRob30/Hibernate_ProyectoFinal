package org.example;

import com.codegym.Service.App.ApplicationRunner;

/*
El 'Main' es el punto de entrada de la aplicación. En su método 'main', crea una instancia de 'ApplicationRunner'
y llama a su método 'run()', que inicia la configuración y ejecución de la aplicación.
Siento esta su única responsabilidad.
*/

public class Main {
    public static void main() {
        new ApplicationRunner().run();
    }
}

