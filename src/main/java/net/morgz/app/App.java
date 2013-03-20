package net.morgz.app;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Log4j
     **/
    private static final Logger LOG = Logger.getLogger(App.class);

    private static final String LOG4_CONFIG_FILE = "log4jconfig.xml";

    private static final int LOG4J_WATCH_INTERVAL = 1000;

    private static final String PROPERTIES_FILE = "liza.properties";

    /**
     * Main method
     *
     * @param args Command Line Arguments
     */
    public static void main( String[] args )
    {
        DOMConfigurator.configureAndWatch(LOG4_CONFIG_FILE, LOG4J_WATCH_INTERVAL);

        Properties properties = PropertiesPersistor.getProperties(PROPERTIES_FILE);

        try {
            new App().start(properties);
        } catch (IOException ioEx) {
            LOG.error(ioEx.toString());
        }
    }

    public void start(Properties properties) throws IOException {

        String rootDirectory = properties.getProperty(PropertiesPersistor.DIRECTORY_TO_WATCH);

        Path dir = Paths.get(rootDirectory);

        DirectoryWatcher directoryWatcher = new DirectoryWatcher();

        directoryWatcher.register(dir);
    }
}
