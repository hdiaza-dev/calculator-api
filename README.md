Calculator
--------------------


He decidido crear un objeto Operator que contiene las siguientes propiedades:

* Operator operator1 (se instancia a si mismo)
* Operator operator2 (se instancia a si mismo)
* String operation 
* Double value
* enum Operation que contiene las dos diferencias operation a implementar (ADD y SUB).

Con este diseño podemos anidar operaciones teniendo en cuenta que tendremos que validar los Operator que llegan al endpoint para que se de una de estas dos situaciones:

* Tiene informado la propiedad value y no informadas operator1, operator2 y operation.
* No tiene informada la propiedad value por lo que será obligatorio que tenga informadas las tres otras propiedades operator1, operator2 y operation.

En la capa de controller recibimos un objeto Operator en el endpoint /operate y validamos con las considerciones anteriores que este objeto está bien formado, si no es asi devolveremos BadRequest (400).
Desde la capa de controller después de validar el parámetro de entrada si es correcta llamaremos al servicio operate generico de CalculatorService que de manera recursiva calcula los resultados de operators y suboperators.

Test Services

* whenAddOperationWhithoutSubOperationReturnCorrectValue

Testea una operacion de suma básica sin operators anidados

* whenSubOperationWhithoutSubOperationReturnCorrectValue

Testea una operacion de resta básica sin operators anidados

* whenOperationWhithSubOperationReturnCorrectValue

Testea una operacion básica con suboperators anidados y operaciones mezcladas (sumas y restas).

* whenValidOperationReturnValidOperation

Testea el metodo de validacion del operator en la capa de servicios
l

Controller

* whenOperationIsNotValidThenReturns400

Testea que cuando llega al endpoint una peticion mal formada devuelva 400. 

* whenOperationIsValidThenReturns200

Testea que cuando llega al endpoint una peticion bien formada 200. 


Generacion de jar y ejecución
--------------------

Para generar el jar desde la carpeta del proyecto ejecutar comando

mvn clean package

Para ejecutar jar

java -jar 





