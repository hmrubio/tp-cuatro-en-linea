package juego;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aplicaci�n del juego Cuatro en L��nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en L��nea";
	
	private GridPane grilla;
	
	private TextField campoNombreJugadorRojo;
	private TextField campoNombreJugadorAmarillo;

	private TextField campoColumnas;
	private TextField campoFilas;

	private Button botonIniciar;

	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);
		
		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFont(new Font(16));
		
		crearControles();

		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(new Label("Nombre del color Rojo"), 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(new Label("Nombre del color Amarillo"), 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(new Label("Filas"), 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(new Label("Columnas"), 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(botonIniciar, 0, 5, 2, 1);
		
		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
	}

	private void crearControles() {

		campoNombreJugadorRojo = new TextField("Julieta");
		campoNombreJugadorAmarillo = new TextField("Pedro");
		
		campoColumnas = new TextField("7");
		campoFilas = new TextField("7");
		
		botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new IniciarJuego(this));
	}
	
	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero 
	 * 		 y comienza su ejecuci�n.
	 * 
	 */
	public void iniciar() {
		
		String nombreJugadorRojo = campoNombreJugadorRojo.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		
		//AGREGADO A MODO DE PRUEBA, CONSULTAR CON MARIANO{
		int filas = 0;
		int columnas = 0;
		
		boolean esNumeroDeFilasValido = esEnteroValido(campoFilas.getText());
		boolean esNumeroDeColumnasValido = esEnteroValido(campoColumnas.getText());
		
		if (esNumeroDeFilasValido && esNumeroDeColumnasValido) {
			filas = Integer.parseInt(campoFilas.getText());
			columnas = Integer.parseInt(campoColumnas.getText());
			
			CuatroEnLinea juego = new CuatroEnLinea(filas, columnas, 
					nombreJugadorRojo, nombreJugadorAmarillo);
			
			Tablero tablero = new Tablero(juego);
			tablero.mostrar();
		} else {
			if(!esNumeroDeFilasValido) {
				throw new Error(String.format("Hmm... no creo que existan %s filas", campoFilas.getText()));
			}
			
			if(!esNumeroDeColumnasValido) {
				throw new Error(String.format("Hmm... no creo que existan %s columnas", campoColumnas.getText()));
			}
		}
	}
	
	public static void main(String[] args) {
		
		Thread.setDefaultUncaughtExceptionHandler(new MostrarError());
		
		launch(args);
	}
	
	/**
	 * post: Devuelve si el n�mero ingresado en el campo de texto es un entero v�lido,
	 * de lo contrario lanza un error
	 */
	private boolean esEnteroValido(String numeroEnteroEnTexto) {
		try {
			Integer.parseInt(numeroEnteroEnTexto);
			return true;
		} catch(NumberFormatException error) {
			return false;
		}
	}
}
