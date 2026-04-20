
package prueba1;


public class Email {
    
     private String emisor; 
        private String asunto; 
        private String contenido; 
        private boolean leido; 

    public Email(String emisor, String asunto, String contenido){
        this.emisor = emisor; 
        this.asunto = asunto; 
        this.contenido = contenido; 
        this.leido = false; 
        
    }
    public String getEmisor(){
        return emisor; 
    }
    public String getAsunto(){
        return asunto; 
    }
    public String getContenido(){
        return contenido; 
    }
    
    public boolean isLeido(){
        return leido; 
    }
    public void marcarLeido(){
        this.leido = true; 
    }
    public void imprimir(){
        System.out.println("De: "+emisor);
        System.out.println("Asunto"+ asunto);
        System.out.println(contenido);
    }
}
