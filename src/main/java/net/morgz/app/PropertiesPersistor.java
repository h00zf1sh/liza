package net.morgz.app;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

public class PropertiesPersistor
{
	/** Log4j **/
	private static final Logger LOG = Logger.getLogger(PropertiesPersistor.class);

    public static final String DIRECTORY_TO_WATCH = "directoryToWatch";

    /**
     * Get properties from file
     *
     * @param filename Filename
     *
     * @return Properties
     */
	public static LizaProperties getProperties(String filename)
	{
		LizaProperties properties = new LizaProperties();

		try
		{
			FileInputStream in = new FileInputStream(filename);

			properties.load(in);

			in.close();
		}
		catch (IOException ioEx)
		{
			LOG.error(ioEx);
		}

		return properties;
	}
}
