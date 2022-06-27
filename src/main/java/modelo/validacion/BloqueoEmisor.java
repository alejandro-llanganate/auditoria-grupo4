package modelo.validacion;

import java.time.LocalTime;
import java.util.ArrayList;

public class BloqueoEmisor {
    private String nombre;
    private int intentos;
    private LocalTime ultimaHoraErronea;
    private static ArrayList<BloqueoEmisor> emisoresBloqueados = new ArrayList<>();

    private static int MAX_INTENTOS_TRES = 3;
    private static int MAX_INTENTOS_CUATRO = 4;
    private static int MAX_INTENTOS_CINCO = 5;

    //Contructor
    public BloqueoEmisor(String nombre) {
        this.nombre = nombre;
        this.intentos = 1;
        this.ultimaHoraErronea = LocalTime.now();
    }

    public static String verificarBloqueo(String nombre){
        /*Verifica el bloqueo del emisor, si el emisor puede acceder, se devuelve null
         * caso contrario retorna el mensaje de cuanto le falta para poder acceder.*/
        for(BloqueoEmisor emisor : emisoresBloqueados) {
            if (emisor.getNombre().equals(nombre)) {
                if (emisor.getIntentos() == MAX_INTENTOS_TRES) {
                    if (emisor.getUltimaHoraErronea().plusMinutes(10).isAfter(LocalTime.now())) {
                        return "Usuario bloqueado durante 10 minutos";
                    }else{
                        return null;
                    }
                }else if(emisor.getIntentos() == MAX_INTENTOS_CUATRO){
                    if (emisor.getUltimaHoraErronea().plusHours(1).isAfter(LocalTime.now())) {
                        return "Usuario bloqueado durante 1 hora";
                    }else {
                        return null;
                    }
                }else if(emisor.getIntentos() >= MAX_INTENTOS_CINCO){
                    return "Usuario bloqueado indefinidamente";
                }else{
                    return null;
                }
            }
        }
        return null;
    }


    public static void bloquearEmisor(String nombre){
        /*Aqui ingresan los usuarios que pusieron mal la clave, si no estan en la lista, se les agrega,
         * en caso que ya esten en la lista, se la aumenta un intento fallido y se actualiza su hora erronea.*/
        for (int i = 0; i < emisoresBloqueados.size(); i++) {
            if (emisoresBloqueados.get(i).getNombre().equals(nombre)) {
                emisoresBloqueados.get(i).agregarIntento();
                emisoresBloqueados.get(i).setUltimaHoraErronea(LocalTime.now());
                return;
            }
        }
        emisoresBloqueados.add(new BloqueoEmisor(nombre));

        for (BloqueoEmisor emisor : emisoresBloqueados) {
            System.out.println(emisor.toString());
        }
    }


    public static void limpiarEmisor(String nombreUsuario){
        /*Elimina el usuario de la lista de usuario bloqueados*/
        for (int i = 0; i < emisoresBloqueados.size(); i++) {
            if (emisoresBloqueados.get(i).getNombre().equals(nombreUsuario)) {
                emisoresBloqueados.remove(i);
            }
        }
    }


    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public LocalTime getUltimaHoraErronea() {
        return ultimaHoraErronea;
    }

    public void setUltimaHoraErronea(LocalTime ultimaHoraErronea) {
        this.ultimaHoraErronea = ultimaHoraErronea;
    }

    public void agregarIntento(){
        this.intentos++;
    }


}
