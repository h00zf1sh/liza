package net.morgz.app;

import java.util.Properties;

public class LizaProperties extends Properties {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Properties#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String a_Key)
	{
		String val = super.getProperty(a_Key);

		if (val == null)
		{
			return "";
		}
		else
		{
			return val;
		}
	}

}
