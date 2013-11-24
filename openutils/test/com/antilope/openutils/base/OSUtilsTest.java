package com.antilope.openutils.base;

import java.awt.Toolkit;

/**
 * 
 * @author         :    xushiheng
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public class OSUtilsTest {
	
	public static boolean runModeBaseBS = false;

	private static  boolean osIsMacOsX;
	private static  boolean osIsWindows;
	private static  boolean osIsWindowsXP;
	private static  boolean osIsWindows2003;
	private static  boolean osIsLinux;

	public static void initOS() {
		
		String os = System.getProperty("os.name").toLowerCase();
		osIsMacOsX = "mac os x".equals(os);
		osIsWindows = os.indexOf("windows") != -1;
		osIsWindowsXP = "windows xp".equals(os);
		osIsWindows2003 = "windows 2003".equals(os);
		osIsLinux = "linux".equals(os);
	}

	/**
	 * @return true if this VM is running on Mac OS X
	 */
	public static boolean isMacOSX() {
		return osIsMacOsX;
	}

	/**
	 * @return true if this VM is running on Windows
	 */
	public static boolean isWindows() {
		return osIsWindows;
	}

	/**
	 * @return true if this VM is running on Windows XP
	 */
	public static boolean isWindowsXP() {
		return osIsWindowsXP;
	}

	/**
	 * @return true if this VM is running on Windows 2003
	 */
	public static boolean isWindows2003() {
		return osIsWindows2003;
	}

	/**
	 * @return true if this VM is running on Linux
	 */
	public static boolean isLinux() {
		return osIsLinux;
	}

	/**
	 * @return true if the VM is running Windows and the Java application is
	 *         rendered using XP Visual Styles.
	 */
	public static boolean isUsingWindowsVisualStyles() {
		if (!isWindows()) {
			return false;
		}

		boolean xpthemeActive = Boolean.TRUE.equals(Toolkit.getDefaultToolkit()
				.getDesktopProperty("win.xpstyle.themeActive"));
		if (!xpthemeActive) {
			return false;
		} else {
			try {
				return System.getProperty("swing.noxp") != null;
			} catch (RuntimeException e) {
				return true;
			}
		}
	}

}