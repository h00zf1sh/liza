package net.morgz.app.storage.amazon;

import junit.framework.TestCase;
import net.morgz.app.storage.StorageFactoryInterface;
import net.morgz.app.storage.StorageInterface;

/**
 *
 */
public class AmazonS3StorageFactoryTest extends TestCase {

    private StorageFactoryInterface storageFactory;

    public void setUp() {

        this.storageFactory = new AmazonS3StorageFactory();

    }

    public void testCreateStorage() {

        StorageInterface storage = this.storageFactory.createStorage();

        this.assertEquals(storage.getClass(), AmazonS3Storage.class);

    }
}
