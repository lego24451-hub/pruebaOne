package prueba1;
import java.util.Scanner;

public class JavaLook {
    
    private static EmailAccount[] cuentas = new EmailAccount[100];
    private static EmailAccount cuentaActiva = null;
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        menuInicial();
    }
    
    
    public static void menuInicial() {
        int opcion;
        do {
            System.out.println("\n===== JAVALOOK =====");
            System.out.println("1. Login");
            System.out.println("2. Crear cuenta");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    crearCuenta();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }
    
    
    public static void login() {
        System.out.print("Direccion de correo: ");
        String dir = sc.nextLine();
        System.out.print("Contrasena: ");
        String pass = sc.nextLine();
        
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null &&
                cuentas[i].getDireccion().equals(dir) &&
                cuentas[i].getContrasena().equals(pass)) {
                
                cuentaActiva = cuentas[i];
                System.out.println("Bienvenido, " + cuentaActiva.getNombre());
                menuPrincipal();
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
    }
 
    public static void crearCuenta() {
       
        int pos = -1;
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("No hay espacio para mas cuentas.");
            return;
        }
        
        System.out.print("Direccion de correo: ");
        String dir = sc.nextLine();
        
        
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getDireccion().equals(dir)) {
                System.out.println("Esa direccion ya existe.");
                return;
            }
        }
        
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Contrasena: ");
        String pass = sc.nextLine();
        
        cuentas[pos] = new EmailAccount(dir, pass, nombre);
        cuentaActiva = cuentas[pos];
        System.out.println("Cuenta creada exitosamente. Bienvenido, " + nombre);
        menuPrincipal();
    }
    
        public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Ver inbox");
            System.out.println("2. Mandar correo");
            System.out.println("3. Leer un correo");
            System.out.println("4. Limpiar inbox");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    cuentaActiva.mostrarInbox();
                    break;
                case 2:
                    mandarCorreo();
                    break;
                case 3:
                    leerCorreo();
                    break;
                case 4:
                    cuentaActiva.eliminarLeidos();
                    System.out.println("Inbox limpiado.");
                    break;
                case 0:
                    cuentaActiva = null;
                    System.out.println("Sesion cerrada.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }
    
    
    public static void mandarCorreo() {
        System.out.print("Direccion del destinatario: ");
        String dest = sc.nextLine();
        
        // Buscar destinatario
        EmailAccount destinatario = null;
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getDireccion().equals(dest)) {
                destinatario = cuentas[i];
                break;
            }
        }
        
        if (destinatario == null) {
            System.out.println("Destinatario no encontrado.");
            return;
        }
        
        System.out.print("Asunto: ");
        String asunto = sc.nextLine();
        System.out.print("Contenido: ");
        String contenido = sc.nextLine();
        
        Email email = new Email(cuentaActiva.getDireccion(), asunto, contenido);
        boolean enviado = destinatario.recibirCorreo(email);
        
        if (enviado) {
            System.out.println("Correo enviado exitosamente.");
        } else {
            System.out.println("No se pudo enviar, el inbox del destinatario esta lleno.");
        }
    }
    
   
    public static void leerCorreo() {
        System.out.print("Posicion del correo: ");
        int pos = sc.nextInt();
        sc.nextLine();
        cuentaActiva.leerCorreo(pos);
    }
}
