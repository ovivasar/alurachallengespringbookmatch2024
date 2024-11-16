
# Proyecto Backend - Buscador de Libros (Gutendex API)

Este es un proyecto backend desarrollado en **Spring Boot**, diseñado para permitir la búsqueda de libros desde la API pública de **Gutendex**. La aplicación ofrece funcionalidades para realizar consultas sobre una vasta base de datos de libros disponibles en dominio público.

## Descripción

El proyecto interactúa con la **API de Gutendex** para obtener información sobre libros, basándose en parámetros como el título, autor, año de publicación, y más. El sistema permite realizar búsquedas según distintos filtros, guardar los resultados en una base de datos y visualizarlos a través de una interfaz de línea de comandos.

## Características

- **Búsqueda por Título/Autor**: Permite realizar consultas de libros por nombre o autor.
- **Búsqueda por Rango de Año**: Filtra libros publicados dentro de un rango específico de años.
- **Filtro por Copyright**: Realiza búsquedas para obtener solo los libros sin restricciones de derechos de autor.
- **Filtro por Idioma**: Permite buscar libros que estén en inglés o español.
- **Historial de Búsquedas**: Guarda el historial de libros buscados y muestra los resultados previamente obtenidos.
- **Top 5 Libros Descargados**: Muestra los cinco libros más descargados de la base de datos.

## Tecnologías Utilizadas

- **Spring Boot**: Framework utilizado para el desarrollo del backend.
- **Java**: Lenguaje principal utilizado para la implementación.
- **Gutendex API**: API pública para obtener datos sobre libros de dominio público.

## Instalación

1. Clona este repositorio:
    ```bash
    git clone https://github.com/tu-usuario/buscador-libros.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd buscador-libros
    ```

3. Compila el proyecto utilizando Maven:
    ```bash
    mvn clean install
    ```

4. Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

## Uso

1. Al ejecutar la aplicación, el sistema mostrará un menú en la consola con las opciones disponibles:
    - 1: Buscar libros por nombre o autor.
    - 2: Buscar libros por rango de años.
    - 3: Mostrar historial de búsquedas.
    - 4: Buscar libros sin copyright.
    - 5: Buscar libros por idioma.
    - 6: Mostrar los 5 libros más descargados.
    - 0: Salir de la aplicación.

2. Los resultados de la búsqueda se mostrarán en la consola y se guardarán en una base de datos para su posterior consulta.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas agregar nuevas características o mejorar el proyecto, por favor abre un **Pull Request**.

## Licencia

Este proyecto está licenciado bajo la licencia MIT.
