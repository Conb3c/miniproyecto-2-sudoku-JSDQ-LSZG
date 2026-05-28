#  Sudoku 6×6  🎮

*Miniproyecto #2 del curso Fundamentos de Programación Orientada a Eventos (FPOE) de la Universidad del Valle.*

Juego de Sudoku 6×6 desarrollado en **Java con JavaFX**, usando una estructura de datos de **árbol** como modelo interno del tablero. El proyecto aplica el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**.

---

## 📋 Descripción

El jugador debe completar un tablero de 6×6 celdas con números del 1 al 6, siguiendo las reglas clásicas del Sudoku: cada número debe aparecer exactamente una vez en cada fila, columna y cuadrante (2×3). Al iniciar una partida, el juego genera automáticamente un tablero válido y muestra 2 números por cuadrante como pistas iniciales.

---

## 🧱 Arquitectura del proyecto

El proyecto sigue el patrón MVC:

```
src/main/java/
├── Model/
│   ├── Main.java            # Punto de entrada de la aplicación JavaFX
│   ├── tree.java            # Estructura de árbol que representa el tablero
│   ├── node.java            # Nodo del árbol (cada celda del tablero)
│   ├── dataOfTree.java      # Lógica de generación y validación del Sudoku
│   ├── Inode.java           # Interfaz del nodo
│   └── IdataOfTree.java     # Interfaz del modelo de datos
└── Controller/
    └── GameController.java  # Controlador JavaFX (maneja eventos de la UI)

src/main/resources/
└── com/example/miniproyecto2sudokujsdqlszg/
    └── GameView.fxml        # Vista del tablero (definida en FXML)
```

### Estructura de datos: Árbol lineal

El tablero se representa como un **árbol con encadenamiento lineal** de nodos hijo. El nodo raíz no representa una celda real (fila y columna = -1). A partir de él, cada nodo hijo representa una celda del tablero en orden de izquierda a derecha, fila por fila (posiciones [0,0] → [5,5]).

Cada nodo almacena:
- `data` — valor correcto de la celda (solución)
- `userValue` — valor ingresado por el jugador
- `row`, `column` — posición en el tablero
- `isItVisible` — si la celda está visible como pista

---

## ⭐ Features

- 🎲 Generación automática de tablero con backtracking
- 🗂️ Estructura MVC
- ✅ Validación en tiempo real de celdas
- 💡 Sistema de ayuda con revelado de celdas
- 🔄 Nueva partida
- 🖱️ Ingreso por teclado y botones numéricos en pantalla

---

## 🛠️ Tecnologías y dependencias

| Tecnología            | Versión                |
|-----------------------|------------------------|
| Java (Amazon Coretto) | 17                     |
| JavaFX                | 21.0.6                 |
| Maven                 | (Incluido via wrapper) |


---

## 🚀 Cómo ejecutar el proyecto

### 1. Clonado

Debes tener [Git](https://git-scm.com/) instalado y escribir estos comandos en la terminal del directorio donde quieres clonar el proyecto:

```bash
$ git clone https://github.com/Conb3c/miniproyecto-2-sudoku-JSDQ-LSZG.git
$ cd miniproyecto-2-sudoku-JSDQ-LSZG
```

o puedes descargar el [ZIP](https://github.com/Conb3c/miniproyecto-2-sudoku-JSDQ-LSZG/archive/refs/heads/main.zip) del proyecto

### 2. Ejecución

```bash
# En Linux/macOS
./mvnw clean javafx:run

# En Windows
mvnw.cmd clean javafx:run
```

---

## 🎮 Cómo jugar

1. Al iniciar, se genera un tablero con 2 números visibles por cuadrante.
2. Haz clic en una celda vacía y escribe un número del 1 al 6, o usa los botones numéricos de la interfaz.
3. La celda se pondrá **verde** si el número es correcto, **roja** si es incorrecto.
4. Usa el botón **Ayuda** para revelar automáticamente una celda.
5. Usa **Nueva Partida** para generar un tablero diferente.

---

## 👥 Autores

- *Juan Sebastian Duarte Quintero - 2516473*
- *Lesly Sharif Zapata Gómez - 2516574*