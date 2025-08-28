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

---

## Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + MockMvc

## Estructura del proyecto

├── domain/
│   ├── model/
│   │   └── Price.java                  # Entidad de dominio (sin dependencias de Spring/JPA)
│   └── service/
│       └── PriceSelector.java          # Lógica de negocio para seleccionar tarifa correcta según fecha/prioridad
│
├── application/
│   ├── port/in/
│   │   └── GetPriceUseCase.java        # Interfaz de entrada (caso de uso)
│   ├── port/out/
│   │   └── LoadPricePort.java          # Puerto de salida (persistencia de precios)
│   └── service/
│       └── PriceService.java           # Implementación del caso de uso, usa PriceSelector y LoadPricePort
│
├── infrastructure/
│   ├── persistence/
│   │   ├── entity/
│   │   │   └── PriceEntity.java       # Entidad JPA mapeada a H2
│   │   ├── jpa/
│   │   │   └── JpaPriceRepository.java  # Extiende JpaRepository<PriceEntity, Long>
│   │   └── adapter/
│   │       └── PriceRepositoryAdapter.java  # Implementa LoadPricePort usando JpaPriceRepository
│   └── web/
│       ├── controller/
│       │   └── PriceController.java   # Endpoints REST GET /price?brandId=&productId=&date=
│       └── dto/
│           └── PriceDTO.java          # Entrada/salida REST
│
└── config/
    └── H2DataInitializer.java          # Inicializa datos de ejemplo al arrancar la app
    
## Iniciación de datos

Se cargan automáticamente al arrancar la aplicación con el H2DataInitializer:

BRAND_ID	PRODUCT_ID	PRICE_LIST	START_DATE	END_DATE	PRIORITY	PRICE	CURR
1	35455	1	2020-06-14T00:00:00	2020-12-31T23:59:59	0	35.50	EUR
1	35455	2	2020-06-14T15:00:00	2020-06-14T18:30:00	1	25.45	EUR
1	35455	3	2020-06-15T00:00:00	2020-06-15T11:00:00	1	30.50	EUR
1	35455	4	2020-06-15T16:00:00	2020-12-31T23:59:59	1	38.95	EUR
    
    
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

Se incluyen 5 tests de ejemplo para validar distintos escenarios de fecha y hora:

-Test 1: 2020-06-14T10:00:00
-Test 2: 2020-06-14T16:00:00
-Test 3: 2020-06-14T21:00:00
-Test 4: 2020-06-15T10:00:00
-Test 5: 2020-06-16T21:00:00
