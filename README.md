# ForoB
This repository will keep code related to Java

Una API RESTful robusta y segura para la gestión de un foro de discusión, desarrollada con Java y Spring Boot. Permite a los usuarios registrarse, autenticarse mediante tokens JWT y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre tópicos de discusión, garantizando la integridad de los datos y la seguridad de los endpoints.

Tecnologías Utilizadas
- Java

- Spring Boot 3 (Web, Data JPA, Security, Validation)

- PostgreSQL (Base de datos relacional)

- JSON Web Tokens (JWT) con la librería de Auth0 para autenticación stateless.

- Maven (Gestor de dependencias)

RUTAS PUBLICAS

1=> /login
2=> /registro

1. Registrar Nuevo Usuario
   Crea un usuario en la base de datos con su contraseña encriptada.

{
"login": "usuario@correo.com",
"clave": "MiContraseña123"
}

2. Iniciar Sesión (Login)
   Valida las credenciales y devuelve el Token JWT.

{
"login": "usuario@correo.com",
"clave": "MiContraseña123"
}

RUTAS PROTEGIDAS

- CREAR TOPICO  (POST) /topicos

{
  "titulo": "Duda con Spring Security",
  "mensaje": "No entiendo cómo configurar el SecurityFilterChain",
  "autor": "Tu Nombre",
  "curso": "Spring Boot 3"
  }

- LISTAR TOPICOS (GET) /topicos

- CONSUTLAR TOPICO (GET) /topicos/{id}

- ACTUALIZAR TOPICO (PUT) /topicos/{id}

{
"titulo": "Duda RESUELTA con Spring Security",
"mensaje": "Ya lo solucioné agregando el @EnableWebSecurity",
"autor": "Tu Nombre",
"curso": "Spring Boot 3"
}

- ELIMINAR TOPICO  (DELETE) /topicos/{id}