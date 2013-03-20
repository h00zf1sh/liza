package net.morgz.app;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 *
 */
public class DirectoryWatcher {

    private final WatchService watcherService;

    private final Map<WatchKey,Path> keys;

    public DirectoryWatcher() throws IOException {
        this.watcherService = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
    }

    /**
     * Register the given directory with the WatchService
     */
    private void registerDirectoryWithWatchService(Path directory) throws IOException {
        WatchKey key = directory.register(
            this.watcherService,
            ENTRY_CREATE,
            ENTRY_DELETE,
            ENTRY_MODIFY
        );

        keys.put(key, directory);
    }

    /**
     * Register a directory structure with the watch service
     *
     * @param startDirectory Start Directory
     *
     * @throws IOException
     */
    public void register(final Path startDirectory) throws IOException {

        // Register directory and sub-directories
        Files.walkFileTree(startDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectoryWithWatchService(dir);

                return FileVisitResult.CONTINUE;
            }
        });
    }
}
