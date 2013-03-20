package net.morgz.app.observer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class DirectoryWatcherTest {

    public void testRegisterMethod() {

        DirectoryWatcher directoryWatcher = null;

        try {

            directoryWatcher = new DirectoryWatcher();

            Path path = Paths.get("c://");

            directoryWatcher.registerDirectory(path);

        } catch (IOException ioEx) {

            System.out.println(ioEx.getMessage());
        }

    }
}
