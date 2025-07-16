package com.desafio.literalura.main;
import com.desafio.literalura.model.DatosRespuesta;
import com.desafio.literalura.repository.IAutorRepository;
import com.desafio.literalura.repository.ILibroRepository;
import com.desafio.literalura.service.ConsumoApi;
import com.desafio.literalura.service.ConvertirDatos;
import java.util.Scanner;

public class Main {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConvertirDatos conversor = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    // Repositorios para interactuar con la base de datos
    private IAutorRepository autorRepository;
    private ILibroRepository libroRepository;

    // Constructor que recibe el repositorio de Libros y Autores para poder interactuar con la base de datos
    public Main(IAutorRepository autoresRepository, ILibroRepository librosRepository) {
        this.autorRepository = autoresRepository;
        this.libroRepository = librosRepository;
    }

    public void muestraMenu () {
        var opcion = -1;
        System.out.println("Bienvenido! Por favor selecciona una opción: ");
        while (opcion != 0) {
            var menu = """
                    ,--.   ,--.  ,--.                  ,---.  ,--.                      \\s
                    |  |   `--',-'  '-. ,---. ,--.--. /  O  \\ |  |,--.,--.,--.--.,--,--.\\s
                    |  |   ,--.'-.  .-'| .-. :|  .--'|  .-. | |  ||  ||  ||  .--' ,-.  |\\s
                    |  '--.|  |  |  |  \\  --. |  |   |  |  | ||  ||  |'  ''  '| | \\'-'|\\s
                    `-----'`--'  `--'   `----'`--'   `--' `--'`--' `----' `--'   `--`--'\\s
                    
                    1 - | Buscar libros por título | 📕
                    2 - | Listar libros registrados | ✍️
                    3 - | Listar autores registrados | 👨‍🏫
                    4 - | Listar autores vivos en un determinado año | ⌛
                    5 - | Listar libros por idioma | ℹ️
                    6 - | Top 10 libros más descargados | 🔝
                    7 - | Obtener estadísiticas | 📊
                    8 - | Prueba de conexión a la API | 🌐
                    0 - | Salir | 👋
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
//                case 1:
//                    agregarLibros();
//                    break;
//                case 2:
//                    librosRegistrados();
//                    break;
//                case 3:
//                    autoresRegistrados();
//                    break;
//                case 4:
//                    autoresPorAño();
//                    break;
//                case 5:
//                    listarPorIdioma();
//                    break;
//                case 6:
//                    topDiezLibros();
//                    break;
//                case 7:
//                    estaditicasApi();
//                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo");
            }
        }
    }

    private DatosRespuesta obtenerDatosLibros() {
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        return conversor.obtenerDatos(json, DatosRespuesta.class);
    }

}
