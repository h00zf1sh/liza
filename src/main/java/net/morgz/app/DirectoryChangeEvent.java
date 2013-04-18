package net.morgz.app;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

/**
 *
 */
public class DirectoryChangeEvent {

    private Kind eventType;

    private Path file;

    public Kind getEventType() {

        return this.eventType;

    }

    public Path getFile() {

        return this.file;

    }

    public void setEventType(Kind eventType) {

        this.eventType = eventType;

    }

    public void setFile(Path file) {

        this.file = file;


    }
}
