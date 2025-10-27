# Order Management System

A comprehensive Spring Boot application for managing orders, contracts, customers, and sellable items (products and services) based on a UML class diagram specification.

## System Overview

This Order Management System implements a complete business domain model with the following core entities:

### Core Entities
- **Customer** - Companies or individuals who place orders and enter contracts
- **Order** - Customer requests for products/services, optionally linked to contracts
- **Contract** - Formal agreements between parties with specific terms
- **SellableItem** (Abstract) - Base class for items that can be sold
  - **Product** - Physical items with monetary value
  - **Service** - Intangible offerings with operational status
- **UnitOfMeasure** - Measurement units for quantities
- **ContractType** - Categories of contracts (Standard, Premium, etc.)

### Supporting Entities
- **OrderLine** - Individual items within an order
- **ContractLine** - Individual items within a contract

## Features

### Customer Management
- Create, read, update, delete customers
- Support for multiple currencies (USD, EUR, GBP)
- Customer order history tracking

### Order Management
- Full CRUD operations for orders
- Order line management with quantities and units
- Optional contract association
- Order total calculation
- Customer-specific order filtering

### Contract Management
- Contract lifecycle management (Active/Down status)
- Contract line management
- Contract type categorization
- Status activation/deactivation

### Product & Service Management
- Product catalog with pricing
- Service management with operational status
- Support for both tangible and intangible offerings
- Status management for services

### Unit of Measure Management
- Comprehensive unit system (pieces, kg, meters, hours, etc.)
- Symbol-based unit identification
- Flexible quantity management

## REST API Endpoints

### Customer Management
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `GET /api/customers/currency/{currency}` - Get customers by currency
- `GET /api/customers/with-orders` - Get customers with their orders
- `POST /api/customers` - Create new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

### Order Management
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/customer/{customerId}` - Get orders by customer
- `GET /api/orders/contract/{contractId}` - Get orders by contract
- `GET /api/orders/without-contract` - Get orders without contracts
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}` - Update order
- `DELETE /api/orders/{id}` - Delete order
- `GET /api/orders/{id}/total` - Get order total value
- `POST /api/orders/{orderId}/lines` - Add order line
- `GET /api/orders/{orderId}/lines` - Get order lines

### Contract Management
- `GET /api/contracts` - Get all contracts
- `GET /api/contracts/{id}` - Get contract by ID
- `GET /api/contracts/status/{status}` - Get contracts by status
- `GET /api/contracts/type/{contractTypeId}` - Get contracts by type
- `GET /api/contracts/active` - Get active contracts
- `POST /api/contracts` - Create new contract
- `PUT /api/contracts/{id}` - Update contract
- `DELETE /api/contracts/{id}` - Delete contract
- `PATCH /api/contracts/{id}/activate` - Activate contract
- `PATCH /api/contracts/{id}/deactivate` - Deactivate contract
- `POST /api/contracts/{contractId}/lines` - Add contract line
- `GET /api/contracts/{contractId}/lines` - Get contract lines

### Product & Service Management
- `GET /api/items` - Get all sellable items
- `GET /api/items/{id}` - Get item by ID
- `GET /api/items/type/{itemType}` - Get items by type
- `GET /api/items/products` - Get all products
- `GET /api/items/services` - Get all services
- `GET /api/items/services/active` - Get active services
- `POST /api/items/products` - Create new product
- `POST /api/items/services` - Create new service
- `PUT /api/items/{id}` - Update item
- `DELETE /api/items/{id}` - Delete item
- `PATCH /api/items/services/{id}/activate` - Activate service
- `PATCH /api/items/services/{id}/deactivate` - Deactivate service

### System Management
- `GET /api/contract-types` - Get all contract types
- `GET /api/contract-types/{id}` - Get contract type by ID
- `GET /api/units` - Get all units of measure
- `GET /api/units/{id}` - Get unit by ID
- `GET /api/units/symbol/{symbol}` - Get unit by symbol
- `GET /api/status` - Get system status
- `POST /api/initialize-all` - Initialize sample data

## Sample Data

The application comes with comprehensive sample data:

### Customers (5)
- Acme Corporation (USD)
- Tech Solutions Ltd (EUR)
- Global Industries (USD)
- Startup Ventures (GBP)
- Enterprise Systems (USD)

### Products (4)
- Laptop Computer ($1,200.00)
- Wireless Mouse ($25.50)
- Mechanical Keyboard ($150.00)
- Monitor 24" ($300.00)

