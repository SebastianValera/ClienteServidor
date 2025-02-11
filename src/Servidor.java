import javax.swing.*;
import javax.swing.border.Border;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame {

    //Declaracion de un socket
    private Socket socket;
    //Declaracion de un Socket servidor
    private ServerSocket servidor;
    //Declaracion de un flujo de entrada
    private ObjectInputStream flujo_entrada;
    JLabel lblTitulo = new JLabel();
    JLabel lblEncabezado = new JLabel();
    JLabel lblFuncion = new JLabel();
    JLabel lblMensaje = new JLabel();

    /** Crea una nueva intancia para el servidor */
    public Servidor(int puerto) {
        //Intenta levantar el objeto, en caso de no ser posible cacha la excepcion
        try {
            //Crea un objeto a partir del constructo ServerSocket(No. puerto)
            servidor = new ServerSocket(puerto);
        }
        //Cacha la exception
        catch(IOException ex ){
            //Imprime la traza de lanzamiento de excepciones
            ex.printStackTrace();
        }
    }
    //Metodo mensaje entrante no resive ningun parametro pero retorna una cadena de caracteres
    public void mensajeEntrante(){
        //Variable que contendra el mensaje que reciva desde el socket
        String mensaje = null;
        //Es necesario declara un try ya que es posible que el codigo en la
        try{
            //Se establece el tamaño de la ventana
            setSize(390,190);
            setTitle("Servidor");
            //Manda a la pantalla la cadena Servidor concatenandole la direccion local del equipo mas una cadena que dice listo y escuchando
            lblTitulo.setText("Servidor ["+ InetAddress.getLocalHost().getHostAddress()+"] listo y escuchando...");
            lblEncabezado.setText("Función:                                              Mensaje:");
            //Agrega el control label a la ventana
            add(lblTitulo);
            add(lblEncabezado);
            //Asigna las coordenadas en donde aparece en la ventana, junto con el tamaño del control
            lblTitulo.reshape(10,1,250,20);
            lblEncabezado.reshape(10,20,250,20);
            lblFuncion.reshape(10,40,170,100);
            lblMensaje.reshape(195,40,170,100);
            lblFuncion.setBorder(BorderFactory.createBevelBorder(1));
            lblMensaje.setBorder(BorderFactory.createBevelBorder(1));
            add(lblFuncion);
            add(lblMensaje);
            //Muestra la ventana
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            //el metodo accept de los ServerSocket retorna un socket
            socket =servidor.accept();
            //se crea un flujo de entrada a partir de la declaracion del objeto ObjectInputStream(pasandole como parametro el socket que crea la conexcion a partir del ServerSocket
            flujo_entrada = new ObjectInputStream(socket.getInputStream());
            //leo el mensaje entrante del objeto flujo de entrada y al objeto lo convierto a un String por medio del Casting
            mensaje = (String) flujo_entrada.readObject();
            //Cierro el socket
            socket.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //regresa el mensaje
        lblTitulo.setText("Mensaje recibido:");
        if (mensaje.contains("@gato://")){
            lblFuncion.setText(mensaje.substring(8));
        }else{
            lblMensaje.setText(mensaje);
        }
    }
    public void cerrarSocket() {
        try {

            socket.close();
        } catch (IOException ex) {
            System.out.println("no queda ");
            ex.printStackTrace();
        }
    }
}
