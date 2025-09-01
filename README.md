# PricingApp

Aplicación Spring Boot que permite consultar las tarifas aplicables de productos para una marca en función de la fecha de aplicación. 

La aplicación sigue **arquitectura hexagonal / clean architecture**, aplica principios **SOLID** y demuestra buenas prácticas en **tests de integración y separación de capas**.

## Tabla de Contenidos
1. [Características](#características)
2. [Estructura del Proyecto](#estructura-del-proyecto)
   - [Main](#main)
   - [Application (Casos de uso)](#application-casos-de-uso)
   - [Domain (Modelo de negocio)](#domain-modelo-de-negocio)
   - [Infrastructure (Adaptadores)](#infrastructure-adaptadores)
     - [Persistence](#persistence)
     - [REST](#rest)
     - [Exceptions](#exceptions)
3. [Requisitos](#requisitos)
4. [Iniciación de datos](#iniciacion-de-datos)
5. [Endpoint](#endpoints)
6. [Test](#test)
     - [Test de Unitarios](#test-de-unitarios)
     - [Test de Integracion](#test-de-integracion)
7. [Tecnologías](#tecnologías)
8. [Recursos adicionales](#recursos-adicionales)
9. [Licencia](#licencia)


## Características
- **API REST**: consulta el precio aplicable según `brandId`, `productId` y `applicationDate`.
- **Arquitectura Hexagonal**: separación entre dominio, aplicación e infraestructura.
- **Persistencia con H2 en memoria**: base de datos inicializada automáticamente con datos de ejemplo.
- **DTOs y Mappers**: separación entre modelo de dominio y capa web.
- **Manejo global de excepciones** con `@RestControllerAdvice`.
- **Tests unitarios** y **tests de integración** con `JUnit 5` y `MockMvc`.

## Estructura del Proyecto

### Main
- **com.bcnc.pricing.PricingApplication** → Clase principal, punto de entrada de Spring Boot.

### Application (Casos de uso)
- **GetPriceUseCase** → Puerto de entrada (interfaz del caso de uso).
- **LoadPricePort** → Puerto de salida (contrato de persistencia).
- **PriceService** → Implementa la lógica de negocio del caso de uso `GetPriceUseCase`.

### Domain (Modelo de negocio)
- **Price** → Entidad de dominio que representa una tarifa.
- **PriceCriteria** → Objeto de criterios de búsqueda de precios.
- **PriceSelector** → Lógica para seleccionar la tarifa aplicable (ej. mayor prioridad).
- **PriceTestConstants** → Constantes de prueba.

### Infrastructure (Adaptadores)
#### Persistence
- **PriceRepositoryAdapter** → Implementa `LoadPricePort`, puente con JPA.
- **PriceEntity** → Entidad JPA que mapea la tabla `PRICE`.
- **JpaPriceRepository** → Repositorio Spring Data JPA.
- **PriceEntityMapper** → Conversión `PriceEntity ↔ Price`, `Price ↔ PriceEntity`.
- **H2DataInitializer** → Carga inicial de datos en H2.
- **H2DataConstants** → Constantes de datos de inicialización.

#### REST
- **PriceController** → Expone el endpoint `/price`.
- **PriceDTOIN** → DTO de entrada desde la API REST.
- **PriceDTOOUT** → DTO de salida para la respuesta.
- **PriceCriteriaMapper** → Mapea `PriceDTOIN → PriceCriteria`.
- **PriceDTOMapper** → Mapea `Price → PriceDTOOUT`.

#### Exceptions
- **GlobalExceptionHandler** → Manejo centralizado de errores.
- **PriceNotFoundException** → Excepción personalizada si no se encuentra tarifa.

## Requisitos
- **JDK 17+**
- **Maven 3.9+**

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
Otros ejemplos utiles:

-curl "http://localhost:8080/price?applicati	onDate=2020-06-14T16:00:00&productId=35455&brandId=1"
-curl "http://localhost:8080/price?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1"
-curl "http://localhost:8080/price?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1"

## Test
### Test de Unitarios

PriceServiceTest → Valida la lógica de negocio (PriceSelector, prioridades, fechas, etc.).

### Test de Integracion

La aplicación incluye **tests de integración** para validar el correcto funcionamiento de la API REST en escenarios de negocio reales.
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

### Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + MockMvc

## Recursos adicionales

Spring Boot Documentation

Arquitectura Hexagonal

## Licencia

Este proyecto está bajo la licencia **MIT** - ver el archivo [LICENSE](LICENSE) para más detalles.
