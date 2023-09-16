package Exepciones;

public class BadFEN extends Exception {

    public static String errorFilas = "Bad FEN format! El número de filas es distinto a ocho.";
    public static String errorColumnas = "Bad FEN format! El número de columnas es distinto a ocho en la fila: ";
    public static String errorReyesNegros = "Bad FEN format! Se han detectado dos reyes negros.";
    public static String errorReyesBlancos = "Bad FEN format! Se han detectado dos reyes blancos.";
    public static String errorCaracterIncorrecto = "Bad FEN format! Caracter incorrecto: ";
    public static String errorFaltaReyBlanco = "Bad FEN format! No se ha encontrado el rey blanco.";
    public static String errorFaltaReyNegro = "Bad FEN format! No se ha encontrado el rey negro.";
    public static String errorFaltanReyes = "Bad FEN format! No se ha encontrado ningún rey.";

    private String errActual;

    public BadFEN() {
    }


    public BadFEN(String message) {
        super(message);
        this.errActual = message;
    }


}
