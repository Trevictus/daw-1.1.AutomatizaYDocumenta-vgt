15/10/2025  
15/10/2025  
Víctor Gómez Tejada  
vgomtej353@g.educaand.es  
2ºDAW-DAW-U1-1.1.Automatiza y documenta

# Proyecto CI con JavaDoc y PDF

En este proyecto se implementa un workflow de integración continua en gitHub Actions automatizando la generación, conversión y publicación de documentación técnica para un proyecto Java. Utiliza `Javadoc` para crear documentación HTML y `wkhtmltopdf` para convertirla en PDF. 


## Funcionamiento del workflow

* Mediante [trigger](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L3C1-L9C21) se ejecutará el workflow en cada push a la rama principal y con `workflow_dispatch` se podrá lanzar este desde Github manualmente.
* Le damos [premisos](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L11-L13) al workflow de escritura, para que haga commits y push al repo.
* La [ejecución](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L17-L18) será en una maquina virtual de ubuntu de ultima versión.
* [Clonamos](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L19-L22) el repo para trabajar sobre el.
* [Configuramos Java y Maven](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L24-L30), instalando la versión JDK21 y habilitamos la caché maven para acelerar las siguientes ejecuciones.
* Para generar la documentación desde cero se [ejecuta Maven](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L32-L34) que creará la documentación por defecto en `target/site/apidocs`.
* Debemos [mover la documentación HTML](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L36-L41) creada a la carpeta destino, para ello crearemos el directorio primero, luego haremos una copia del archivo nuevo en el directorio destino, no sin antes eliminar versiones anteriores.
* Mediante el siguiente paso en el job [instalamos `wkhtmltopdf`](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L43-L47) en la maquina de ubuntu donde se está ejecutando el workflow.
* Creamos la [carpeta para el PDF](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L49-L53) que se va a generar. Eliminando también el PDF anterior si existiese.
* [Convertir HTML a PDF](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L55-L60). Mediante `--enable-local-file-access` le damos acceso local a la herramienta y con `docs/_build/html/index.html \ docs/pdf/report.pdf` se le indica que lo convierta.
* [Se suben los artefactos](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L63-L70).
* [Commit y push automático](https://github.com/Trevictus/daw-1.1.AutomatizaYDocumenta-vgt/blob/362590477179d06b365545cbba94678b1366f59a/.github/workflows/main.yml#L72-L80). Se configura Git, añade los archivos generados y hace commits si hay cambios. `|| echo` muestra un mensaje en caso de que no los haya.

## Documentación del proceso y preguntas

a).- **Identificación de herramientas de generación de documentación. ¿Qué herramienta o generador** (p. ej., Sphinx, pdoc, Javadoc, Doxygen, Dokka) **utilizaste en el workflow para crear la documentación en /docs?**  

* Utilicé **JavaDoc** que es una herramienta que genera documentación `HTML` del código a partir de los comentarios en el código fuente Java. Esta herramienta interpreta la sintaxis propia de JavaDoc. 
* Utilicé **wkhtmltopdf** que es una herramienta de línea de comandos que genera documentación en formato `pdf` mediante la conversión de un archivo `HTML`. 

b).- **Documentación de componentes. Muestra un fragmento del código con comentarios/docstrings estructurados** (p. ej., :param, :return: o etiquetas equivalentes) **que haya sido procesado por la herramienta. Comenta que estilo de documentación has utlizado:** (p. ej., reStructuredText, Google Style, KDoc)  

* [Fragmento comentado](https://github.com/Trevictus/proyecto-vgt/blob/b4bb7b6af60555c64a00cd98b89ea1d68a49f895/src/main/java/NombreInvertido.java#L32C5-L39C62) en este fragmento, comento el código y uso etiquetas como `@param` para indicar el parametro que recibe la función o `@return` para indicar que devuelve esta.  
Utilizando el estilo JavaDoc, se documenta el código con el estandar oficial para Java. Utiliza etiquetas especiales para describir y se coloca encima de los métodos.  

c).- **Multiformato. ¿Qué segundo formato** (además de HTML) **generaste? Explica la configuración o comandos del workflow y herramientas que lo producen.**  

* El segundo que utilicé es `PDF`. Mediante la herramienta `wkhtmltopdf` convertí la documentación `HTML` en `PDF`.
* Para la configuración se requiere [instalar `wkhtmltopdf`](https://github.com/Trevictus/proyecto-vgt/blob/b4bb7b6af60555c64a00cd98b89ea1d68a49f895/.github/workflows/main.yml#L43C7-L47C26). Trás eso se requiere añadir al workflow el paso de [conversion de HTML a PDF](https://github.com/Trevictus/proyecto-vgt/blob/b4bb7b6af60555c64a00cd98b89ea1d68a49f895/.github/workflows/main.yml#L55C7-L60C30), mediante el cual damos acceso local a `wkhtmltopdf` al archivo.

d).-	**Colaboración. Explica cómo GitHub facilita mantener la documentación** (actualizaciones del README.md y de /docs) **cuando colaboran varias personas** (PRs, reviews, checks de CI, protección de ramas)**.**  

* Un colaborador desde una rama independiente puede solicitar un PR con cambios en el contenido. Los reviews se pueden asignar a una o mas personas para que verifique los cambios solicitados y pueda aprobar o bloquear el PR, evitando así errores. Se pueden proteger la rama para que solo si se aprueban los PRs se fusione.

e).-	**Control de versiones. Muestra mensajes de commit que evidencien el nuevo workflow. ¿Son claros y descriptivos? Justifícalo. Ademas de un conjunto de mensajes de tus commits.**  

* [Mensajes de commits](https://github.com/Trevictus/proyecto-vgt/commits/main/). En la mitad de los casos se ve mensajes de commits descriptivos y claros como `Fixed wkhtmltopdf blocking local url
` o `actions/setup-java@v5.0.0 now @V3` ya que expresan que se cambió o hizo, mientras que en la otra mitad de casos los mensajes son vagos o no expresan lo que se ha hecho en la totalidad, son genéricos o poco técnicos. Los commits se deben hacer por partes, por cada implementación y no por un conjunto de funcionalidades añadidas las cuales no se pueden expresar correctamente en un commit.

f).-	**Accesibilidad y seguridad. ¿Qué medidas/configuración del repositorio garantizan que solo personal autorizado accede al código y la documentación?** (p. ej., repositorio privado, equipos, roles, claves/secretos, branch protection)**.**  

g).-	**Instalación/uso documentados. Indica dónde en el README.md explicas el funcionamiento del workflow y dónde detallas las herramientas y comandos de documentación.**  

* [Funcionamiento del workflow](#funcionamiento-del-workflow)

h).-	**Integración continua. Justifica por qué el workflow utilizado es CI. ¿Qué evento dispara automáticamente la generación/actualización de la documentación** (p. ej., push, pull_request, workflow_dispatch)?

* Es de integración continua porque mediante `on: push` se ejecuta tras cada push que se haga al repositorio manteniendo el proyecto actualizado. [`on: push`](https://github.com/Trevictus/proyecto-vgt/blob/b4bb7b6af60555c64a00cd98b89ea1d68a49f895/.github/workflows/main.yml#L3C1-L5C8) es el evento que dispara la actualización de la documentación.
