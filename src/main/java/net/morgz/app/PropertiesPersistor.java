package net.morgz.app;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

class PropertiesPersistor
{
	/** Log4j **/
	private static final Logger LOG = Logger.getLogger(PropertiesPersistor.class);

    public static final String DIRECTORY_TO_WATCH = "directoryToWatch";

    private static final String PROPERTIES_FILE = "liza.properties";

    /**
     * Get properties from file
     *
     * @return Properties
     */
	public static LizaProperties getProperties()
	{
		LizaProperties properties = new LizaProperties();

		try
		{
			FileInputStream in = new FileInputStream(PROPERTIES_FILE);

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