### Services (4)
- Technical Support (Active)
- Software Installation (Active)
- Hardware Maintenance (Down)
- Data Recovery (Active)

### Contract Types (5)
- Standard Contract (Customer/Seller)
- Premium Contract (Customer/Seller)
- Service Agreement (Service Provider)
- Maintenance Contract (Service Provider)
- Support Agreement (Technical Support)

### Units of Measure (8)
- Piece (pcs), Kilogram (kg), Meter (m), Liter (L)
- Hour (hr), Day (day), Box (box), Package (pkg)

### Contracts (5)
- Annual Support Contract (Active)
- Premium Service Agreement (Active)
- Maintenance Contract (Active)
- Technical Support Agreement (Down)
- Standard Service Contract (Active)

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)

### Running the Application

1. **Using Maven Wrapper (if available):**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Using Maven (if installed):**
   ```bash
   mvn spring-boot:run
   ```

3. **Using IDE:**
   - Import the project as a Maven project
   - Run `OrderManagementSystemApplication.java`

### Access the Application
- **Base URL:** http://localhost:8080
- **API Base:** http://localhost:8080/api

## Example API Calls

### Create a new customer
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"name": "New Company", "currency": "USD"}'
```

### Create a new order
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"name": "Q2 Equipment Order", "customerId": "C001", "contractId": "CON001"}'
```

### Add items to an order
```bash
curl -X POST http://localhost:8080/api/orders/O001/lines \
  -H "Content-Type: application/json" \
  -d '{"itemId": "P001", "unitId": "U001", "quantity": 5.0}'
```

### Get order total
```bash
curl http://localhost:8080/api/orders/O001/total
```

### Activate a service
```bash
curl -X PATCH http://localhost:8080/api/items/services/S003/activate
```

### Get system status
```bash
curl http://localhost:8080/api/status
```

## Project Structure

```
src/main/java/com/example/OrderManagementSystem/
├── controller/
│   ├── OrderController.java              # Order management endpoints
│   ├── ContractController.java           # Contract management endpoints
│   ├── CustomerController.java           # Customer management endpoints
│   ├── SellableItemController.java       # Product/Service endpoints
│   └── OrderManagementController.java   # System-wide endpoints
├── model/
│   ├── SellableItem.java                # Abstract base class
│   ├── Product.java                      # Product implementation
│   ├── Service.java                      # Service implementation
│   ├── UnitOfMeasure.java                # Unit of measure
│   ├── Customer.java                     # Customer entity
│   ├── ContractType.java                 # Contract type
│   ├── Contract.java                     # Contract entity
│   ├── ContractLine.java                 # Contract line item
│   ├── Order.java                        # Order entity
│   └── OrderLine.java                    # Order line item
├── repository/
│   ├── SellableItemRepository.java       # Item data access
│   ├── UnitOfMeasureRepository.java      # Unit data access
│   ├── CustomerRepository.java           # Customer data access
│   ├── ContractTypeRepository.java       # Contract type data access
│   ├── ContractRepository.java           # Contract data access
│   ├── ContractLineRepository.java       # Contract line data access
│   ├── OrderRepository.java              # Order data access
│   └── OrderLineRepository.java          # Order line data access
├── service/
│   ├── OrderService.java                 # Order business logic
│   ├── ContractService.java              # Contract business logic
│   ├── CustomerService.java              # Customer business logic
│   └── SellableItemService.java          # Item business logic
└── OrderManagementSystemApplication.java  # Main Spring Boot application
```

## Configuration

The application is configured to run on:
- **Port:** 8080
- **Context Path:** /
- **CORS:** Enabled for all origins
- **Logging:** DEBUG level for application and web layers
- **JSON:** Pretty-printed output with null exclusion
- **Default Currency:** USD
- **Default Contract Status:** Active
- **Default Service Status:** Active

## Business Logic Features

### Order Management
- Orders can be associated with contracts (optional)
- Automatic order total calculation based on product values
- Support for multiple order lines per order
- Customer order history tracking

### Contract Management
- Contract lifecycle management with status control
- Contract line management for detailed terms
- Contract type categorization for different business models
- Active/inactive contract filtering

### Product & Service Management
- Unified interface for both products and services
- Product pricing and value calculation
- Service status management (Active/Down)
- Type-based filtering and management

### Customer Management
- Multi-currency support
- Customer order and contract association
- Comprehensive customer data management

This Order Management System provides a complete solution for managing business relationships, orders, contracts, and inventory in a structured, RESTful manner.