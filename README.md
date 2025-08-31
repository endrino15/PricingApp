# PricingApp

Aplicación Spring Boot que expone un endpoint REST para consultar tarifas de productos de una cadena (brand) en base a una fecha de aplicación. Implementa **arquitectura hexagonal**, principios **SOLID** y utiliza una base de datos en memoria H2.

## Características

- Endpoint REST para obtener la tarifa aplicable de un producto.
- Arquitectura **Hexagonal / Clean**.
- Persistencia con **H2 en memoria**, inicializada con datos de ejemplo.
- Manejo de excepciones globales (`@RestControllerAdvice`).
- Tests de integración con `MockMvc`.
- Uso de **DTOs** para separación de dominio y capa web.
- Logs de SQL habilitados (`spring.jpa.show-sql=true`).


## Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + MockMvc


## Ejecución local
### Requisitos
- JDK 17+
- Maven 3.9+

## Iniciación de datos

Se cargan automáticamente al arrancar la aplicación con el H2DataInitializer:

BRAND_ID	PRODUCT_ID	PRICE_LIST	START_DATE	END_DATE	PRIORITY	PRICE	CURR
1	35455	1	2020-06-14T00:00:00	2020-12-31T23:59:59	0	35.50	EUR
1	35455	2	2020-06-14T15:00:00	2020-06-14T18:30:00	1	25.45	EUR
1	35455	3	2020-06-15T00:00:00	2020-06-15T11:00:00	1	30.50	EUR
1	35455	4	2020-06-15T16:00:00	2020-12-31T23:59:59	1	38.95	EUR

## EndPoints
application → Casos de uso (lógica de negocio)
domain → Entidades del dominio
infrastructure → Adaptadores (JPA, REST, etc.)
controller → Exposición de la API REST
    
## EndPoints

-GET http://localhost:8080/price?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1

-Respuesta:

{
  "brandId": 1,
  "productId": 35455,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.5,
  "currency": "EUR"
}

## Test de Integracion

La aplicación incluye **tests de integración** para validar el correcto funcionamiento de la API REST en escenarios de negocio reales.

### Tests implementados
En **PriceControllerIntegrationTest** se definen **5 casos de prueba oficiales**, que cubren los escenarios de aplicación de tarifas solicitados:

1. **Petición a las 10:00 del día 14** → Se espera la tarifa con `priceList = 1`.
2. **Petición a las 16:00 del día 14** → Se espera la tarifa con `priceList = 2`.
3. **Petición a las 21:00 del día 14** → Se espera la tarifa con `priceList = 1`.
4. **Petición a las 10:00 del día 15** → Se espera la tarifa con `priceList = 3`.
5. **Petición a las 21:00 del día 16** → Se espera la tarifa con `priceList = 4`.

Cada test valida:
- Código de estado HTTP (`200 OK`).  
- Identificador de producto y marca en la respuesta.  
- Tarifa aplicada (`priceList`).  
- Precio final calculado (`price`).  

### Ejecución
Para ejecutar todos los tests:

bash
mvn test

