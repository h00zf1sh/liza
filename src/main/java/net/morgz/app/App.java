package net.morgz.app;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void main( String[] args )
    {
        try {
            new App().start();
        } catch (IOException ioEx) {
            LOG.error(ioEx.toString());
        }
    }

    public void start() throws IOException
    {
        Path dir = Paths.get(".");

        DirectoryWatcher directoryWatcher = new DirectoryWatcher();

        directoryWatcher.register(dir);
    }
}
