package net.morgz.app.observer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Watch a directory
 */
public class DirectoryWatcher {

    private final WatchService watcher;

    private final Map<WatchKey,Path> keys;


    public DirectoryWatcher() throws IOException {

        this.watcher = FileSystems.getDefault().newWatchService();

        this.keys = new HashMap<WatchKey,Path>();

    }

    /**
     * Register a directory with the WatchService
     */
    public void registerDirectory(Path directory) throws IOException {

        WatchKey key;

        key = directory.register(
            this.watcher,
            ENTRY_CREATE,
            ENTRY_DELETE,
            ENTRY_MODIFY
        );

        keys.put(key, directory);
    }
}
