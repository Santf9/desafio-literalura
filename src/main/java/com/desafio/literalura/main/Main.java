package com.desafio.literalura.main;
import com.desafio.literalura.model.*;
import com.desafio.literalura.repository.IAutorRepository;
import com.desafio.literalura.repository.ILibroRepository;
import com.desafio.literalura.service.ConsumoApi;
import com.desafio.literalura.service.ConvertirDatos;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConvertirDatos conversor = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private final IAutorRepository autorRepository; // Repositorios para interactuar con la base de datos
    private final ILibroRepository libroRepository;

    // Constructor que recibe el repositorio de Libros y Autores para poder interactuar con la base de datos
    public Main(IAutorRepository autoresRepository, ILibroRepository librosRepository) {
        this.autorRepository = autoresRepository;
        this.libroRepository = librosRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ,--.   ,--.  ,--.                  ,---.  ,--.                      \\s
                    |  |   `--',-'  '-. ,---. ,--.--. /  O  \\ |  |,--.,--.,--.--.,--,--.\\s
                    |  |   ,--.'-.  .-'| .-. :|  .--'|  .-. | |  ||  ||  ||  .--' ,-.  |\\s
                    |  '--.|  |  |  |  \\  --. |  |   |  |  | ||  ||  |'  ''  '| | \\'-' |\\s
                    `-----'`--'  `--'   `----'`--'   `--' `--'`--' `----' `--'   `--`--'\\s
                
                    Bienvenido! Por favor selecciona una opción:
                    
                    1 - | Buscar libros por título | 📕
                    2 - | Listar libros registrados | ✍️
                    3 - | Listar autores registrados | 👨‍🏫
                    4 - | Listar autores vivos en un determinado año | ⌛
                    5 - | Listar libros por idioma | ℹ️
                    6 - | Top 10 libros más descargados | 🔝
                    7 - | Obtener estadísticas | 📊
                    0 - | Salir | 👋
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agregarLibros();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresPorAnio();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 6:
                    topDiezLibros();
                    break;
                case 7:
                    estadisticasApi();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo");
            }
        }
    }

    // Metodo para obtener los datos de un libro desde la API de Gutendex
    private DatosRespuesta obtenerDatosLibros() {
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        return conversor.obtenerDatos(json, DatosRespuesta.class);
    }

    // Metodo para crear un libro a partir de los datos obtenidos de la API y el autor
    private Libro crearLibro(DatosLibro datosLibros, Autor autor) {
        if (autor != null) {
            return new Libro(datosLibros, autor);
        } else {
            System.out.println("El autor es null, no se puede crear el libro");
            return null;
        }
    }

    // Metodo para agregar un libro a la base de datos
    private void agregarLibros() {
        System.out.println("Escribe el libro que deseas buscar: ");
        DatosRespuesta datos = obtenerDatosLibros();

        if (!datos.resultados().isEmpty()) {
            DatosLibro datosLibro = datos.resultados().getFirst();
            DatosAutor datosAutores = datosLibro.autores().getFirst();
            Libro libro;
            Libro libroRepositorio = libroRepository.findByTitulo(datosLibro.titulo());

            if (libroRepositorio != null) {
                System.out.println("Este libro ya se encuentra en la base de datos");
                System.out.println(libroRepositorio);
            } else {
                Autor autorRepositorio = autorRepository.findByNombreIgnoreCase(datosLibro.autores().getFirst().nombre());

                if (autorRepositorio != null) {
                    libro = crearLibro(datosLibro, autorRepositorio);
                } else {
                    Autor autor = new Autor(datosAutores);
                    autor = autorRepository.save(autor);
                    libro = crearLibro(datosLibro, autor);
                }
                try {
                    libroRepository.save(libro);
                    System.out.println("------ LIBRO AGREGADO ------\n");
                    System.out.println(libro);
                } catch (Exception e) {
                    System.out.println("No puedes registrar un mismo libro mas de una vez");
                }
            }
        } else {
            System.out.println("El libro no existe en la API de Gutendex, ingresa otro");
        }
    }

    // Metodo para listar los libros registrados en la base de datos
    private void librosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        }

        System.out.println("------ LOS LIBROS REGISTRADOS SON: ------\n");
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    // Metodo para listar los autores registrados en la base de datos
    private void autoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        }

        System.out.println("------ LOS AUTORES REGISTRADOS SON: ------\n");
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    // Metodo para listar autores vivos en un determinado año
    private void autoresPorAnio() {
        System.out.println("Escribe el año en el que deseas buscar: ");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        if (fecha < 0) {
            System.out.println("El año no puede ser negativo, intenta de nuevo");
        }

        List<Autor> autoresPorFecha = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(fecha, fecha);
        if (autoresPorFecha.isEmpty()) {
            System.out.println("No hay autores registrados en ese año");
            return;
        }

        System.out.println("------ LOS AUTORES VIVOS REGISTRADOS EN EL AÑO " + fecha + " SON: ------\n");
        autoresPorFecha.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    // Metodo para listar los libros por idioma
    private void listarPorIdioma() {
        System.out.println("Escribe el idioma por el que deseas buscar: ");
        String menu = """
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """;
        System.out.println(menu);
        var idioma = teclado.nextLine();

        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("Idioma no válido, intenta de nuevo");
        }

        List<Libro> librosPorIdioma = libroRepository.findByLenguajeContaining(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma");
        }

        System.out.println("------ LOS LIBROS REGISTRADOS EN EL IDIOMA SELECCIONADO SON: ------\n");
        librosPorIdioma.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    // Metodo para obtener los 10 libros más descargados de la API o de la base de datos
    private void topDiezLibros() {
        System.out.println("De donde quieres obtener los libros más descargados?");
        String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 1) {
            System.out.println("------ LOS 10 LIBROS MÁS DESCARGADOS EN GUTENDEX SON: ------\n");
            var json = consumoApi.obtenerDatos(URL_BASE);
            DatosRespuesta datos = conversor.obtenerDatos(json, DatosRespuesta.class);
            List<Libro> libros = new ArrayList<>();

            for (DatosLibro datosLibros : datos.resultados()) {
                Autor autor = new Autor(datosLibros.autores().getFirst());
                Libro libro = new Libro(datosLibros, autor);
                libros.add(libro);
            }

            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTotalDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);

        } else if (opcion == 2) {
            System.out.println("------ LOS 10 LIBROS MÁS DESCARGADOS EN LA BASE DE DATOS SON: ------\n");
            List<Libro> libros = libroRepository.findAll();

            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
            }

            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTotalDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } else {
            System.out.println("Opción no válida, intenta de nuevo");
        }
    }

    // Metodo para obtener estadísticas de descargas de libros
    private void estadisticasApi() {
        System.out.println("De donde quieres obtener las estadísticas: ");
        String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 1) {
            System.out.println("------ ESTADÍSTICAS DE DESCARGAS EN GUTENDEX ------\n");
            var json = consumoApi.obtenerDatos(URL_BASE);
            DatosRespuesta datos = conversor.obtenerDatos(json, DatosRespuesta.class);

            DoubleSummaryStatistics estadisticas = datos.resultados().stream()
                    .collect(Collectors.summarizingDouble(DatosLibro::totalDescargas));
            System.out.println("📈 Libro con más descargas: " + estadisticas.getMax());
            System.out.println("📉 Libro con menos descargas: " + estadisticas.getMin());
            System.out.println("📊 Promedio de descargas: " + estadisticas.getAverage());
            System.out.println("\n");
        } else if (opcion == 2) {
            System.out.println("------ ESTADÍSTICAS DE DESCARGAS EN LA BASE DE DATOS ------\n");

            List<Libro> libros = libroRepository.findAll();

            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
            } else {
                DoubleSummaryStatistics estadisticas = libros.stream()
                        .collect(Collectors.summarizingDouble(Libro::getTotalDescargas));

                System.out.println("📈 Libro con más descargas: " + estadisticas.getMax());
                System.out.println("📉 Libro con menos descargas: " + estadisticas.getMin());
                System.out.println("📊 Promedio de descargas: " + estadisticas.getAverage());
                System.out.println("\n");
            }
        } else {
            System.out.println("Opción no válida, intenta de nuevo");
        }
    }
}


