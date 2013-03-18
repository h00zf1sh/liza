package net.morgz.app;

import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class DirectoryWatcherTest extends TestCase {

    public void testRegister() {
        try {
            DirectoryWatcher directoryWatcher = new DirectoryWatcher();

            Path dir = Paths.get(".");

            directoryWatcher.register(dir);
        } catch (IOException ioEx) {
            fail("Test should pass : " + ioEx.getMessage());
        }
    }
}
