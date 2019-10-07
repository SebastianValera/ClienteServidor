import javax.swing.*;

public class TestServer{


    public static void main(String[] args){
        Servidor servidor = new Servidor( 9001);
        servidor.mensajeEntrante();
    }
}