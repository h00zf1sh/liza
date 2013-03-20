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

    private DirectoryWatcher directoryWatcher;


    /**
     * Setter for the directory watcher
     *
     * @param directoryWatcher Directory Watcher
     */
    public void setDirectoryWatcher(DirectoryWatcher directoryWatcher) {

        this.directoryWatcher = directoryWatcher;

    }

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
            App app = new App();

            app.setDirectoryWatcher(new DirectoryWatcher());

            app.start(properties);
        } catch (IOException ioEx) {
            LOG.error(ioEx.toString());
        }
    }

    public void start(Properties properties) throws IOException {

        String rootDirectory = properties.getProperty(PropertiesPersistor.DIRECTORY_TO_WATCH);

        Path dir = Paths.get(rootDirectory);

        this.directoryWatcher.register(dir);

        this.intializeDirectoryWatcherListeners();

        this.directoryWatcher.watch();
    }

    private void intializeDirectoryWatcherListeners() {


    }
}
