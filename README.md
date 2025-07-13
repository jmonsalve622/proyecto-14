# proyecto-14
### Grupo N.° 14
### Joaquín Alonso Monsalve Luengo y Cristóbal Ignacio Valenzuela Escobar

## Enunciado

### Tema 3: Sistema de Reservas de clases particular
Este sistema está diseñado como una herramienta interna 
para que un administrador gestione eficientemente las 
reservas de clases particulares o tutorías. El administrador 
podrá crear y mantener perfiles detallados de los tutores, 
incluyendo las materias que imparten, sus tarifas, la 
cantidad máxima de estudiante por materias y, sus bloques de 
horarios de disponibilidad. De igual forma, el administrador 
registrará y gestionará la información de los estudiantes 
que solicitan el servicio. Cuando se reciba una solicitud de 
clase el administrador utilizará el sistema para buscar 
horarios y tutores compatibles con las necesidades del 
estudiante. Una vez encontrada una opción adecuada, el 
administrador creará la reserva directamente en el sistema, 
asignando al estudiante con el tutor en el horario específico. El sistema deberá prevenir conflictos horarios y mantener un "calendario" de las clases programadas con vistas filtradas para cada tutor u estudiante.  Además, el administrador se encargará de procesar modificaciones o cancelaciones de clases.

## Patrones de diseño implementados

### Factory Method
Se eligio este patron para facilitar la creación de perfiles, 
tanto de tutores como de estudiantes. Además de evidentemente 
hacer que el código sea más simple de comprender.

### Clases que utilizan Factory Method
- PerfilFactory
- EstudianteFactory
- TutorFactory

### Builder
Este patron se utilizó para complementar al Factory Method en
la creación de perfiles. Dado que Estudiante y Tutor comparten
algunos atributos, pero Tutor tiene atributos que Estudiante
no, como la tarifa o la cantidad maxima de estudiantes, Builder 
facilito esa diferencia de número de atributos de clases que 
pertenecen al mismo nivel jerárquico.

### Clases que utilizan Builder
- PerfilBuilder
- EstudianteFactory
- TutorFactory

### Observer
Finalmente, este patron se utilizó para notificar los cambios
que se hacían en relación con la creación o cancelación de 
clases, ya que si se creaba o se cancelaba una clase desde el
perfil de un Estudiante, se tenía que hacer modificaciones 
a la instancia de Tutor con la que se relacionaba la Clase.

### Clases que utilizan Observer
- Observador
- Notificador
- Estudiante
- Tutor

## Decisiones importantes

## Problemas y autocrítica