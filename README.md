# OrbiTrack Platform

> Plataforma inteligente de gestión logística para eCommerce · DSY1106

## Estructura

| Módulo | Descripción |
|--------|-------------|
| `sl-web` | Frontend React + Vite · empaquetado NPM |
| `sl-gateway` | Backend For Frontend · Spring Boot |
| `sl-inventory-ms` | Microservicio de inventario |
| `sl-orders-ms` | Microservicio de pedidos |

## Levantar el proyecto

```bash
# Frontend
cd sl-web && npm install && npm run dev

# BFF
cd sl-gateway && mvn spring-boot:run

# Inventario
cd sl-inventory-ms && mvn spring-boot:run

# Pedidos
cd sl-orders-ms && mvn spring-boot:run
```

## Stack

- React 18 · Vite · Tailwind CSS
- Spring Boot 3 · Java 25 · Maven
- Patrones: Repository · Factory Method · Observer
