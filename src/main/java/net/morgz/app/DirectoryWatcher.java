package net.morgz.app;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

/**
 *
 */
class DirectoryWatcher {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(DirectoryWatcher.class);

    private final WatchService watcherService;

    private final Map<WatchKey,Path> keys;

    private List<DirectoryChangeListener> listeners;


    public DirectoryWatcher() throws IOException {

        this.watcherService = FileSystems.getDefault().newWatchService();

        this.keys = new HashMap<WatchKey,Path>();

        this.listeners = new ArrayList<DirectoryChangeListener>();
    }

    /**
     * Register the given directory with the WatchService
     */
    private void registerDirectoryWithWatchService(Path directory) throws IOException {
        LOG.debug("Registering " + directory.toString() + " with watcher service");

        WatchKey key = directory.register(
            this.watcherService,
            ENTRY_CREATE,
            ENTRY_MODIFY,
            ENTRY_DELETE
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

    public void watch() {

        while(true) {

            try {
                WatchKey key = this.watcherService.take();

                Path directory = this.keys.get(key);

                this.processEvents(key, directory);

            } catch (InterruptedException iEx) {
                LOG.error(iEx.toString());
            }
        }
    }

    private void processEvents(WatchKey key, Path directory) {

        for (WatchEvent<?>event: key.pollEvents()) {

            WatchEvent<Path> ev = (WatchEvent)event;

            Path name = ev.context();

            Path child = directory.resolve(name);

            DirectoryChangeEvent directoryChangeEvent = this.createEvent(event, child);

            this.notifyListeners(directoryChangeEvent);

            LOG.debug(event.kind().name() + ": " + child);

            boolean valid = key.reset();

            if (!valid) {
                this.keys.remove(key);
            }
        }
    }

    private void notifyListeners(DirectoryChangeEvent event) {

        for (DirectoryChangeListener listener : this.listeners) {

            listener.directoryChanged(event);

        }

    }

    public void addDirectoryChangeListener(DirectoryChangeListener listener) {

        this.listeners.add(listener);

    }

    private DirectoryChangeEvent createEvent(WatchEvent watchEvent, Path file) {

        DirectoryChangeEvent event = new DirectoryChangeEvent();

        event.setEventType(watchEvent.kind());

        event.setFile(file);

        return event;

    }

}
