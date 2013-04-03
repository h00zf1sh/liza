package net.morgz.app.storage.amazon;

import junit.framework.TestCase;
import net.morgz.app.storage.InvalidCredentialsException;
import net.morgz.app.storage.StorageException;
import net.morgz.app.storage.StorageInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class AmazonS3StorageTest extends TestCase {

    private StorageInterface storage;

    public void setUp() {

        this.storage = new AmazonS3Storage();

    }

    public void testCanStoreFileOK() {

        try {

            this.storage.createConnection("http://www.example.com");

            String storageArea = "test-bucket";

            InputStream is = new ByteArrayInputStream("badger".getBytes());

            boolean storedOK = this.storage.store(storageArea, is);

            this.assertTrue(storedOK);

        } catch (StorageException sEx) {

            this.fail("StorageException should not be thrown: " + sEx.toString());

        } catch (IOException ioEx) {

            this.fail("IOException should not be thrown: " + ioEx.toString());

        }

    }

    public void testCannotStoreFileOK() {

        //this.fail("not implemented yet");

    }

    public void testStorageExceptionThrownIfStorageFails() {

        //this.fail("not implemented yet");

    }

    /**
     *
     */
    public void testSetCredentialsMethodWithValidCredentials() {

        Map<String, String> credentials = new HashMap<String, String>();

        credentials.put("AccessKey","TestAccessKey");
        credentials.put("Secret", "TestSecret");

        try {

            this.storage.setCredentials(credentials);

        } catch (InvalidCredentialsException icEx) {

            this.fail("This test should not throw an exception: " + icEx.toString());

        }

    }

    public void testSetCredentialsMethodWithNoAccessKeyCredentials() {

        Map<String, String> credentials = new HashMap<String, String>();

        try {

            this.storage.setCredentials(credentials);

        } catch (InvalidCredentialsException icEx) {

            this.assertEquals("Missing [AccessKey] from Amazon S3 credentials", icEx.getMessage());

        }

    }

    public void testSetCredentialsMethodWithNoSecretCredentials() {

        Map<String, String> credentials = new HashMap<String, String>();

        credentials.put("AccessKey", "TestAccessKey");

        try {

            this.storage.setCredentials(credentials);

            this.fail("This should not pass as exception should be thrown");

        } catch (InvalidCredentialsException icEx) {

            this.assertEquals("Missing [Secret] from Amazon S3 credentials", icEx.getMessage());

        }

    }

}
