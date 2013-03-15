package net.morgz.app.observer;

import junit.framework.TestCase;

import java.util.Date;

/**
 *
 */
public class DirectoryContentTest extends TestCase {

    public void testDirectoryContentConstructor() {

        String name = "FileName1.jpg";

        Date timeStamp = new Date();

        DirectoryContent directoryContent = new DirectoryContent(name, timeStamp);

        this.assertEquals(directoryContent.getName(), "FileName1.jpg");

        this.assertEquals(directoryContent.getTimeStamp(), timeStamp);

    }
}
