package net.morgz.app.observer;

import java.io.IOException;

/**
 *
 */
public class DirectoryWatcherTest {

    public void testRegisterMethod() {

        DirectoryWatcher directoryWatcher = null;

        try {

            directoryWatcher = new DirectoryWatcher();

        } catch (IOException ioEx) {

            System.out.println(ioEx.getMessage());
        }

        directoryWatcher.registerDirectory();

    }
}
