package net.morgz.app.storage;

import java.util.Map;

/**
 *
 */
public interface StorageInterface {

    public boolean store() throws StorageException;

    public void setCredentials(Map<String, String> credentials) throws InvalidCredentialsException;

}
