# ARSW-2020-1-H-APP #

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/808778ab71044192946b471d27e6c39f)](https://app.codacy.com/manual/JonatanGonzalez09/ARSW-2020-1-H-APP/dashboard?bid=17170766)

# Definición de proyecto #

### Título : H-APP ###

### Asignatura: Arquitectura de software ###

## Descripción ##

El objetivo era definir, diseñar e implementar una aplicación Web, la cual debe cumplir con los requerimientos presentados durante el semestre. El proyecto se desarrolló a lo largo del semestre 2020-1, para ello se usó la metodología Scrum a través de la implementación de 3 Sprints de trabajo. 

### Proyecto planteado ###

#### Resumen: ####

Construcción de una plataforma web que apoye las labor de las enfermeras en cuanto al mentenimiento de la historia clínica y seguimiento de tareas asignadas.

#### Problema: #### 

Las tareas de una enfermar son muchas, dentro de estas están mantener actualizada la historia médica de un paciente cuando este está hospitalizado y cumplir con los procedimientos asignados a estos, ya sean llevarlos a exámenes, tomas de signos vitales, asegurar que el paciente tome sus medicamentos, entre otros. 

En un hospital donde un piso puede tener más de 20 cuartos las enfermeras asignadas deben estar pendientes de todos los pacientes presentes, muchas veces las enfermeras olvidan anotar algo que se le realizó al paciente, se acuerdan tarde de dar un medicamento o les toca quedarse después de su turno de trabajo a digitalizar y/o anotar todo lo que sucedió en el día.

### Autores: ###

 - Juan Alberto Mejía S.
 
 - Jonatan Esteban González R.
 
 - Jimmy Andres Moya S.
 
### Heroku ### 

La aplicación se dividió en front y back 

**Back: 
https://happ2020.herokuapp.com/**

**Front: 
https://h-app-front.herokuapp.com/login**

## Arquitectura de la aplicación ##

### Diagrama de Clases ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/diagramaClases.png)

### Diagrama de Entidad Relación ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/ER.png)

### Diagrama de Despliegue ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Diagrama%20de%20despliegue.png)

### Casos de Uso ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/casosDeUso.jpg)

### **Diagrama de Componentes** ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Diagrama%20de%20componentes.png)

### **Indicaciones de Uso** ###

Existen tres usuarios por defecto
 - admin 
 - manager
 - assistant
 
La contraseña para cada usario es el username + 123 por ejemplo usuario admin contraseña admin123

El administrados solo cumple como su nombre lo dice tareas administrativas y no puede acceder a los pacientes o a las funcionalidades de las enfermeras

Las enfermeras jefes (manager) son las que tienen más funcionalidades, ellas pueden asignar tareas a las enfermeras, procedimientos a los pacientes.

Las enfermeras asistentes tienen funcionalidades restringidas, ellas pueden ver los procedimientos asignados a ellas y decir cuando los completaron.

### **Historias de Usuario** ###

**_Administrador_**:
 - **COMO** Administrador **QUIERO** iniciar sesión **PARA PODER** administrar H-APP y **sus recursos.
 - **COMO** Administrador **QUIERO** cerrar sesión **PARA PODER** garantizar la integridad de la información.
 - **COMO** Administrador **QUIERO** agregar enfermero/a **PARA PODER** añadirlo al sistema del hospital.
 - **COMO** Administrador **QUIERO** agregar un piso **PARA PODER** habilitar una nueva zona para pacientes.
 - **COMO** Administrador **QUIERO** consultar el personal activo **PARA PODER** consultar los recursos humanos disponibles en el hospital.
 - **COMO** Administrador **QUIERO** modificar un piso **PARA PODER** asignar un nuevo número de camas.
 - **COMO** Administrador **QUIERO** modificar un piso **PARA PODER** habilitar las habitaciones.
 - **COMO** Administrador **QUIERO** modificar un piso **PARA PODER** deshabilitar las habitaciones.
 - **COMO** Administrador **QUIERO** modificar los enfermero/a **PARA PODER** actualizar sus datos.
 - **COMO** Administrador **QUIERO** modificar los enfermero/a **PARA PODER** actualizar darles de alta en el sistema.

**_Enfermera Jefe_**:
- **COMO** Enfermera jefe **QUIERO** asignar auxiliares de enfermeria **PARA PODER** conocer la disponibilidad a mis pacientes.
- **COMO** Enfermera jefe **QUIERO** consultar pacientes **PARA PODER** ver los procedimientos asignados.
- **COMO** Enfermera jefe **QUIERO** iniciar turno **PARA PODER** indicar el inicio de mi jornada laboral.
- **COMO** Enfermera jefe **QUIERO** cerrar turno **PARA PODER** indicar el final de mi jornada laboral.
- **COMO** Enfermera jefe **QUIERO** login app **PARA PODER** ingresar al sistema.
- **COMO** Enfermera jefe **QUIERO** logout app **PARA PODER** salir del sistema.
- **COMO** Enfermera jefe **QUIERO** actualizar estados pacientes **PARA PODER** mantener vigente los reportes medicos.
- **COMO** Enfermera jefe **QUIERO** asignar pacientes a Enfermeras Auxiliares **PARA PODER** distribuir el cuidado de los pacientes en un piso.
- **COMO** Enfermera jefe **QUIERO** generar informe del paciente **PARA PODER**  mantener al dia el estado del paciente.
- **COMO** Enfermera jefe **QUIERO** consultar novedades de sus enfermeras auxiliares **PARA PODER** conocer las emergencias que se presenten en los pacientes.
- **COMO** Enfermera jefe **QUIERO** añadir procedimientos **PARA PODER** mantener actulizados los procedimientos que se les pueden realizar a los pacientes.
- **COMO** Enfermera jefe **QUIERO** procesar novedades y solicitudes de sus enfermeras **PARA PODER** efectuar las solicitudes de las enfermeras Axuliares.
- **COMO** Enfermera jefe **QUIERO** solicitar cambio de piso del paciente **PARA PODER** mantener actulizado los cambios en el sistemas

**_Enfermera Auxiliar_**:
- **COMO** Enfermera Auxiliar **QUIERO** iniciar turno **PARA PODER** indicar el inicio de mi jornada laboral.
- **COMO** Enfermera Auxiliar **QUIERO** cerrar turno **PARA PODER** indicar el final de mi jornada laboral.
- **COMO** Enfermera Auxiliar **QUIERO** login app **PARA PODER** ingresar al sistema.
- **COMO** Enfermera Auxiliar **QUIERO** logout app **PARA PODER** salir del sistema.
- **COMO** Enfermera Auxiliar **QUIERO** solicitar permisos **PARA PODER** asignar mis responsabilidades temporalmente a otra auxiliar, mientras me encuentro descansado en mi turno laboral.
- **COMO** Enfermera Auxiliar **QUIERO** informar novedades **PARA PODER** aletar de una situacion critica de un paciente.
- **COMO** Enfermera Auxiliar **QUIERO** actualizar estado de procedimientos de sus pacientes **PARA PODER** dar el reporte medico de si se han realizado o no.
- **COMO** Enfermera Auxiliar **QUIERO** consultar estado de un paciente **PARA PODER** verificar su situacion actual o si tiene procemientos pendientes.
- **COMO** Enfermera Auxiliar **QUIERO** consultar pacientes asignados **PARA PODER** atender a los pacientes acargo.

**Link del Drive del proyecto**
https://drive.google.com/drive/folders/1hvCMZi-28FHQ9eHM8lZRvgDKDoNwJfgX




### **Requerimientos no funcionales** ###

## Performance

![Performance 1 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Performance1.png)

![Performance 2 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Performance2.png)

## Usabilidad

![Usabilidad 1 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Usability1.png)

![Usabilidad 2 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Usability2.png)

## Escalabiliad

![Escalabilidad 1 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Escalabilidad1.png)

![Escalabilidad 2 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Escalabilidad2.png)

## Disponibilidad

![Disponibilidad 1 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Disponibilidad1.png)

![Disponibilidad 2 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Disponibilidad2.png)

## Seguridad

![Seguridad 1 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Seguridad1.png)

![Seguridad 2 ](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/Non%20functional%20Requierments/Seguridad2.png)



Abra los links para observar el diseño de la interfaz gráfica usando el Wireframe **Balsamiq**

[Administrador](https://balsamiq.cloud/s86k2wg/pkw0j2s)

[Enfermera jefe](https://balsamiq.cloud/s86k2wg/piokebl/r1C5F)

[Enfermera Auxiliar](https://balsamiq.cloud/s86k2wg/po0go6a)



