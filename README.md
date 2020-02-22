# ARSW-2020-1-H-APP #

## Definición de proyecto ##

### Título : H-APP ###

### Integrantes: ###

 - Juan Alberto Mejía S.
 
 - Jonatan Esteban González R.
 
 - Jimmy Andres Moya S.
 
### Resumen: ###
_Construcción de una plataforma web que apoye las tareas/responsabilidades de las enfermeras jefes y auxiliares en un hospital,para llevar a cabo un mejor control de pacientes en tiempo real._

### Descripción: ###

**Problema:** _La gestión de datos de los pacientes de un hospital se convierte en una tarea tediosa cuando las enfermeras necesitan visualizar la información de varios pacientes de forma clara y rápida. Como las enfermedades, alergias, y otras posibles condiciones que pueden afectar su estado de salud. Además tiene que lidiar con datos en papel, los cuales pueden ser escritos a mano o extraviados por otros auxiliares. Como consecuencia a esto, los otros auxiliares tardan en comprender el tratamiento actual que está recibiendo el paciente y otras posibles observaciones que se han evidenciado en los turnos y/o boletines médicos pasados, también lidian con confusiones de los resultados de exámenes médicos._

**Características:** _Las enfermeras jefes y auxiliares podrán tener un reporte en tiempo real de las enfermedades, tratamientos, resultados de examenes, examenes programados  y observaciones de cada paciente asignado en el  turno de cada enfermera y así poder brindarles a los pacientes una mejor atención y cuidado. También le permitirá ver a las enfermeras jefes los pacientes que presenten alguna complicación. Esta debe ser notificada por la auxiliar de enfermería y le llegara un mensaje de alerta a la enfermera jefe para que haga su respectivo procedimiento._

### **Diagrama de Clases** ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/diagramaClases.jpg)

### **Casos de Uso** ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/casosDeUso.jpg)

### **Historias de Usuario** ###

**_Administrador_**:
 - **COMO** Administrador **QUIERO** iniciar sesión **PARA PODER** administrar H-APP y sus recursos.
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

### **Entidad Relación** ###
![](https://github.com/JonatanGonzalez09/ARSW-2020-1-H-APP/blob/master/resources/entidadRelacion.jpg)

**Link del Drive del proyecto**
https://drive.google.com/drive/folders/1hvCMZi-28FHQ9eHM8lZRvgDKDoNwJfgX

**Link de la aplicación en Heroku**
https://happ2020.herokuapp.com/

Abra los links para observar el diseño de la interfaz gráfica usando el Wireframe **Balsamiq**

[Administrador](https://balsamiq.cloud/secxh97/pl9u9j5/r2278)

[Enfermera jefe](https://balsamiq.cloud/secxh97/pqdt8dm)

[Enfermera Auxiliar](https://balsamiq.cloud/secxh97/p99hqvl/r2278)



