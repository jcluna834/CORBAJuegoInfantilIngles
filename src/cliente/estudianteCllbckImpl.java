package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import sop_corba.*;

public class estudianteCllbckImpl extends estudianteCllbckPOA {

    private int puntos;
    private String mensaje;
    private List<String> mensajes;
    private estudianteCllbck otroEstudiante;
    private JTextArea chat;
    public String nombre;

    public estudianteCllbckImpl() {
        super();
        puntos = 0;
        mensaje = "";
        mensajes = new ArrayList<>();
        otroEstudiante = null;
    }

    // obtiene la cantidad de puntos acumulados
    public int puntos() {
        return puntos;
    }

    // suma los nuevos puntos a los puntos actuales
    public void puntos(int newPuntos) {
        System.out.println("agregar: " + newPuntos);
        puntos = puntos + newPuntos;
    }

    //establece la conexion entre los dos estudiantes, req: otroEstudiante debe haber iniciado el juego
    public boolean chat(sop_corba.estudianteCllbck otroEstudiante) {
        if (this.otroEstudiante != null) {
            return false;
        } else {
            this.otroEstudiante = otroEstudiante;
            if (otroEstudiante.sendMessage(nombre + ">> Conexión establecida")) {
                otroEstudiante.actualizar();
                return true;
            } else {
                this.otroEstudiante = null;
                return false;
            }
        }
    }

    //actualiza la ventana del chat del estudiante
    public void actualizar() {
        if(otroEstudiante!=null) {
            mensaje = otroEstudiante.getMessage();
            if(!mensajes.contains(mensaje))
                mensajes.add(mensaje);
            if (chat != null) {
                chat.setText("");
                mensajes.stream().forEach((aux) -> {
                    chat.append(aux + "\n");
                });
            }
        }
    }

    //envia un mensaje al chat
    public boolean sendMessage(String mensaje) {
        this.mensaje = mensaje;
        if (!mensajes.contains(mensaje)) {
            mensajes.add(mensaje);
        }
        if(otroEstudiante!=null)
            otroEstudiante.actualizar();
        return true;
    }

    //obtiene un mensaje del chat
    public String getMessage() {
        return mensaje;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    public JTextArea getChat() {
        return chat;
    }
}
