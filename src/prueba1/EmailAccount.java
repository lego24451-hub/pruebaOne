package prueba1;

public class EmailAccount {
    private String direccion;
    private String contrasena;  
    private String nombre;
    private Email[] inbox;

    public EmailAccount(String direccion, String contrasena, String nombre) {
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.inbox = new Email[100];
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContrasena() {   
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean recibirCorreo(Email email) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {  
                inbox[i] = email;
                return true;
            }
        }
        return false;
    }

    public void mostrarInbox() {
        System.out.println("Cuenta: " + direccion);
        System.out.println("Usuario: " + nombre);

        int sinLeer = 0;
        int total = 0;

        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null) {   
                String estado = inbox[i].isLeido() ? "LEIDO" : "SIN LEER";
                System.out.println(i + " - " + inbox[i].getEmisor() + " - " + inbox[i].getAsunto() + " - " + estado);
                if (!inbox[i].isLeido()) sinLeer++;
                total++;
            }
        }

        System.out.println("Sin leer: " + sinLeer);
        System.out.println("Total de correos: " + total);
    }

    public void leerCorreo(int posicion) {
        if (posicion < 0 || posicion >= inbox.length || inbox[posicion] == null) { 
            System.out.println("Correo No Existe");
            return;
        }
        inbox[posicion].imprimir();
        inbox[posicion].marcarLeido();
    }

    public void eliminarLeidos() {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null && inbox[i].isLeido()) {
                inbox[i] = null;
            }
        }
    }
}