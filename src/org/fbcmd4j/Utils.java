package org.fbcmd4j;

import java.net.MalformedURLException;
import java.net.URL;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.PostUpdate;

public class Utils {

	public static void publicaEstado(Facebook facebook, String estado) throws FacebookException {
		// Publica estado en el WALL del usuario
		facebook.postStatusMessage(estado);
	}
	
	public static void publicaLink(Facebook facebook, String link, String descripcion) throws MalformedURLException, FacebookException {
		PostUpdate post = new PostUpdate(new URL(link))
				.description(descripcion);
		facebook.postFeed(post);
	}
}
