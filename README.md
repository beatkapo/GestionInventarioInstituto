# GestionInventarioInstituto
Aplicación para la gestión de inventario de un instituto.
## ¿Cómo funciona?
Es una aplicación muy sencilla, no será muy dificil entendera siguiendo los diferentes apartados.
### Guardar un dispositivo en la lista
Es tan sencillo como rellenar los campos, sin olvidarse de ninguno, y darle al botón de guardar.
![Gif animado con un ejemplo de como guardar un dispositivo en la lista](https://github.com/beatkapo/GestionInventarioInstituto/blob/main/GestionInventarioInstituto/doc/img/guardar.gif?raw=true)
### Filtrar la lista 
Puedes usar uno de los desplegables para filtrar la lista por diferentes atributos del dispositivo, siempre en orden ascendente.
![Gif animado con un ejemplo de como se filtra](https://github.com/beatkapo/GestionInventarioInstituto/blob/main/GestionInventarioInstituto/doc/img/filtrar.gif?raw=true)
### Editar un dispositivo que ya existe
Al pulsar el boton de editar tras seleccionar un dispositivo de la lista, se rellenan sus datos en el formulario y podemos editarlo, al pulsar en guardar se actualiza la información del dispositivo.
![Gif animado con un ejemplo de como se edita un dispositivo de la lista](https://github.com/beatkapo/GestionInventarioInstituto/blob/main/GestionInventarioInstituto/doc/img/editar.gif?raw=true)
### Imprimir la lista en un archivo de texto
Al pulsar en la impresora, que resulta ser un botón, se abre una ventana para guardar un archivo, tras seleccionar el nombre y el directorio donde se guardará se genera un archivo con extensión .txt con los datos del inventario, así como la fecha y sus dispositivos.
![Gif con un ejemplo de como se guarda un inventario](https://github.com/beatkapo/GestionInventarioInstituto/blob/main/GestionInventarioInstituto/doc/img/imprimir.gif?raw=true)
![Gif animado de el archivo apareciendo](https://github.com/beatkapo/GestionInventarioInstituto/blob/main/GestionInventarioInstituto/doc/img/pop.gif?raw=true)
El archivo de texto se ve algo parecido a esto:
```
Inventario del día dd-mm-aaaa

	Dispositivos ordenados por ID:
ID: 0\ Fecha de compra: dd-mm-aaaa\ Precio: 0.0\ Tipo: IMPRESORA\ Marca: marca\ Modelo: modelo
ID: 1\ Fecha de compra: dd-mm-aaaa\ Precio: 0.0\ Tipo: IMPRESORA\ Marca: marca\ Modelo: modelo
ID: 2\ Fecha de compra: dd-mm-aaaa\ Precio: 0.0\ Tipo: PANTALLA\ Marca: marca\ Modelo: modelo

```


