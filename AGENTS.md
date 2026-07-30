# Hobito Project - AI Agent Guide

Este proyecto sigue una arquitectura **Clean Architecture + MVVM + MVI** utilizando **Kotlin Multiplatform** y **Compose Multiplatform**.

Todas las sugerencias y el código generado deben respetar las siguientes reglas.

---

# Arquitectura

La aplicación sigue la siguiente dirección de dependencias:

Presentation → Domain ← Data

## Reglas

- Las dependencias siempre apuntan hacia Domain.
- Domain nunca depende de Presentation ni de Data.
- Data depende de Domain implementando sus interfaces.
- Presentation depende de Domain utilizando UseCases y modelos de dominio.
- Nunca introducir dependencias inversas.

---

# Estructura del proyecto

```
composeApp/src/commonMain/kotlin/com/nesshop/hobito/

core/
    di/
    ui/
        viewmodel/

data/

domain/

features/

designsystem/

navigation/
```

## Responsabilidad de cada módulo

### core

Contiene componentes compartidos por toda la aplicación.

Ejemplos:

- BaseViewModel
- Utilidades
- Extensiones
- Configuración de DI
- Componentes comunes

---

### domain

Contiene exclusivamente la lógica de negocio.

Puede contener:

- UseCases
- Entidades
- Value Objects
- Enums
- Repository interfaces
- Validadores
- Servicios de dominio

No debe contener:

- Compose
- Android
- Ktor
- Firebase
- SQLDelight
- Room
- Koin
- Recursos
- Navegación
- UI

---

### data

Responsable de obtener y almacenar información.

Puede contener:

- Repository implementations
- DTOs
- Local entities
- API Services
- DataSources
- Cache
- Database
- Networking

Data transforma los modelos externos en modelos de dominio.

Nunca exponer DTOs fuera de Data.

---

### features

Cada funcionalidad vive dentro de su propio módulo.

Ejemplo:

```
features/

    authentication/

        ui/

            login/

                contract/

                LoginViewModel.kt

                LoginScreen.kt
```

---

### designsystem

Implementa Atomic Design.

Puede contener:

- Atoms
- Molecules
- Organisms
- Theme
- Componentes reutilizables

---

### navigation

Toda la navegación debe centralizarse aquí.

---

# MVVM + MVI

Cada pantalla debe disponer de:

```
<Name>Contract.kt
<Name>ViewModel.kt
<Name>Screen.kt
```

## Contract

Debe contener:

- State
- Intent
- UiEffect

Ejemplo:

```kotlin
data class LoginState(...)

sealed interface LoginIntent

sealed interface LoginUiEffect
```

---

## ViewModel

Todos los ViewModels deben heredar de:

```kotlin
BaseViewModel<State, Intent, UiEffect>
```

El ViewModel únicamente debe:

- recibir Intents
- ejecutar UseCases
- actualizar State
- emitir UiEffects

Nunca debe contener lógica de negocio.

Toda la lógica pertenece a los UseCases.

---

# Clean Architecture

## Domain debe ser completamente agnóstico al framework.

Antes de añadir cualquier clase al dominio comprobar:

- ¿Compila sin Android?
- ¿Compila sin Compose?
- ¿Compila sin Firebase?
- ¿Compila sin Ktor?
- ¿Representa lógica de negocio?

Si alguna respuesta es NO, la clase no pertenece al dominio.

---

## Dependencias prohibidas en Domain

Nunca utilizar:

- Android SDK
- AndroidX
- Compose
- Compose Multiplatform Resources
- Material3
- Firebase
- Ktor
- SQLDelight
- Room
- Coil
- Koin

---

## Tipos prohibidos en Domain

Nunca utilizar:

- StringResource
- Painter
- ImageVector
- Color
- Modifier
- Dp
- TextStyle
- Shape

Ejemplo correcto:

```kotlin
enum class HobbyCategory {
    MOVIE,
    SERIES,
    BOOK,
    GAME
}
```

El mapeo a recursos pertenece a Presentation.

Ejemplo:

```kotlin
fun HobbyCategory.toStringResource()

fun HobbyCategory.toIcon()

fun HobbyCategory.toColor()
```

---

## Coroutines

Domain puede utilizar únicamente:

- suspend
- Flow

No utilizar:

- Dispatchers.Main
- Dispatchers.IO
- withContext()

La elección del Dispatcher pertenece a la infraestructura o presentación.

---

# Modelos

Cada capa posee sus propios modelos.

Domain

- entidades de negocio

Data

- DTOs
- Entities
- modelos de persistencia

Presentation

- UiState
- UiModel

Nunca reutilizar DTOs dentro del dominio.

Utilizar mappers entre capas.

---

# Compose

## Atomic Design

Seguir siempre Atomic Design.

Atoms

- Button
- Text
- Icon

Molecules

- TextField
- Cards
- List Items

Organisms

- Formularios
- Toolbars
- Secciones completas

Antes de crear un nuevo componente comprobar si ya existe uno reutilizable.

---

## Composables

Los composables deben ser principalmente Stateless.

Preferir:

```kotlin
MyScreen(
    uiState,
    onIntent
)
```

Evitar estado interno salvo que sea estrictamente necesario.

---

## Organización

Preferir muchos composables pequeños antes que un composable enorme.

Extraer componentes reutilizables siempre que sea posible.

---

## Preview

Toda nueva Screen debe incluir su Preview correspondiente.

---

## Recursos

Nunca utilizar Strings hardcodeadas.

Siempre utilizar:

- Res.string
- Res.drawable
- Res.plurals

Los recursos pertenecen únicamente a Presentation.

---

# Dependency Injection

El proyecto utiliza Koin.

Todos los módulos deben registrarse en:

```
core/di
```

No instanciar dependencias manualmente salvo casos excepcionales claramente justificados.

---

# Naming

Pantallas

```
HomeScreen.kt
```

ViewModels

```
HomeViewModel.kt
```

Contracts

```
HomeContract.kt
```

UseCases

```
GetUserUseCase
```

Repositories

```
UserRepository
```

Implementaciones

```
UserRepositoryImpl
```

---

# Reutilización

Antes de generar código nuevo:

1. Buscar un componente existente.
2. Buscar un UseCase existente.
3. Buscar un Mapper existente.
4. Buscar un Atom, Molecule u Organism existente.
5. Buscar una utilidad existente.

Preferir reutilizar antes que duplicar.

---

# Manejo de errores

Errores puntuales

→ UiEffect

Estado persistente

→ State

Nunca utilizar UiEffect para almacenar estado.

---

# Reglas de Oro

1. Respetar siempre Clean Architecture.
2. Mantener Domain completamente independiente de cualquier framework.
3. No introducir lógica de negocio en ViewModels ni Composables.
4. Priorizar la reutilización frente a la duplicación.
5. Mantener los composables pequeños, legibles y reutilizables.
6. Utilizar Atomic Design siempre que sea posible.
7. Mantener el estado inmutable.
8. Toda pantalla debe seguir MVVM + MVI.
9. Toda dependencia debe inyectarse mediante Koin.
10. Todo código nuevo debe integrarse con la arquitectura existente sin romper las reglas anteriores.

# Antes de escribir código

Antes de implementar una nueva funcionalidad:

1. Analiza la arquitectura existente.
2. Reutiliza componentes siempre que sea posible.
3. No introduzcas nuevas abstracciones si una existente resuelve el problema.
4. Mantén consistencia con el estilo del proyecto.
5. Si una solución incumple alguna regla de este documento, propone una alternativa compatible con la arquitectura.
