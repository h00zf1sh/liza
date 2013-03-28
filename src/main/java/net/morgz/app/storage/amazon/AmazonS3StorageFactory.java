package net.morgz.app.storage.amazon;

import net.morgz.app.storage.StorageFactoryInterface;
import net.morgz.app.storage.StorageInterface;

/**
 *
 */
public class AmazonS3StorageFactory implements StorageFactoryInterface {

    @Override
    public StorageInterface createStorage() {

        return new AmazonS3Storage();

    }

}
