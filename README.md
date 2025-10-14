# Spring Boot Interview API

This is a lightweight Spring Boot project designed for use in technical interviews. It simulates a simple RESTful API for managing inventory items.

## Features

- **GET /api/items** - Retrieve all items or filter by name/price
- **GET /api/items/{id}** - Retrieve a specific item by ID
- **POST /api/item** - Create a new item
- **PUT /api/items/{id}** - Update an existing item
- **DELETE /api/items/{id}** - Delete an item
- Validation with proper HTTP status codes
- Global exception handling
- Filtering capabilities

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build and Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/tharover/springboot-interview-api.git
   cd springboot-interview-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Documentation

### Get All Items

```http
GET /api/items
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 1299.99,
    "quantity": 10
  }
]
```

### Filter Items by Name

```http
GET /api/items?name=Laptop
```

**Response:** `200 OK` - Returns items matching the name filter

### Filter Items by Price Range

```http
GET /api/items?minPrice=50&maxPrice=100
```

**Response:** `200 OK` - Returns items within the price range

### Get Item by ID

```http
GET /api/items/{id}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 1299.99,
  "quantity": 10
}
```

**Error Response:** `404 NOT FOUND` if item doesn't exist

### Create New Item

```http
POST /api/item
Content-Type: application/json

{
  "name": "Monitor",
  "description": "4K Monitor",
  "price": 399.99,
  "quantity": 15
}
```

**Response:** `201 CREATED`
```json
{
  "id": 4,
  "name": "Monitor",
  "description": "4K Monitor",
  "price": 399.99,
  "quantity": 15
}
```

**Validation Errors:** `400 BAD REQUEST`
```json
{
  "status": 400,
  "errors": {
    "name": "Name is required",
    "price": "Price must be positive"
  },
  "timestamp": "2025-10-14T12:31:44"
}
```

### Update Item

```http
PUT /api/items/{id}
Content-Type: application/json

{
  "name": "Updated Laptop",
  "description": "Updated description",
  "price": 1499.99,
  "quantity": 20
}
```

**Response:** `200 OK` - Returns updated item

**Error Response:** `404 NOT FOUND` if item doesn't exist

### Delete Item

```http
DELETE /api/items/{id}
```

**Response:** `204 NO CONTENT`

**Error Response:** `404 NOT FOUND` if item doesn't exist

## Error Handling

The API uses standard HTTP status codes:

- `200 OK` - Request successful
- `201 CREATED` - Resource created successfully
- `204 NO CONTENT` - Resource deleted successfully
- `400 BAD REQUEST` - Invalid request or validation errors
- `404 NOT FOUND` - Resource not found
- `500 INTERNAL SERVER ERROR` - Server error

## Testing

Run the test suite:

```bash
mvn test
```

## Interview Discussion Topics

This project is designed to facilitate discussions on:

1. **REST API Design** - RESTful principles, endpoint naming conventions
2. **Validation** - Bean validation, custom validators
3. **Error Handling** - Global exception handlers, appropriate HTTP status codes
4. **Filtering** - Query parameters, dynamic filtering
5. **Testing** - Unit tests, integration tests, MockMvc
6. **Future Enhancements**:
   - Database integration (JPA/Hibernate)
   - Authentication & Authorization
   - Pagination and sorting
   - API versioning
   - Swagger/OpenAPI documentation
   - Caching strategies
   - Rate limiting
