# Comprensión del diseño — Enunciado A
**Nombre:** juan pablo lozada lopez
**Fecha:** 19/10/26
Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el
contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona
qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> _Tu respuesta:_Porque un "sensor" a secas es una idea general y no existe en la vida real sin ser de algo específico (como temperatura o humedad). Obliga a las hijas a programar generarLectura() y fueraDeRango(v). Gracias a esto ganamos polimorfismo

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> _Tu respuesta:_en la composición, los sensores nacen y mueren con el nodo porque se crean directamente adentro con agregarSensor. En la agregación, el centro solo guarda una lista de nodos que ya existían afuera. Si se borra un NodoIoT, sus sensores también se eliminan; pero si borras el CentroMonitoreo, los nodos siguen existiendo normalmente.

3. Asociación

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> _Tu respuesta:_Ninguno es dueño de nadie, solo están relacionados para saber a quién le toca revisar qué. Es N:M porque un técnico puede tener varios nodos a cargo y a un nodo lo pueden revisar varios técnicos. Tampoco es composición porque si despiden o borran a un técnico, los nodos no tienen por qué desaparecer del sistema

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> _Tu respuesta:_Porque la clase Lectura solo necesita usar al validador un segundo cuando llama a esValida(), no necesita vivir pegada a él todo el tiempo. Si lo pasamos por parámetro nos ahorramos guardar un objeto extra en memoria por cada lectura creada, y hace que el código sea menos rígido y más limpio

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> _Tu respuesta:_El arreglo int[] se usó en Sensor porque ya sabemos de antemano el tamaño del historial (la capacidad). El ArrayList en NodoIoT sirvió para ir agregando sensores dinámicamente sin límite fijo. La ventaja del int[] circular es que es súper rápido y eficiente: cuando se llena, simplemente va sobrescribiendo la posición más vieja usando indiceActual sin desperdiciar memoria