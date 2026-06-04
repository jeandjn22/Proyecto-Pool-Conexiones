package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {
    private static final String LOG_FILE = "simulador_conexiones.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 'synchronized' evita problemas si varios hilos escriben al mismo tiempo
    public static synchronized void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logLine = "[" + timestamp + "] " + message + System.lineSeparator();

        // Muestra el mensaje en la consola de Visual Studio Code
        System.out.print(logLine);

        try {
            // Escribe en la raíz. Si el archivo no existe lo crea, si existe añade el texto al final
            Files.writeString(
                Paths.get(LOG_FILE), 
                logLine, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Error crítico al escribir el log: " + e.getMessage());
        }
    }
}
