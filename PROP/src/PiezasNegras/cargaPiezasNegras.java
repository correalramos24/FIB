package PiezasNegras;


import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;


public class cargaPiezasNegras {


    InputStream bishop;
    InputStream rook;
    InputStream pawn;
    InputStream queen;
    InputStream king;
    InputStream knight;


    public cargaPiezasNegras() {
            String b = "Nando";
            bishop = getClass().getResourceAsStream(b+"Bishop.png");
            king = getClass().getResourceAsStream(b+"King.png");
            knight = getClass().getResourceAsStream(b+"Knight.png");
            pawn = getClass().getResourceAsStream(b+"Pawn.png");
            rook = getClass().getResourceAsStream(b+"Rook.png");
            queen = getClass().getResourceAsStream(b+"Queen.png");
    }

    public InputStream getBishop() {
        return bishop;
    }

    public InputStream getRook() {
        return rook;
    }

    public InputStream getPawn() {
        return pawn;
    }

    public InputStream getQueen() {
        return queen;
    }

    public InputStream getKing() {
        return king;
    }

    public InputStream getKnight() {
        return knight;
    }
}
