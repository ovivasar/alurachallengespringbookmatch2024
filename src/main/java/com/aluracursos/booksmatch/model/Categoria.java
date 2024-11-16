package com.aluracursos.booksmatch.model;
import java.util.List;
import java.util.stream.Collectors;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen"),

    CULTURA("Culture", "Cultura"),
    CIVILIZACION("Civilization", "Civilizacion"),
    SOCIEDAD("Society", "Sociedad"),
    FICCION("Fiction", "Ficcion"),
    LITERATURA("Literature", "Literatura");

    private String categoriaOmdb;
    private String categoriaEspanol;
    Categoria (String categoriaOmdb, String categoriaEspanol){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }
    public String getCategoriaOmdb() {
        return categoriaOmdb;
    }
    public String getCategoriaEspanol() {
        return categoriaEspanol;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Categoria fromEspanol(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    /*public static Categoria fromBookshelves(List<String> bookshelves) {
        // Validar que la lista no sea nula ni esté vacía
        if (bookshelves == null || bookshelves.isEmpty()) {
            throw new IllegalArgumentException("La lista de bookshelves es nula o está vacía.");
        }

        for (String shelf : bookshelves) {
            // Eliminar "Browsing:" del inicio si está presente
            String cleanedShelf = shelf.replace("Browsing:", "").trim();

            for (Categoria categoria : Categoria.values()) {
                // Verificar si ya está en español
                if (cleanedShelf.contains(categoria.getCategoriaEspanol())) {
                    return categoria;
                }
                // Si no está en español, verificar en inglés
                if (cleanedShelf.contains(categoria.getCategoriaOmdb())) {
                    return categoria;
                }
            }
        }

        throw new IllegalArgumentException("Ninguna categoría encontrada en: " + bookshelves);
    }*/
    public static Categoria fromBookshelves(List<String> bookshelves) {
        // Validar que la lista no sea nula ni esté vacía
        if (bookshelves == null || bookshelves.isEmpty()) {
            //throw new IllegalArgumentException("La lista de bookshelves es nula o está vacía.");
            return Categoria.values()[0];  // Devuelve el primer valor del enum
        }

        // Convertir todas las categorías de bookshelves a mayúsculas
        List<String> bookshelvesUpperCase = bookshelves.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        for (String shelf : bookshelvesUpperCase) {
            // Eliminar "BROWSING:" del inicio si está presente y convertir a mayúsculas
            String cleanedShelf = shelf.replace("BROWSING:", "").trim().toUpperCase();

            for (Categoria categoria : Categoria.values()) {
                // Comparar la categoría en mayúsculas con los valores de bookshelves
                if (cleanedShelf.equals(categoria.getCategoriaEspanol().toUpperCase()) ||
                        cleanedShelf.equals(categoria.getCategoriaOmdb().toUpperCase())) {
                    return categoria;
                }
            }
        }

        //throw new IllegalArgumentException("Ninguna categoría encontrada en: " + bookshelves);
        return Categoria.values()[0];
    }


}
