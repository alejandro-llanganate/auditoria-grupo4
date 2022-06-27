package modelo.validacion;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Validaciones {

    public static String calcularTiempoResolucion(String fechaCierre, String fechaCreacion){
        LocalDateTime fechaCierreDate = LocalDateTime.parse(fechaCierre, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime fechaCreacionDate = LocalDateTime.parse(fechaCreacion, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.valueOf(ChronoUnit.HOURS.between(fechaCreacionDate, fechaCierreDate)) + " horas";
    }

}
