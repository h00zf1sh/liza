package net.morgz.app;

import net.morgz.app.storage.InvalidCredentialsException;
import net.morgz.app.storage.StorageException;
import net.morgz.app.storage.StorageFactoryInterface;
import net.morgz.app.storage.StorageInterface;
import net.morgz.app.storage.amazon.AmazonS3StorageFactory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FileStore implements DirectoryChangeListener {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(FileStore.class);

    private StorageInterface storageEngine;

    public FileStore() {

        StorageFactoryInterface storageFactory = new AmazonS3StorageFactory();

        this.storageEngine = storageFactory.createStorage();

        Map<String, String> credentials = new HashMap<String, String>();

        credentials.put("AccessKey","AKIAIOFWIGAJ4ERWOSQA");
        credentials.put("Secret", "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");

        try {

            this.storageEngine.setCredentials(credentials);

        } catch (InvalidCredentialsException icEx) {

            LOG.error(icEx);

        };

        this.storageEngine.createConnection("http://s3-website-eu-west-1.amazonaws.com");

    }

    @Override
    public void directoryChanged(DirectoryChangeEvent event) {

        if(event.getEventType().name().equals("ENTRY_CREATE")) {

            try {

                File file = event.getFile().toFile();

                LOG.info("Sending " + file.getName());

                this.storageEngine.store("morgz-test", new FileInputStream(file));

            } catch (IOException ioEx) {

                LOG.error(ioEx.getMessage());

            } catch (StorageException sEx) {

                LOG.error(sEx.getMessage());

            }

            LOG.info("Add New File: " + event.getFile());

        }

    }

}
