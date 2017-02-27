/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import sop_corba.*;

/**
 *
 * @author CristianCamilo
 */
public class inicial {

    static serviciosServidor servidorServicios;

    public static void main(String[] args) {
        for (String s : args) {
            System.out.println(s);
        }
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nContactando al N_S...\n");
            
            ORB orb;
            if (args.length > 2) {
                // crea e inicia el ORB
                orb = ORB.init(args, null);
            } else {
                // crea e inicia el ORB
                Properties props = new Properties();
                props.put("org.omg.CORBA.ORBInitialPort", "1099");
                props.put("org.omg.CORBA.ORBInitialHost", "localhost");
                orb = ORB.init(args, props);
            }
            
            // Obtiene la base el naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Usa NamingContextExt en lugar de NamingContext.Esto es 
            // Parte del Interoperable naming Service.  
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resuelve la referencia del objeto en el N_S
            String name = "serviciosServidor";
            servidorServicios = serviciosServidorHelper.narrow(ncRef.resolve_str(name));
            
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

	    estudianteCllbckImpl estudianteImpl = new estudianteCllbckImpl();

	    org.omg.CORBA.Object refEstudiante = rootpoa.servant_to_reference(estudianteImpl);
	    estudianteCllbck estudianteCallback = estudianteCllbckHelper.narrow(refEstudiante);
            
            String user = JOptionPane.showInputDialog(null, "Ingresa tu nombre de usuario");

            if (user.compareTo("admin") == 0) {
                String pass = JOptionPane.showInputDialog(null, "Ingresa tu contraseña");

                if (pass.compareTo("root") == 0) {
                    Cliente admin = new Cliente(user, 1, servidorServicios, null, null, null);
                    admin.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrectos");
                }
            } else {
                estudianteImpl.nombre = user;
                Cliente estudiante = new Cliente(user, 2, servidorServicios, estudianteCallback, estudianteImpl, orb);
                estudiante.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}
