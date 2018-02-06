package org.fbcmd4j;

import facebook4j.Facebook;
import facebook4j.FacebookException;

public class Utils {

	public static void publicaEstado(Facebook facebook, String estado) throws FacebookException {
		// Publica estado en el WALL del usuario
		facebook.postStatusMessage(estado);
	}
}
