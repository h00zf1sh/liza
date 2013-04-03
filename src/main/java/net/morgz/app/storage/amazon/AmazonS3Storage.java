package net.morgz.app.storage.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import net.morgz.app.storage.InvalidCredentialsException;
import net.morgz.app.storage.StorageException;
import net.morgz.app.storage.StorageInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AmazonS3Storage implements StorageInterface {

    private static final String ACCESS_KEY_PARAM = "AccessKey";

    private static final String SECRET_PARAM = "Secret";

    private static final List<String> REQUIRED_CREDENTIALS = new ArrayList<String>() {{
        add(ACCESS_KEY_PARAM);
        add(SECRET_PARAM);
    }};

    private AmazonS3 connection;

    private AWSCredentials credentials;


    @Override
    public void setCredentials(Map<String, String> credentials) throws InvalidCredentialsException {

        this.validateCredentials(REQUIRED_CREDENTIALS, credentials);

        String accessKey = credentials.get(ACCESS_KEY_PARAM);

        String secret = credentials.get(SECRET_PARAM);

        this.credentials = new BasicAWSCredentials(accessKey, secret);

    }

    @Override
    public void createConnection(String endpoint) {

        this.connection = new AmazonS3Client(this.credentials);

        this.connection.setEndpoint(endpoint);

    }

    @Override
    public boolean store(String storageArea, InputStream inputStream) throws StorageException, IOException {

        Bucket bucket = this.connection.createBucket(storageArea);

        PutObjectResult putObjectResult = this.connection.putObject(
            bucket.getName(),
            "todo",
            inputStream,
            new ObjectMetadata()
        );

        if(putObjectResult.getVersionId().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Validate credentials against a list of required settings
     *
     * @param requiredKeys Required keys list
     * @param credentials Credentials
     *
     * @throws InvalidCredentialsException
     */
    private void validateCredentials(List<String> requiredKeys, Map<String, String> credentials)
        throws InvalidCredentialsException {

        for (String requiredKey: requiredKeys) {

            if (!credentials.containsKey(requiredKey)) {

                throw new InvalidCredentialsException("Missing [" + requiredKey + "] from Amazon S3 credentials");

            }
        }
    }

}
