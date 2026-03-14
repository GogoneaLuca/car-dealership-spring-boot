# EliteAuto - Dealership Management System 

A full-stack, enterprise-level web application developed for comprehensive car dealership management, handling vehicle inventory, employee administration, and financial transaction processing.

##  Tech Stack
* **Backend:** Java 21, Spring Boot 3 (Spring Web, Spring Data JPA)
* **Database:** Microsoft SQL Server (Hosted in Microsoft Azure Cloud)
* **Frontend:** Thymeleaf, Bootstrap 5, HTML5/CSS3 (Modern UI/UX design)
* **Architecture:** MVC (Model-View-Controller)

##  Key Features & Architectural Decisions
* **Hybrid Data Architecture:** Utilized ORM (Spring Data JPA / Hibernate) for standard CRUD operations to ensure rapid development and built-in protection against SQL Injection.
* **Native SQL for Analytics:** Implemented business intelligence reporting using complex Native SQL queries (multiple JOINs, nested subqueries, GROUP BY, HAVING) to extract performance metrics (e.g., Premium Brands, Top Sales Agents, VIP Clients).
* **Secure Team Management:** Comprehensive personnel administration system, protected by session validations at the Controller level.
* **Transaction Processing:** Robust transactional logic for associating vehicles, clients, and sales agents, automatically updating live stock availability.

##  How to Run Locally
*Note: In production, the database was hosted on a Microsoft Azure server. To run this project locally, follow these steps:*

1. Clone this repository.
2. Execute the `export_baza_date.sql` script (included in the project root) in your local SQL Server instance to generate the schema and mock data.
3. Update the `application.properties` file with your local database credentials (username and password).
4. Run the application using `mvn spring-boot:run` and access `http://localhost:8080` in your browser.
