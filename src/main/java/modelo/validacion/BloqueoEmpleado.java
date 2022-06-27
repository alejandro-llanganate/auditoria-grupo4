package modelo.validacion;

import java.time.LocalTime;
import java.util.ArrayList;

public class BloqueoEmpleado {
    private String nombre;
    private int intentos;
    private LocalTime ultimaHoraErronea;
    private static ArrayList<BloqueoEmpleado> empleadosBloqueados = new ArrayList<>();

    private static int MAX_INTENTOS_TRES = 3;
    private static int MAX_INTENTOS_CUATRO = 4;
    private static int MAX_INTENTOS_CINCO = 5;


    public BloqueoEmpleado(String nombre) {
        this.nombre = nombre;
        this.intentos = 1;
        this.ultimaHoraErronea = LocalTime.now();
    }

    public static String verificarBloqueo(String nombre){
        /*Verifica el bloqueo del empleado, si el empleado puede acceder, se devuelve null
        * caso contrario retorna el mensaje de cuanto le falta para poder acceder.*/
        for(BloqueoEmpleado empleado : empleadosBloqueados) {
            if (empleado.getNombre().equals(nombre)) {
                if (empleado.getIntentos() == MAX_INTENTOS_TRES) {
                    if (empleado.getUltimaHoraErronea().plusMinutes(10).isAfter(LocalTime.now())) {
                        return "Empleado bloqueado durante 10 minutos";
                    }else{
                        return null;
                    }
                }else if(empleado.getIntentos() == MAX_INTENTOS_CUATRO){
                    if (empleado.getUltimaHoraErronea().plusHours(1).isAfter(LocalTime.now())) {
                        return "Empleado bloqueado durante 1 hora";
                    }else {
                        return null;
                    }
                }else if(empleado.getIntentos() >= MAX_INTENTOS_CINCO){
                        return "Empleado bloqueado indefinidamente";
                }else{
                    return null;
                }
            }
        }
        return null;
    }



    public static void bloquearEmpleado(String nombre){
        /*Aqui ingresan los empleados que pusieron mal la clave, si no estan en la lista, se les agrega,
        * en caso que ya esten en la lista, se la aumenta un intento fallido y se actualiza su hora erronea.*/
        for (int i = 0; i < empleadosBloqueados.size(); i++) {
            if (empleadosBloqueados.get(i).getNombre().equals(nombre)) {
                empleadosBloqueados.get(i).agregarIntento();
                empleadosBloqueados.get(i).setUltimaHoraErronea(LocalTime.now());

                return;
            }
        }
        empleadosBloqueados.add(new BloqueoEmpleado(nombre));

        for (BloqueoEmpleado empleado : empleadosBloqueados) {
            System.out.println(empleado.toString());
        }
    }

    public static void limpiarEmpleado(String nombreUsuario) {
        /*Elimina el empleado de la lista de empleados bloqueados*/
        for (int i = 0; i < empleadosBloqueados.size(); i++) {
            if (empleadosBloqueados.get(i).getNombre().equals(nombreUsuario)) {
                empleadosBloqueados.remove(i);
            }
        }

    }


    public String getNombre() {
        return nombre;
    }

    public int getIntentos() {
        return intentos;
    }

    public void agregarIntento() {
        this.intentos = this.intentos + 1;
    }

    public LocalTime getUltimaHoraErronea() {
        return this.ultimaHoraErronea;
    }

    public void setUltimaHoraErronea(LocalTime ultimaHoraErronea) {
        this.ultimaHoraErronea = ultimaHoraErronea;
    }

    @Override
    public String toString() {
        return "BloqueoEmpleado{" +
                "nombre='" + nombre + '\'' +
                ", intentos=" + intentos +
                ", ultimaHoraErronea=" + ultimaHoraErronea +
                '}';
    }
}


