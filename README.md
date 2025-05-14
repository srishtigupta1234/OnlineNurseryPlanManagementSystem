# 🌿 Online Plant Nursery Management System

This is a backend application built using **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **Swagger**. It enables efficient management of customers, orders, plants, seeds, and planters in an online nursery store.

---

## 📌 Features

- 🔐 Admin and Customer login with session management
- 🧑‍🌾 CRUD operations for Plants, Seeds, and Planters
- 🛒 Order placement and viewing for customers
- 👨‍💼 Admin control for approving and managing entities
- 📄 API documentation using Swagger UI

---

## 💻 Technologies Used

| Technology       | Purpose                          |
|------------------|----------------------------------|
| Java             | Backend Language                 |
| Spring Boot      | Application Framework            |
| Spring Data JPA  | ORM and DB operations            |
| Hibernate        | ORM Implementation               |
| MySQL / H2       | Database                         |
| Swagger UI       | API Documentation                |
| Maven            | Dependency Management            |
| STS / Eclipse    | Development Environment          |

---

## 📁 Project Structure

src/
└── main/
├── java/
│ └── com/masai/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── service/
│ ├── exceptions/
│ └── OnlinePlantNurseryApplication.java
└── resources/
├── application.properties
└── data.sql / schema.sql (optional)

## 🔧 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/online-plant-nursery.git
   
2. Import in STS/Eclipse as a Maven project

3. Update database settings in application.properties:
   * spring.datasource.url=jdbc:mysql://localhost:3306/nurserydb
   * spring.datasource.username=root
   * spring.datasource.password=yourpassword
   * spring.jpa.hibernate.ddl-auto=update
   
4. Run the Application using OnlinePlantNurseryApplication.java

5. Access Swagger Docs at:

   --http://localhost:8088/swagger-ui/index.html
   
## 🧪 Sample API Endpoints
- Method	Endpoint	Access	Description
- POST	/customer/register	Public	Register new customer
- POST	/admin/register	Public	Register new admin
- GET	/plant/all	Authenticated	View all plants
- POST	/order/place	Customer	Place an order
- PUT	/plant/update/{id}	Admin	Update plant details


