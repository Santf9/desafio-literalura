# Literalura

Literalura es una aplicación de terminal desarrollada con Spring Boot que permite a los usuarios buscar, gestionar y obtener información sobre libros y autores utilizando la API de Gutendex (Proyecto Gutenberg).

![Literalura Logo](src/main/resources/images/literalura-logo.jpg)

## 🚀 Funcionalidades

La aplicación ofrece las siguientes funcionalidades:

### 1. Búsqueda de Libros por Título 📕
- Permite al usuario buscar libros en la API de Gutendex por título
- Guarda automáticamente el libro y su autor en la base de datos local
- Evita duplicados verificando si el libro ya existe en la base de datos

### 2. Listado de Libros Registrados ✍️
- Muestra todos los libros guardados en la base de datos
- Ordenados alfabéticamente por título
- Muestra información detallada de cada libro (título, autor, idioma, descargas)

### 3. Listado de Autores Registrados 👨‍🏫
- Muestra todos los autores guardados en la base de datos
- Ordenados alfabéticamente por nombre
- Incluye información sobre fecha de nacimiento, fallecimiento y libros asociados

### 4. Listado de Autores Vivos en un Determinado Año ⌛
- Permite al usuario ingresar un año específico
- Muestra los autores que estaban vivos en ese año (nacieron antes o en ese año y fallecieron después o en ese año)
- Ordenados alfabéticamente por nombre

### 5. Listado de Libros por Idioma ℹ️
- Permite al usuario seleccionar un idioma (español, inglés, francés o portugués)
- Muestra los libros disponibles en el idioma seleccionado
- Ordenados alfabéticamente por título

### 6. Top 10 Libros Más Descargados 🔝
- Ofrece dos opciones de consulta:
  - Libros más descargados en Gutendex (API)
  - Libros más descargados en la base de datos local
- Muestra los 10 libros con mayor número de descargas
- Ordenados de mayor a menor por número de descargas

### 7. Estadísticas de Descargas 📊
- Ofrece dos opciones de consulta:
  - Estadísticas de Gutendex (API)
  - Estadísticas de la base de datos local
- Muestra:
  - Libro con más descargas
  - Libro con menos descargas
  - Promedio de descargas

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Spring Boot**: Framework para el desarrollo de la aplicación
- **Spring Data JPA**: Para la persistencia de datos
- **H2 Database**: Base de datos en memoria (opcional, puede ser configurada con otras bases de datos)
- **Gutendex API**: API externa para obtener información de libros del Proyecto Gutenberg

## 📋 Requisitos

- Java 17 o superior
- Maven 3.6 o superior
- Conexión a Internet (para acceder a la API de Gutendex)

## 🔧 Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/literalura.git
   cd literalura
   ```

2. Compila el proyecto con Maven:
   ```bash
   mvn clean package
   ```

3. Ejecuta la aplicación:
   ```bash
   java -jar target/literalura-0.0.1-SNAPSHOT.jar
   ```

## 💻 Uso de la Aplicación

Al iniciar la aplicación, se presentará un menú interactivo con las siguientes opciones:

```
    ,--.   ,--.  ,--.                  ,---.  ,--.                      
    |  |   `--',-'  '-. ,---. ,--.--. /  O  \ |  |,--.,--.,--.--.,--,--.
    |  |   ,--.'-.  .-'| .-. :|  .--'|  .-. | |  ||  ||  ||  .--' ,-.  |
    |  '--.|  |  |  |  \  --. |  |   |  |  | ||  ||  |'  ''  '| | '-' |
    `-----'`--'  `--'   `----'`--'   `--' `--'`--' `----' `--'   `--`--'
                
    Bienvenido! Por favor selecciona una opción:
    
    1 - | Buscar libros por título | 📕
    2 - | Listar libros registrados | ✍️
    3 - | Listar autores registrados | 👨‍🏫
    4 - | Listar autores vivos en un determinado año | ⌛
    5 - | Listar libros por idioma | ℹ️
    6 - | Top 10 libros más descargados | 🔝
    7 - | Obtener estadísticas | 📊
    0 - | Salir | 👋
```

Selecciona la opción deseada ingresando el número correspondiente y sigue las instrucciones en pantalla.

## 🗄️ Estructura de la Base de Datos

La aplicación utiliza dos entidades principales:

### Autor
- **id**: Identificador único del autor
- **nombre**: Nombre completo del autor
- **fechaNacimiento**: Año de nacimiento del autor
- **fechaFallecimiento**: Año de fallecimiento del autor
- **libros**: Relación con los libros escritos por el autor

### Libro
- **id**: Identificador único del libro
- **titulo**: Título del libro
- **nombreAutor**: Nombre del autor (para optimizar consultas)
- **lenguaje**: Idiomas disponibles del libro
- **totalDescargas**: Número total de descargas del libro
- **autor**: Relación con el autor del libro

## 🔄 Flujo de la Aplicación

1. El usuario selecciona una opción del menú
2. Dependiendo de la opción, la aplicación puede:
   - Consultar la API de Gutendex
   - Consultar la base de datos local
   - Solicitar información adicional al usuario
3. La aplicación procesa los datos y muestra los resultados en la terminal
4. Vuelve al menú principal para una nueva consulta o para salir

## 👨‍💻 Desarrollo

Si deseas contribuir al proyecto o extenderlo:

1. Bifurca el repositorio
2. Crea una rama para tu funcionalidad:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz commits:
   ```bash
   git commit -m 'Añade nueva funcionalidad'
   ```
4. Envía tus cambios a tu fork:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Crea un Pull Request en GitHub

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.

## 🙏 Agradecimientos

- [Gutendex](https://gutendex.com/) por proporcionar acceso a una amplia colección de libros electrónicos
- [Alura](https://www.alura.com.br/) por la formación en Spring Boot y desarrollo Java
