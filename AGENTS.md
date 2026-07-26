# Hobito Project - AI Agent Guide

Este proyecto sigue una arquitectura y prácticas específicas para asegurar la consistencia y mantenibilidad en Compose Multiplatform.

## Arquitectura: MVVM + MVI

Utilizamos una arquitectura Model-View-ViewModel (MVVM) combinada con los principios de Model-View-Intent (MVI) para la gestión del estado de la UI.

### Componentes MVI (Contract)

Cada pantalla debe tener un archivo de "Contract" que defina:
1.  **State**: Un `data class` que representa el estado completo de la UI en un momento dado.
2.  **Intent**: Una `sealed interface` para las acciones del usuario o eventos que disparan cambios de estado.
3.  **UiEffect**: Una `sealed interface` para efectos secundarios únicos (navegación, mostrar errores, etc.).

### Implementación del ViewModel

Todos los ViewModels deben heredar de `BaseViewModel<S, I, F>`.

```kotlin
class MyViewModel(...) : BaseViewModel<MyState, MyIntent, MyUiEffect>(initialState = MyState()) {
    override suspend fun handleIntent(intent: MyIntent) {
        when (intent) {
            is MyIntent.Action -> { /* actualizar estado o enviar efecto */ }
        }
    }
}
```

## Estructura del Proyecto

- `composeApp/src/commonMain/kotlin/com/nesshop/hobito/`
    - `core/`: Clases base, utilidades y componentes comunes.
        - `di/`: Módulos de Koin.
        - `ui/viewmodel/`: `BaseViewModel`.
    - `data/`: Repositorios, servicios API, DTOs y fuentes de datos locales.
    - `domain/`: Lógica de negocio, Casos de Uso, Modelos de Dominio y Validadores.
    - `features/`: Módulos basados en funcionalidades. Cada funcionalidad sigue esta estructura:
        - `<feature>/ui/<screen>/`:
            - `contract/`: `<Screen>Contract.kt`
            - `<Screen>ViewModel.kt`
            - `<Screen>Screen.kt`
    - `designsystem/`: Componentes de Atomic Design (atoms, molecules) y definiciones del Tema.
    - `navigation/`: Lógica de navegación.

## Sistema de Diseño y UI

Seguimos principios de **Atomic Design**:
- **Atoms**: Bloques básicos (botones, iconos, tipografía).
- **Molecules**: Grupos de átomos (campos de texto con validación, items de lista).
- **Organisms**: Componentes complejos (formularios, app bars).

**Reglas de UI:**
- Usar `Res.string`, `Res.drawable`, etc., para todos los recursos (multiplatform).
- Los Composables deben ser mayoritariamente sin estado (*stateless*).
- El estado se observa mediante `uiState.collectAsState()`.

## Inyección de Dependencias (Koin)

El proyecto utiliza **Koin** para la inyección de dependencias. Los módulos se definen en `core/di/` y se inician en `App.kt`.

## Guías para Agentes

- **Nombramiento**: 
    - Pantallas: `<Name>Screen.kt`
    - ViewModels: `<Name>ViewModel.kt`
    - Contratos: `<Name>Contract.kt`
- **Lógica de Negocio**: Siempre debe ir en `domain/usecase`. Los ViewModels solo coordinan el estado y los efectos.
- **Validaciones**: Utilizar los validadores definidos en `domain/validation`.
- **Manejo de Errores**: Usar `UiEffect` para comunicar errores puntuales a la UI.

## Reglas de Oro (Core Rules)

1.  **MVI Estricto**: No saltarse la definición del Contract. Estado inmutable siempre.
2.  **Zero Strings**: No usar hardcoded strings en Composables. Todo vía `Res.string`.
3.  **DI con Koin**: Todo componente inyectable debe estar en `core/di`.
4.  **Stateless UI**: El 90% de los composables deben recibir el estado y devolver lambdas de eventos.
5.  **Single Responsibility**: Los UseCases solo hacen una cosa.
