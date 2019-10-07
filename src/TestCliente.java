import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCliente extends JFrame {
    JLabel lbltituloIP = new JLabel();
    JLabel lbltitulo = new JLabel();
    JButton boton = new JButton();
    JTextArea txtIP = new JTextArea();
    JTextArea txtMensaje = new JTextArea();

    public TestCliente(){
        Cliente cliente = new Cliente();
        setSize(390,190);
        setTitle("Cliente");
        lbltituloIP.setText("IP de servidor:");
        lbltitulo.setText("Introduzca un mensaje:");
        boton.setText("Mandar");
        lbltituloIP.reshape(10,1,150,20);
        lbltitulo.reshape(10,20,150,20);
        txtMensaje.reshape(10,40,355,70);
        txtIP.reshape(100,2,265,20);
        boton.reshape(265,120,100,20);
        add(lbltituloIP);
        add(lbltitulo);
        add(txtIP);
        add(txtMensaje);
        add(boton);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cliente.mensaje_Saliente(txtIP.getText(),txtMensaje.getText(),9001);
            }
        });
    }
    public static void main(String[] args){
        //Se establece el tamaño de la ventana
        TestCliente TS = new TestCliente();
    }
}
