package net.morgz.app;

import net.morgz.app.storage.StorageFactoryInterface;
import net.morgz.app.storage.StorageInterface;
import net.morgz.app.storage.amazon.AmazonS3StorageFactory;
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

    private DirectoryWatcher directoryWatcher;

    private Properties properties;

    private FileStore fileStore;


    /**
     * Setter for the directory watcher
     *
     * @param directoryWatcher Directory Watcher
     */
    void setDirectoryWatcher(DirectoryWatcher directoryWatcher) {

        this.directoryWatcher = directoryWatcher;

    }

    /**
     * Setter for the properties
     *
     * @param properties Properties Properties
     */
    void setProperties(Properties properties) {

        this.properties = properties;

    }

    void setFileStore(FileStore fileStore) {

        this.fileStore = fileStore;

    }

    /**
     * Main method
     *
     * @param args Command Line Arguments
     */
    public static void main( String[] args )
    {
        DOMConfigurator.configureAndWatch(LOG4_CONFIG_FILE, LOG4J_WATCH_INTERVAL);

        Properties properties = PropertiesPersistor.getProperties();

        try {

            App app = new App();

            app.setFileStore(new FileStore());

            app.setDirectoryWatcher(new DirectoryWatcher());

            app.setProperties(properties);

            app.start();

        } catch (IOException ioEx) {

            LOG.error(ioEx.toString());

        }

    }

    void start() throws IOException {

        String rootDirectory = this.properties.getProperty(PropertiesPersistor.DIRECTORY_TO_WATCH);

        Path dir = Paths.get(rootDirectory);

        this.directoryWatcher.register(dir);

        this.directoryWatcher.addDirectoryChangeListener(this.fileStore);

        this.directoryWatcher.watch();

    }

}
