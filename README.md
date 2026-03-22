# 🚌 TransCaribeExpress — Sistema de Gestión de Transporte

Sistema de gestión de transporte público desarrollado en **Java** como proyecto universitario para la asignatura Programación III — Universidad Popular del Cesar.

---

## 📋 Descripción

Aplicación de consola que permite administrar la operación de una empresa de transporte intermunicipal. Gestiona rutas, vehículos, conductores, pasajeros, venta de tickets y reservas anticipadas de cupos.

---

## ⚙️ Funcionalidades

### 🗺️ Rutas
- Registro de rutas con ciudad origen, destino, distancia y tiempo estimado.

### 🚍 Vehículos
- Tres tipos disponibles: **Bus** (45 cupos, $15.000), **Buseta** (19 cupos, $8.000) y **MicroBus** (25 cupos, $10.000).
- Cada vehículo se asocia a una ruta registrada.

### 👥 Personas
- **Conductores:** registro con categoría y número de licencia.
- **Pasajeros:** clasificados automáticamente por edad y condición:
    - **Adulto Mayor** (≥ 60 años) — 30% de descuento
    - **Estudiante** — 15% de descuento
    - **Regular** — sin descuento

### 🎫 Tickets
- Venta de tiquetes con descuento automático según tipo de pasajero.
- Recargo del **20%** en días festivos colombianos.
- Límite de **3 tickets por pasajero por día**.

### 📅 Reservas
- Apartado anticipado de cupos con código único.
- Estados: **Activa**, **Convertida** y **Cancelada**.
- Expiración automática a las **24 horas** de creación.
- Conversión directa de reserva a ticket aplicando todas las reglas de venta.
- Un pasajero no puede tener más de una reserva activa para el mismo vehículo y fecha.

### 📊 Reportes
- Vehículo con mayor número de tickets vendidos.
- Total recaudado.
- Conteo de pasajeros por tipo.

---

## 💾 Persistencia

Todos los datos se guardan en archivos `.txt` con separador `;`, ubicados en la raíz del proyecto:

| Archivo | Contenido |
|---|---|
| `rutas.txt` | Rutas registradas |
| `bus.txt` / `buseta.txt` / `microbus.txt` | Vehículos por tipo |
| `conductores.txt` | Conductores |
| `pasajeros.txt` | Pasajeros |
| `tickets.txt` | Tickets vendidos |
| `reservas.txt` | Reservas y su estado |

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
modelo/     → Entidades del dominio (Vehiculo, Pasajero, Ticket, Reserva...)
dao/        → Acceso a datos (lectura y escritura en archivos .txt)
servicio/   → Lógica de negocio y reglas de la empresa
view/       → Menú de consola e interacción con el usuario
```

---



## 👨‍💻 Desarrollado por
Mateo Calderon Araujo - Jose Chinchia - Alberto Mario Mendoza


Estudiantes de Ingeniería de Sistemas — Universidad Popular del Cesar
Asignatura: Programación de Computadores III  
Docente: Ing. Esp. Alfredo Bautista