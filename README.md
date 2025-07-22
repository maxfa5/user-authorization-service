# User Authorization Service

## Описание

User Authorization Service — это REST API на Spring Boot для регистрации, аутентификации и авторизации пользователей с поддержкой JWT (JSON Web Token) и refresh токенов.  
Сервис реализует защищённые эндпоинты, роли пользователей, автоматическую генерацию и обновление access/refresh токенов.

---

## Основные возможности

- Регистрация и аутентификация пользователей
- Хеширование паролей (BCrypt)
- JWT access token для авторизации
- Refresh token для обновления access token без повторного ввода логина и пароля
- Роли пользователей (например, GUEST, ADMIN)
- Защищённые эндпоинты по ролям
- H2 in-memory база данных для тестирования
- Глобальная обработка ошибок

---

## Быстрый старт

### 1. Клонируйте репозиторий

```bash
git clone <repo-url>
cd user-authorization-service
```

### 2. Запустите приложение

```bash
./gradlew bootRun
```
или через IDE (класс `org.project.Application`)

### 3. Откройте H2 Console (для тестирования БД)

- [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: (оставьте пустым)

---

## Примеры запросов

### Регистрация

```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "newuser",
  "password": "password123",
  "email": "newuser@example.com",
  "role": "GUEST"
}
```

### Логин

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "newuser",
  "password": "password123"
}
```
**Ответ:**
```json
{
  "accessToken": "<JWT_ACCESS_TOKEN>",
  "refreshToken": "<REFRESH_TOKEN>"
}
```

### Обновление access token

```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "<REFRESH_TOKEN>"
}
```
**Ответ:**
```json
{
  "accessToken": "<NEW_JWT_ACCESS_TOKEN>",
  "refreshToken": "<REFRESH_TOKEN>"
}
```

### Доступ к защищённому эндпоинту

```http
GET /admin/users
Authorization: Bearer <JWT_ACCESS_TOKEN>
```

---

## Конфигурация

- Настройки в `src/main/resources/application.properties`
- JWT секрет и срок жизни refresh token можно задать через переменные:
  ```
  token.signing.key=your-very-secret-key-here
  jwt.refresh.expiration.ms=604800000
  ```

---

## Стек технологий

- Java 21+
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database
- Lombok
- JWT

---

## Лицензия

MIT или любая другая по вашему выбору.