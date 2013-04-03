package net.morgz.app.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 *
 */
public interface StorageInterface {

    public boolean store(String storageArea, InputStream inputStream) throws StorageException, IOException;

    public void setCredentials(Map<String, String> credentials) throws InvalidCredentialsException;

    public void createConnection(String endpoint);

}
