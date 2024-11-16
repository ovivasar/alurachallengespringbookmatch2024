package com.aluracursos.booksmatch.principal;

import com.aluracursos.booksmatch.model.*;
import com.aluracursos.booksmatch.repository.LibroRepository;
import com.aluracursos.booksmatch.service.ConsumoAPI;
import com.aluracursos.booksmatch.service.ConvierteDatos;
import com.aluracursos.booksmatch.service.LibroResponse;

import java.util.*;

public class Menu {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository repositorio;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;

    public Menu(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros (Nombre o Autor)
                    2 - Buscar libros por Años (añoInicio añoFin)
                    3 - Mostrar libros buscados(historial)
                    4 - Buscar libros Nombre o Autor sin Copyright* 
                    5 - Buscar libros Nombre o Autor Ingles(en), Español(es)         
                    6 - Top 5 libros mayor descarga                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibroPorAnos();
                    break;
                case 3:
                    mostrarLibrosBuscados();
                    break;
                case 4:
                    buscarLibroCopyRight();
                    break;
                case 5:
                    buscarLibroIdioma();
                    break;
                case 6:
                    buscarTop5LibrosDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private LibroResponse getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var cadenaBusqueda = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + cadenaBusqueda.replace(" ", "%20"));
        System.out.println(json);

        // Deserializar en LibroResponse, que incluye el campo 'results'
        LibroResponse libroResponse = conversor.obtenerDatos(json, LibroResponse.class);
        return libroResponse;
    }
    private void buscarLibroWeb() {
        // Obtener el objeto LibroResponse con la lista de resultados
        LibroResponse libroResponse = getDatosLibro();
        // Iterar sobre los resultados y crear objetos Libro para cada uno
        for (DatosLibro datos : libroResponse.getResults()) {
            Libro libro = new Libro(datos);  // Usar el constructor que mapea el record
            repositorio.save(libro);  // Guardar en el repositorio
            System.out.println(libro);  // Mostrar el libro
        }
    }

    private LibroResponse getDatosLibroAno() {
        System.out.println("Escribe los años inicio y fin separados x espacio");
        var cadenaBusqueda = teclado.nextLine();
        String[] years = cadenaBusqueda.split(" ");
        String formatted = String.format("author_year_start=%s&author_year_end=%s", years[0], years[1]);

        var json = consumoApi.obtenerDatos(URL_BASE + "?" + formatted);
        System.out.println(json);

        // Deserializar en LibroResponse, que incluye el campo 'results'
        LibroResponse libroResponse = conversor.obtenerDatos(json, LibroResponse.class);
        return libroResponse;
    }
    private void buscarLibroPorAnos() {
        // Obtener el objeto LibroResponse con la lista de resultados
        LibroResponse libroResponse = getDatosLibroAno();
        // Iterar sobre los resultados y crear objetos Libro para cada uno
        for (DatosLibro datos : libroResponse.getResults()) {
            Libro libro = new Libro(datos);  // Usar el constructor que mapea el record
            repositorio.save(libro);  // Guardar en el repositorio
            System.out.println(libro);  // Mostrar el libro
        }
    }


    private void mostrarLibrosBuscados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getGenero))
                .forEach(System.out::println);
    }

    private LibroResponse getDatosLibroCopyRight() {
        System.out.println("Escribe el nombre del libro acompañado de * (sin copyright)");
        var cadenaBusqueda = teclado.nextLine();
        // Verificar si contiene el asterisco
        boolean tieneAsterisco = cadenaBusqueda.contains("*");
        cadenaBusqueda = cadenaBusqueda.replace("*", "").trim();

        // Formatear el texto según la presencia del asterisco
        String sParametroCp = "copyright=" + (tieneAsterisco ? "false" : "true");

        System.out.println(URL_BASE + "?search=" + cadenaBusqueda.replace(" ", "%20") + "&" + sParametroCp);
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + cadenaBusqueda.replace(" ", "%20") + "&" + sParametroCp);
        System.out.println(json);

        // Deserializar en LibroResponse, que incluye el campo 'results'
        LibroResponse libroResponse = conversor.obtenerDatos(json, LibroResponse.class);
        return libroResponse;
    }
    private void buscarLibroCopyRight() {
        // Obtener el objeto LibroResponse con la lista de resultados
        LibroResponse libroResponse = getDatosLibroCopyRight();
        // Iterar sobre los resultados y crear objetos Libro para cada uno
        for (DatosLibro datos : libroResponse.getResults()) {
            Libro libro = new Libro(datos);  // Usar el constructor que mapea el record
            repositorio.save(libro);  // Guardar en el repositorio
            System.out.println(libro);  // Mostrar el libro
        }
    }
    private LibroResponse getDatosLibroIdioma() {
        System.out.println("Escribe el nombre del libro acompañado de Ingles ó Español ");
        var cadenaBusqueda = teclado.nextLine();

        // Convertir el texto a minúsculas para una comparación más sencilla
        String textoNormalizado = cadenaBusqueda.toLowerCase();

        String codigoIdioma;

        // Determinar el idioma basado en contenido y remover la palabra
        if (textoNormalizado.contains("ingles") || textoNormalizado.contains("english")) {
            codigoIdioma = "en";
            cadenaBusqueda = cadenaBusqueda.replaceAll("(?i)ingles|english", "").trim(); // Remover la palabra Inglés/English
        } else if (textoNormalizado.contains("español") || textoNormalizado.contains("spanish")) {
            codigoIdioma = "es";
            cadenaBusqueda = cadenaBusqueda.replaceAll("(?i)español|spanish", "").trim(); // Remover la palabra Español/Spanish
        } else {
            codigoIdioma = "en"; //por default
        }

        // Formatear el texto según la presencia del asterisco
        String sParametroCp = "languages=" + codigoIdioma;

        System.out.println(URL_BASE + "?search=" + cadenaBusqueda.replace(" ", "%20") + "&" + sParametroCp);
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + cadenaBusqueda.replace(" ", "%20") + "&" + sParametroCp);
        System.out.println(json);

        // Deserializar en LibroResponse, que incluye el campo 'results'
        LibroResponse libroResponse = conversor.obtenerDatos(json, LibroResponse.class);
        return libroResponse;
    }
    private void buscarLibroIdioma() {
        // Obtener el objeto LibroResponse con la lista de resultados
        LibroResponse libroResponse = getDatosLibroIdioma();
        // Iterar sobre los resultados y crear objetos Libro para cada uno
        for (DatosLibro datos : libroResponse.getResults()) {
            Libro libro = new Libro(datos);  // Usar el constructor que mapea el record
            repositorio.save(libro);  // Guardar en el repositorio
            System.out.println(libro);  // Mostrar el libro
        }
    }


    private void buscarTop5LibrosDescargados(){
        List<Libro> topLibros = repositorio.findTop5ByOrderByTotalDescargasDesc();
        topLibros.forEach(s ->
                System.out.println("Libro: " + s.getTitulo() + " Descargas: " + s.getTotalDescargas()) );
    }

}

