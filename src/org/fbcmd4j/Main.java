package org.fbcmd4j;

import java.net.MalformedURLException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final Facebook facebook = new FacebookFactory().getInstance();
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "facebook4j.properties";
	private static final String APP_VERSION = "v1.0";


	public static void main(String[] args) {
		logger.info("Iniciando aplicación.");

		int seleccion;
		try (Scanner scanner = new Scanner(System.in)){
			while(true){
				// Inicio Menu
				System.out.format("Cliente de Facebook4j %s\n\n", APP_VERSION);
				System.out.println("Opciones: ");
				System.out.println("(1) Obtener News Feed");
				System.out.println("(2) Obtener Wall");
				System.out.println("(3) Publicar estado en Wall");
				System.out.println("(4) Publicar LINK en Wall");
				System.out.println("(5) Salir");
				System.out.println("\nPor favor ingrese una opción: ");
				// Fin de Menu
				try {
					seleccion = scanner.nextInt();
					scanner.nextLine();

					switch(seleccion){
						case 1: 
							logger.info("Opción seleccionada '(1) Obtener News Feed'");
							ResponseList<Post> feed = Utils.obtenerWall(facebook);
							
							for(Post post : feed) {
								System.out.println(post.getMessage());
							}
							
							break;
						case 2:
							logger.info("Opción seleccionada '(2) Obtener Wall'");
							ResponseList<Post> posts = Utils.obtenerPost(facebook);
							
							for(Post post : posts) {
								System.out.println(post.getMessage());
							}
							
							break;
						case 3:
							logger.info("Opción seleccionada '(3) Publicar Estado'");
							System.out.println("Escribe el estado a publicar: ");
							String estado = scanner.nextLine();
							Utils.publicaEstado(facebook, estado);
							System.out.println("Se publicó estado [" + estado + "].");
							break;
						case 4:
							logger.info("Opción seleccionada '(3) Publicar LINK'");
							System.out.println("Escribe el LINK a publicar: ");
							String link = scanner.nextLine();
							System.out.println("Escribe la descripción del LINK: ");
							String descripcion = scanner.nextLine();
							Utils.publicaLink(facebook, link, descripcion);
							logger.info("Se publicó el link '" + link + "'.");
							break;
						case 5:
							System.exit(0);
						default:
							logger.error("Opción inválida");
							break;
					}
				} catch (InputMismatchException ex){
					System.out.println("Ocurrió un errror, favor de revisar log.");
					logger.error("Opción inválida. %s. \n", ex.getClass());
					scanner.next();
				} catch (FacebookException ex){
					System.out.println("Ocurrió un errror, favor de revisar log.");
					logger.error(ex.getErrorMessage());
					scanner.next();
				} catch( MalformedURLException ex) {
					System.out.println("Ocurrió un error al tratar de publicar el LINK, favor de revisar log.");
					logger.error(ex.getMessage());
				} catch (Exception ex){
					System.out.println("Ocurrió un errror, favor de revisar log.");
					logger.error(ex);
					scanner.next();
				} 
			}
		} catch (Exception ex){
			logger.error(ex);
		}
	}

}
