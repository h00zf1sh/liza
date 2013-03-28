package net.morgz.app.storage.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import net.morgz.app.storage.InvalidCredentialsException;
import net.morgz.app.storage.StorageException;
import net.morgz.app.storage.StorageInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AmazonS3Storage implements StorageInterface {

    private static final List<String> REQUIRED_CREDENTIALS = new ArrayList<String>() {{
        add("AccessKey");
        add("Secret");
    }};

    private AmazonS3 connection;

    private AWSCredentials credentials;


    public AmazonS3Storage() {

    }

    @Override
    public void setCredentials(Map<String, String> credentials) throws InvalidCredentialsException {

        this.validateCredentials(REQUIRED_CREDENTIALS, credentials);

    }

    @Override
    public boolean store() throws StorageException {

        return true;

    }

    /**
     * Validate credentials against a list of required settings
     *
     * @param requiredKeys Required keys list
     * @param credentials Credentials
     *
     * @throws InvalidCredentialsException
     */
    private void validateCredentials(List<String> requiredKeys, Map<String, String> credentials) throws InvalidCredentialsException {

        for (String requiredKey: requiredKeys) {

            if (!credentials.containsKey(requiredKey)) {

                throw new InvalidCredentialsException("Missing [" + requiredKey + "] from Amazon S3 credentials");

            }
        }
    }

}
