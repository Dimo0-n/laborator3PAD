package labs.partea1;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;  // 🔥 important!

import java.net.URI;

public class DWServer {

    public static final String BASE_URI = "http://0.0.0.0:%d/";

    public static void main(String[] args) throws Exception {

        String portEnv = System.getenv("PORT");
        int port = (portEnv != null && !portEnv.isBlank())
                ? Integer.parseInt(portEnv)
                : (args.length > 0 ? Integer.parseInt(args[0]) : 8081);

        // 🔥 Configurare finală corectă
        ResourceConfig rc = new ResourceConfig()
                .packages("labs.partea1.controllers", "labs.partea1.model")
                .register(JacksonFeature.class);  // 🔥 OBLIGATORIU pentru JSON

        String uri = String.format(BASE_URI, port);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(uri), rc
        );

        System.out.println("DW running at: " + uri);
        System.out.println("Available endpoints:");
        System.out.println("   GET     /employees");
        System.out.println("   GET     /employees/{id}");
        System.out.println("   PUT     /employees/{id}");
        System.out.println("   POST    /employees");
        System.out.println("----------------------------------");

        Thread.currentThread().join();
    }
}
