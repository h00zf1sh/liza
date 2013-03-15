package net.morgz.app.observer;

import java.util.Date;

/**
 * Class representing content entry in directory
 */
public class DirectoryContent {

    private String name;

    private Date timeStamp;

    /**
     * Constructor
     *
     * @param name      Name of file
     * @param timeStamp File time stamp
     */
    public DirectoryContent(String name, Date timeStamp) {

        this.setName(name);

        this.setTimeStamp(timeStamp);

    }

    /**
     * Getter for name attribute
     *
     * @return String name
     */
    public String getName() {

        return name;

    }

    /**
     * Setter for the name
     *
     * @param name Name of file
     *
     * @return DirectoryContent this
     */
    public DirectoryContent setName(String name) {

        this.name = name;

        return this;

    }

    /**
     * Getter for the timestamp
     *
     * @return Date time stamp
     */
    public Date getTimeStamp() {

        return this.timeStamp;

    }

    /**
     * Setter for the time stamp
     *
     * @param timeStamp File time stamp
     *
     * @return DirectoryContent this
     */
    public DirectoryContent setTimeStamp(Date timeStamp) {

        this.timeStamp = timeStamp;

        return this;

    }
}
