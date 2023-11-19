package edu.hw6;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// тестов нет, нечего тестировать
public class Task6 {

    public static final int MAX_TCP = 1023;

    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    private static final Map<Integer, String> WELL_KNOWN_PORTS = Map.ofEntries(
        Map.entry(135, "EPMAP"),
        Map.entry(137, "NetBIOS Name Service"),
        Map.entry(138, "NetBIOS Datagram Service"),
        Map.entry(139, "NetBIOS Session Service"),
        Map.entry(445, "Microsoft-DS Active Directory"),
        Map.entry(843, "Adobe Flash"),
        Map.entry(1900, "Simple Service Discovery Protocol (SSDP)"),
        Map.entry(3702, "Dynamic Web Services Discovery"),
        Map.entry(5040, ""),
        Map.entry(5050, ""),
        Map.entry(5353, "Multicast DNS"),
        Map.entry(5355, "Link-Local Multicast Name Resolution (LLMNR)"),
        Map.entry(5939, ""),
        Map.entry(6463, ""),
        Map.entry(6942, ""),
        Map.entry(17500, "Dropbox"),
        Map.entry(17600, ""),
        Map.entry(27017, "MongoDB"),
        Map.entry(42420, "")
    );

    public static void test(String[] args) {
        scanPorts();
    }

    private static void scanPorts() {
        LOGGER.info("%-8s%-8s%-30s%n", "Протокол", "Порт", "Сервис");
        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                printPortInfo(port);

            } catch (IOException e) {
                // Порт занят
            }
        }
    }

    private static void printPortInfo(int port) {
        String protocol = getProtocolForPort(port);
        String serviceName = WELL_KNOWN_PORTS.get(port);

        LOGGER.info("%-8s%-8d%-30s%n", protocol, port, serviceName);
    }

    private static String getProtocolForPort(int port) {
        return (port <= MAX_TCP) ? "TCP" : (port <= MAX_PORT) ? "TCP/UDP" : "UDP";
    }
}

