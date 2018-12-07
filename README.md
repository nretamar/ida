# ida

IMPORTANTE: Este proyecto se hizo en Eclipse Oxygen versión de Abril para 64Bits, también funciona en Eclipse Oxygen de Junio o Julio para 32 bits. El proyecto fue hecho originalmente utilizando SQL Server 2017 Standard con codigo de la facultad. Tambien funciona en SQL Server 2012 Enterprice para 32bits (Full crack no uade). Si se utiliza otro entorno, no aseguro que funcione.

PASOS PARA AGREGAR LOS PROYECTOS A ECLIPSE:

1) Instalar Spring Boot: Vas a Help --> Eclipse Marketplace --> Buscas exactamente "Spring" y te sale como resultado algo similar a Spring Tools, eso lo instalas.
2) Una vez instalado, importas todos los proyectos al eclipse. Quizas tengas que crear un nuevo Spring Boot starter proyect, hace eso si necesitas. Ahora si lo haces, tenes que copiar y pegar la carpeta "src" del proyecto de git "SpringBoot_Rest_Ida" a tu nuevo proyecto, y ademas, copiar y pegar el pom.xml, ahi hay configuraciones necesarias.
3) Teniendo todo importado, te van a salir errores en Servidor, esto es porque la ubicacion de las librerias son de la compu donde se creó el proyecto. Fijate que "Servidor"  --(Clic derecho)--> Propiedades --> Java Build Path --> Libraries --> Cuando te aparezcan las librerias con X, borra todas EXCEPTO la libreria de sistema (JRE o JDk Java). Luego le das a "Add external JARs...", vas a la carpeta "lib" del proyecto servidor, seleccionas todo lo de adentro y le das a aceptar o abrir. Con eso te importas todas las librerias a tu proyecto.
4) Quizas tengas problemas con esta pregunta "perhaps you are using JRE en vez de JDK?", esto es porque tenes que tener instalado en tu compu Java JDK, y a su vez, tenerlo en Eclipse. Busca en google Cómo agregar Java JDK a Eclipse, y te va a salir la rta.
5) Fijate en proyectos Servidor, Cliente_Swing y SpringBoot_Rest_Ida, hacer clic derecho --> propiedades --> Java Build Path --> pestaña Proyects --> y que tenga importado ahí el proyecto repositorio, sin eso no anda. Si no hay ningun proyecto ahí, le das a "add" y seleccionas el proyecto repositorio. EL TP funciona internamente por RMI para comunicarse entre distintos proyectos del eclipse, utilizando el proyecto Repositorio, inicializado en Server.Java en el proyecto Servidor-->Server. Para afuera de la red, se utiliza el SpringBoot para llamadas HTTP.
6) Tema Hibernate, tenes que configurarlo a tu manera

hibernate.connection.url jdbc:jtds:sqlserver://ASUS-PC/IDA
hibernate.connection.url jdbc:jtds:sqlserver://TU-PC/TU-Nombre-De-Base-De-Datos
hibernate.connection.username sasa
hibernate.connection.password 1234

Eso lo personalizas a tu gusto.

7) Estos son los mas importantes de Hibernate, lo vas a usar seguido.
#hibernate.hbm2ddl.auto create
hibernate.hbm2ddl.auto update

El # es // comentario, el "create" te crea las tablas en la base de datos automaticamente. La primera vez, lo corres como Create sin comentario, despues, paras el servidor y lo corres como Update. Cada vez que lo corres como Create, te borra toda la base de datos IDA y te crea todo de nuevo, por cada vez que corres el server o corres algun comando hacia allá.

8)  ¿Como arrancar el servidor?
    Con Hibernate como CREATE: Servidor --> test --> Levantar Servidor (clic derecho, run as, Java Aplication).
    Luego, stop al server corriendo.
    Ahora, con Hibernate como UPDATE: Servidor ..> test --> PruebaPedidos (clic derecho, run as, Java Aplication). Esto carga un ejemplo a tu BD.
    Cliente_Swing --> test --> testSwing (clic derecho, run as, Java Aplication). Con esto corres el swing.
    SpringBoot_Rest_Ida --> com.integracion.runSpringHere --> LevantarServicioRest (clic derecho, run as, Spring).

9) Quizas en ComprasControlador tengas que poner comentario a "new PostOrdenDeCompra" en el método altaOrden, para que no haya errores en caso que den de baja el servidor de Distribuidora.

10) Hay un proyecto llamado Cliente_Web, a ese no darle bola nunca, era un ejemplo que tenia a mano que no sirvió.
    




PASOS PARA SUBIR A GIT (Cuando queres subir cambios):

1) En la pestaña de "Git repositories", Click derecho a IDA --> Stashes --> Stash changes (poner cualquier nombre, ej "MisCambios"). // Sirve para guardar tus nuevos cambios que no eran los que estaban antes.
2) Despues haces un Click derecho a IDA --> Pull (->NO EL Pull...) //Sirve para traerme los ultimos codigos de la otra persona.
3) En la pestaña de "Git repositories", Click derecho a IDA --> Stashes --> "MisCambios", te abre una ventanita --> arriba a la derecha aparece un boton a la izquierda de la (X) que dice "apply Stached changes" y se hace el merge.
4) Cerras esa ventanita, y ahora vas a la ventana "Git Staging", luego seleccionar en "Unstaged Changes" y subis todas las clases que vas a persistir, (NO SUBIR Hibernate.properties, el archivo classpath, y otras cosas que no sean clases que modificaste)(debes estar conectado a internet).
