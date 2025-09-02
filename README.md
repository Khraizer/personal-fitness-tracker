# 🏋️ Personal Fitness Tracker
**Personal Fitness Tracker** es una aplicación de consola diseñada para la gestión de entrenamientos y rutinas fitness.  
El proyecto implementa **Java Spring Boot** siguiendo principios de **arquitectura hexagonal**, lo que asegura una alta mantenibilidad, escalabilidad y separación de responsabilidades.

## 🚀 Características principales
### 👤 Gestión de usuarios con roles
**Cliente:**
- Puede hacer **log en un workout** (registrar que lo completó)
- Consultar sus **workout logs** personales
- Ver todos los **workouts disponibles**

**🛠️ Administrador:**
- Registrar **ejercicios**
- Registrar **workouts** (combinaciones de ejercicios)
- Ver todos los **ejercicios** registrados
- Ver todos los **workouts** disponibles

### 🏗️ Arquitectura
- **Arquitectura Hexagonal**: separación clara entre dominio, aplicación e infraestructura
- **Consola interactiva**: navegación por menús y ejecución de acciones desde terminal

## 🛠️ Tecnologías utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **Arquitectura Hexagonal**
- **Maven** para gestión de dependencias

## 📂 Estructura del proyecto
El proyecto sigue arquitectura hexagonal organizada en capas:
- **Domain**: entidades, lógica de negocio y reglas del sistema
- **Application**: casos de uso que orquestan la lógica del dominio
- **Infrastructure**: adaptadores para consola, persistencia y implementaciones técnicas

## ⚙️ Configuración e instalación
### Prerrequisitos
- Java 17 o superior
- Maven 3.6+

### 🔧 Pasos de instalación
1. **Clonar el repositorio**
   ```
   git clone https://github.com/Khraizer/personal-fitness-tracker.git
   cd personal-fitness-tracker
   ```
2. **Configurar base de datos**
   - Copiar el archivo `application.example.properties` a `application.properties`
   - En el envío del proyecto, se adjuntaron las lineas de configuración para el correcto funcionamiento, debes remplazar estas lineas, por las que se te proporcionaron:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/fitness_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   ```
3. **Compilar y ejecutar**
   ```
   mvn clean install
   mvn spring-boot:run
   ```
4. **Acceder a la aplicación**
   - La aplicación se iniciará en modo consola
   - Sigue las instrucciones en pantalla para navegar por los menús

## 📖 Futuras mejoras
- Integración con planes nutricionales
- Estadísticas y reportes personalizados para clientes

## 🤝 Contribuciones
Las contribuciones son bienvenidas. Si deseas proponer mejoras o reportar errores, abre un issue o envía un pull request.

## 📄 Licencia
Este proyecto se distribuye bajo la licencia MIT.
