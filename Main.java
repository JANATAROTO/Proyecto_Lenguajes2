import  java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Empleado> empleados;
    public static void main(String[] args) throws IOException {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Hola usuario, estas son las opciones a las cuales puede acceder de la Empresa");
        System.out.println("Para mostrar la base de datos de todos los trabajadores: 1");
        System.out.println("Para mostrar Colección de empleados cuyo salario se encuentre en un rango determinado: 2");
        System.out.println("Para mostrar Colección de empleados que pertenecen a cada departamento: 3");
        System.out.println("Para mostrar Cantidad de empleados por departamento: 4");
        System.out.println("Para mostrar Sumatoria de la nómina por departamento: 5");
        System.out.println("Para mostrar Nombre del empleado que más gana de cada departamento: 6");
        System.out.println("Para mostrar Nombre del empleado que mayor salario recibe (de todos): 7");
        System.out.println("Para mostrar Nombre del empleado que menor salario recibe (de todos): 8");
        System.out.println("Para mostrar Promedio de salario por departamento: 9");
        System.out.println("Para mostrar Promedio salario general: 10");
        System.out.println("Para mostrar Valor total de la nómina: 11");
        System.out.println("Para Salir: 12");
        System.out.println("--------------------------------------------------------------------------------------------");
        Scanner scan = new Scanner(System.in);
        System.out.println("introduce el numero del caso que deseas evaluar");
        int num = scan.nextInt();


        switch (num) {
            case 1:
                cargarArchivo();
            case 2:
                mostrarEmpleadosGananMAs();
                break;
            case 3:
                mostrarEmpleadosPorDepartamento();
                break;
            case 4:
                mostrarNumeroEmpleados();
                break;
            case 5:
                mostrarSumatoriaNomina();
                break;
            case 6:
                EmpleadoGanaMasDepartamento();
                break;
            case 7:
                EmpleadoGanaMas();
                break;
            case 8:
                EmpleadoGanaMenos();
                break;
            case 9:
                PromedioSalariosDepartamento();
                break;
            case 10:
                PromedioSalarios();
                break;
            case 11:
                ValorTotalNomina();
                break;
            case 12:
                break;



        }
    }

    public static void cargarArchivo() throws IOException{
        Pattern pattern = Pattern.compile(";");
        String fileName =  "empleados.csv";

        try (Stream<String> lines = Files.lines(Path.of(fileName))){
            empleados = lines.skip(1).map(line -> {
                String[] arr = pattern.split(line);
                return  new Empleado(arr[0], arr[1], arr[2], Double.parseDouble(arr[3]), arr[4]);
            }).collect(Collectors.toList());
            empleados.forEach(System.out::println);

        }
    }

    public static void mostrarEmpleadosGananMAs() throws IOException{
        cargarArchivo();
        Predicate<Empleado> cuatroASeisMil = e -> (e.getSalario() >= 4000 && e.getSalario() <= 6000);
        System.out.printf("%nEmpleados que ganan $4000-$6000 mensuales ordenados por salario:%n");
        empleados.stream()
                .filter(cuatroASeisMil)
                .sorted(Comparator.comparing(Empleado::getSalario))
                .forEach(System.out::println);

        // Muestra el primer empleado con salario en el rango $4000-$6000
        System.out.printf("%nPrimer empleado que gana $4000-$6000:%n%s%n",
                empleados.stream()
                        .filter(cuatroASeisMil)
                        .findFirst()
                        .get());

    }

    public static void mostrarEmpleadosPorDepartamento() throws IOException  {
        cargarArchivo();
        System.out.printf("%nEmpleados por departamento:%n");
        Map<String, List<Empleado>> agrupadoPorDepartamento = empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento));
        agrupadoPorDepartamento.forEach((departamento, empleadosEnDepartamento) -> {
                    System.out.println(departamento);
                    empleadosEnDepartamento.forEach(empleado -> System.out.printf(" %s%n", empleado));
                });

    }

    public static void mostrarNumeroEmpleados() throws IOException {
        cargarArchivo();
        System.out.printf("%nConteo de empleados por departamento:%n");
        Map<String, Long> conteoEmpleadosPorDepartamento = empleados.stream().collect(Collectors.groupingBy(Empleado::getDepartamento,
                TreeMap::new, Collectors.counting()));
        conteoEmpleadosPorDepartamento.forEach((departamento, conteo) -> System.out.printf("%s tiene %d empleado(s)%n", departamento, conteo));


    }

    public static void mostrarSumatoriaNomina() throws IOException{
        cargarArchivo();
        System.out.printf("%nSuma de los salarios de los empleados: %.2f%n",
                empleados.stream()
                        .mapToDouble(Empleado::getSalario)
                        .sum());

    }

    public static void EmpleadoGanaMasDepartamento() throws  IOException {
        cargarArchivo();
        Predicate<Empleado> cuatroASeisMil = e -> (e.getSalario() >= 24000 && e.getSalario() <= 24000);
        System.out.printf("%nEmpleado que gana mas:%n%s%n",
                empleados.stream()
                        .filter(cuatroASeisMil)
                        .findFirst()
                        .get());


    }
    public static void EmpleadoGanaMas() throws  IOException {
        cargarArchivo();
        Predicate<Empleado> cuatroASeisMil = e -> (e.getSalario() >= 24000 && e.getSalario() <= 24000);
        System.out.printf("%nEmpleado que gana mas:%n%s%n",
                empleados.stream()
                        .filter(cuatroASeisMil)
                        .findFirst()
                        .get());


    }

    public static void PromedioSalariosDepartamento() throws IOException{
        cargarArchivo();
        System.out.printf("Promedio de salarios de los empleados: %.2f%n",
                empleados.stream()
                        .mapToDouble(Empleado::getSalario)
                        .average()
                        .getAsDouble());

    }
    public static void PromedioSalarios() throws IOException{
        cargarArchivo();
        System.out.printf("Promedio de salarios de los empleados: %.2f%n",
                empleados.stream()
                        .mapToDouble(Empleado::getSalario)
                        .average()
                        .getAsDouble());

    }

    public static void ValorTotalNomina() throws IOException {
        cargarArchivo();
        System.out.printf("%nEl valor total de nomina es de: %.2f%n",
                empleados.stream()
                        .mapToDouble(Empleado::getSalario)
                        .sum());

    }

    public static void EmpleadoGanaMenos() throws IOException {
        cargarArchivo();
        Predicate<Empleado> cuatroASeisMil = e -> (e.getSalario() >= 2100 && e.getSalario() <= 2100);
        System.out.printf("%nEmpleado que gana menos:%n%s%n",
                empleados.stream()
                        .filter(cuatroASeisMil)
                        .findFirst()
                        .get());


    }

}