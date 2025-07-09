# FastFixes

## Descripción General

Aplicación móvil desarrollada en Android Studio que facilita la conexión entre clientes que necesitan servicios de reparación para el hogar y profesionales que ofrecen estos servicios.

## Funcionalidad Principal

La aplicación tiene como objetivo principal:
- **Para clientes**: Facilitar la contratación de servicios de reparaciones para el hogar
- **Para profesionales**: Permitir que los prestadores de servicios encuentren clientes potenciales

## Características del Sistema

### Sistema de Autenticación
- **Pantalla de inicio**: Menu inicial con opciones para iniciar sesión o registrarse
- **Tipos de usuario**: 
  - Cliente
  - Profesional

### Muro Principal
Interfaz común para ambos tipos de usuarios con funcionalidades específicas:

#### Para Clientes
- Publicar solicitudes de servicio
- Cargar fotografías del problema
- Agregar detalles y descripción del trabajo requerido

#### Para Profesionales
- Visualizar solicitudes publicadas por clientes
- Aceptar trabajos disponibles
- Comunicarse directamente con clientes mediante:
  - Llamadas telefónicas
  - WhatsApp

### Gestión de Solicitudes
- **Clientes**: Visualización de solicitudes publicadas
- **Profesionales**: Visualización de trabajos aceptados

## Stack Tecnológico

### Base de Datos
- **SQLite**: Sistema de gestión de base de datos local
- **Room**: Framework de persistencia para facilitar la conexión con SQLite

### Interfaz de Usuario
- **Material Design**: Componentes de diseño siguiendo las guías de Google
- **Shimmer**: Efectos de carga y animaciones
- **Lottie**: Animaciones vectoriales

### Gestión de Imágenes
- **Glide**: Biblioteca para carga y gestión eficiente de imágenes

## Limitaciones Conocidas

### Pendientes de Desarrollo
- **Tema oscuro**: Falta adaptación de la estética para dispositivos Android con tema oscuro activado

### Limitaciones Técnicas
- **Base de datos local**: SQLite funciona únicamente de forma local, sin sincronización en la nube
- **Escalabilidad**: El almacenamiento local limita la capacidad de compartir datos entre dispositivos

## Arquitectura del Sistema

La aplicación sigue un modelo de arquitectura local con:
- Capa de presentación (UI)
- Capa de lógica de negocio
- Capa de persistencia (SQLite + Room)

## Requisitos del Sistema

- **Plataforma**: Android
- **Entorno de desarrollo**: Android Studio
- **Base de datos**: SQLite (incluida en Android)
