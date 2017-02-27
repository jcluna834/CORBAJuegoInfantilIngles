package servidor;

import java.util.Properties;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import sop_corba.*;

public class Servidor {

    public static void main(String args[]) {
        for (String s : args) {
            System.out.println(s);
        }
        try {
            ORB orb;
            if (args.length > 2) {
                // crea e inicia el ORB
                orb = ORB.init(args, null);
            } else {
                Properties props = new Properties();
                props.put("org.omg.CORBA.ORBInitialPort", "1099");
                props.put("org.omg.CORBA.ORBInitialHost", "localhost");
                orb = ORB.init(args, props);
            }

            // obtiene la referencia del rootpoa & activate el POAManager
            POA rootpoa
                    = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // crea el servant y lo registra con el ORB
            serviciosServidorImpl convref = new serviciosServidorImpl();

            // obtiene la referencia del objeto desde el servant
            org.omg.CORBA.Object obj
                    = rootpoa.servant_to_reference(convref);
            serviciosServidor href = serviciosServidorHelper.narrow(obj);

            // obtiene la base del contexto de nombrado
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            // Usa NamingContextExt el cual es parte de la especificacion 
            // Naming Service (INS).
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            // Realiza el binding de la referencia de objeto en el N_S
            String name = "serviciosServidor";
            NameComponent path[] = ncref.to_name(name);
            ncref.rebind(path, href);

            System.out.println("El Servidor esta listo y esperando ...");

            // esperan por las invocaciones desde los clientes
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("HelloServer: Saliendo ...");
    }
}
