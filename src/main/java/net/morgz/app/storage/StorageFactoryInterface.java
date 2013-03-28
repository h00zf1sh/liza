package net.morgz.app.storage;

/**
 * Storage factory interface
 */
public interface StorageFactoryInterface {

    /**
     * Factory method to generate a storage class
     *
     * @return StorageInterface interface to storage class
     */
    public StorageInterface createStorage();

}
