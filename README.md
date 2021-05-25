# Calculator

## Implementación
He decidido implementar un endpoint de tipo GET (/operate) que reciba tres parámetros obligatorios.

- op1. BigDecimal con el primer valor de la operación
- op2. BigDecimal con el segundo valor de la operación.
- operation. String con la operación. Por ahora solamente tenemos dos operaciones **add** y **sub**.

Creamos una unica interfaz para los servicios (un servicio por operación) que define un solo método **operate(BigDecimal op1, BigDecimal op2)**

Cada una de las implementaciones de este interfaz define una operación en si misma y se anota con **@Qualifier** con el mismo nombre que nos llega como parámetro de entrada (operation). Por esta razón la implementación de nuevas operaciones no significa la modificación de código existente, simplemente creando una implementacion del interfaz y su método y anotándo con **@Qualifier** con el nombre descriptivo que enviaremos en el campo operation al endpoint.

En el controlador un metodo genérico (getCalculatorService) nos devuelve a partir del nombre del servicio la instancia necesaria para hacer el cálculo solicitado.

Para controlar operaciones no implementadas creo un ExceltionHandler que captura la NoSuchBeanDefinitionException que devuelve getCalculatorService cuando se intenta recuperar esta operación. Se tracea el error con el tracer proporcionado implementado como servicio.

## Tests
Se crean los siguientes tests:

#### Controller
**whenParametersAreNotValidThenReturns400**
- Este test comprueba que si no introducimos todos los parámetros necesarios (todos son obligatorios) o si el parámetro operation tiene un valor que no coincide con los actualmente aceptados (add y sub) nos devuelve un 400.

**whenParametersAreValidThenReturns200**
- Este test comprueba que si introducimos todos los parámetros y sus valores correctamente nos devuelve 200.

#### Services
**whenAddOperationReturnCorrectValue**
- Validamos que el servicio efectua la operacion add de manera correcta.

**whenAddOperationWithScaleAndPrecisionReturnCorrectValue**
- Validamos que el servicio efectua la operacion add de manera correcta con parámetros en los la escala y precisión han sido ajustadas.

**whenSubOperationReturnCorrectValue**
- Validamos que el servicio efectua la operacion sub de manera correcta.

**whenSubOperationWithScaleAndPrecisionReturnCorrectValue**
- Validamos que el servicio efectua la operacion add de manera correcta con parámetros en los la escala y precisión han sido ajustadas.

## Generacion de jar y ejecución

Para generar el jar desde la carpeta del proyecto ejecutar comando

    mvn clean package

Para ejecutar

    java -jar ./target/calculator-0.0.1-SNAPSHOT.jar

## Ejemplos

**add con nombre de parámetros correctos**

    curl -i -H --location --request GET 'http://localhost:8080/operate?op1=1231.12&op2=12.389&operation=add'
    
	HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 May 2021 14:32:33 GMT

	1243.509
    
**add con nombre de parámetros incorrectos op11 en lugar de op1**

	curl -i -H --location --request GET 'http://localhost:8080/operate?op11=1231.12&op2=12.389&operation=add'
	HTTP/1.1 400
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 May 2021 14:33:30 GMT
	Connection: close

	{"timestamp":"2021-05-25T14:33:30.378+00:00","status":400,"error":"Bad Request","path":"/operate"}

**sub con nombre de parámetros correctos**

	curl -i -H --location --request GET 'http://localhost:8080/operate?op1=12.12&op2=12.389&operation=sub'	
	HTTP/1.1 200
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 May 2021 14:35:17 GMT

	-0.269
    

**operacion no existente con nombre de parámetros correctos**

	curl -i -H --location --request GET 'http://localhost:8080/operate?op1=1231.12&op2=12.389&operation=no_existente'
	
	HTTP/1.1 400
	Content-Length: 0
	Date: Tue, 25 May 2021 14:36:15 GMT
	Connection: close


