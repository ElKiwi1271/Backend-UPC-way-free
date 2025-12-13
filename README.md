# The Ultimate Survival Guide: UPC Java Backend & DDD Architecture

Yo, welcome to the **Backend Bible** for the Open Source / Web Applications course.

This isn't your average spaghetti code. We are building a **Modular Monolith** using **Domain-Driven Design (DDD)**.


Because we want that **20/20 on the final exam**, and because in the real world (and with this professor), we don't just *"make it work"*—we make it **solid**. LMAO, "Facil es hacerlo, dificil es hacerlo bien".

This architecture splits our brain into specific **Bounded Contexts** (like Profiles, Learning, Communication), but they all rely on one master toolkit: **The Shared Kernel**. Thanks for everything, GitHub.

---

## 🧠 Backend vs. Frontend (The Philosophy)
Based on our handwritten notes, don't get it twisted.

DDD looks different depending on where you stand.

### Backend (The Source of Truth)
This is where we live now (Java). Here, **state is persistent**.
If the database says the student has a grade of 15, they have a 15. Period.
We have strict logic and hard validations.
As the notes say: "The brain is Shared (Shared Kernel)".

### Frontend (The Projection)
This is just a reflection. (Probably your Vue.js with Pinia).
It handles UI state (spinners, pretty colors) and "pre-validations" to be nice to the user, but the real messy stuff happens in the Backend.

### Practical Differences (The Truth Table)

|Layer|In Backend (Node/Java/C#)|In Frontend (Vue/React/Angular)|
|---|---|---|
|Domain|Rich entities. Lots of logic and strict validations. State persists in DB.|Lightweight models. "Pre-validations" (for quick user feedback). State lives in memory/browser.|
|Application|Use Cases. Orchestrate DB transactions. (Ex: CreateOrder).|State Managers (Stores). (Ex: Pinia/Redux). Manage reactivity and call the API.|
|Infrastructure|ORM / DB Drivers. Connects with MySQL, Postgres, Redis.|HTTP/API Clients. Connects with Backend (Axios, Fetch) or LocalStorage.|
|Presentation (Important)|REST/GQL Controllers. Receive JSON and return JSON.|UI Components (.vue, .jsx files). Handle user events and **render** data.|


---

## 🌳 The Starter Pack (File Tree)
Before you start coding like a maniac, this is what the base project looks like in Java.
These are the files you do **not touch** (unless you want to break everything) and the ones you **must configure**.

```plaintext
📂 src
├── 📂 main
│   ├── 📂 java
│   │   └── 📂 com.upc.open
│   │                     ├── 📂 shared <-- 🧠 The Brain (Do not touch! Radioactive zone)
│   │                     │   ├── 📂 domain
│   │                     │   └── 📂 infrastructure
│   │                     └── 📄 BackendUpcOpenApplication.java <-- 🔌 The Main Switch (Wiring)
│   └── 📂 resources
│       ├── 📂 static
│       ├── 📂 templates
│       └── 📄 application.properties <-- 🔑 The Holy Grail of Config
└── 📄 pom.xml <-- 📦 Dependencies (If this fails, you cry)
```

---

## 🕵️‍♂️ The Login Mystery: Why is it asking for a Password?
You ran into this, right? You saw a wild login screen and then an empty Swagger saying "No operations defined".
This happens because Spring Security is paranoid by default. It blocks everything.
But chill, Spring gives you a temporary password every time you start the app.

**Where is the password?**
Look at the damn CONSOLE LOG  when the app starts. Looks like:
```

2025-12-12T19:44:58.490-05:00  WARN 14600 --- [BackendUPCOpen] [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: b9d41ac8-16ca-4b05-8039-a48b3a894f0d

This generated password is for development use only. Your security configuration must be updated before running your application in production.


```
Heads up: Every time you restart the server, that password CHANGES. If you want to remove this, configure `SecurityConfig.java`. `user:user`

---

## ⚙️ Configuration: Where to Click
### 1. pom.xml (The Heart of Maven) 📦
This is where you tell Java which libraries to download.
Modify only to add OpenAPI (Swagger), MySQL Driver, or Lombok.
If you see red in your code, reload Maven: Right-click -> Maven -> Reload Project.

### 2. application.properties (Where the Magic Lives) 🛢️
This file in `src/main/resources` controls everything.

**MySQL Connection:**
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

**Localhost & Ports:**
```
server.port=8080
```
Swagger lives at: `http://localhost:8080/swagger-ui.html`

**JPA & Hibernate:**
```
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚨 Emergency Kit JAJAJAJA  oh no again 
Use this responsibly (o no, quien sabe, no me es relevante).
The promptitacion

```plaintextAct as a Senior Software Architect expert in Java 21+, Spring Boot 3.x, and Domain-Driven Design (DDD).

I need you to generate the code for a specific Bounded Context based on an exam requirement.

### 1. ARCHITECTURE & STRUCTURE RULES (STRICT)
- **Architecture:** Layered DDD (Domain, Application, Interfaces, Infrastructure).
- **Format:** Modular Monolith.
- **DTOs:** Use Java `records` for all Input/Output DTOs (Immutable).
- **ORM:** Spring Data JPA.
- **Boilerplate:** Use Lombok (`@Data`, `@Getter`, `@NoArgsConstructor`, `@AllArgsConstructor`) everywhere applicable.

### 2. CODING STANDARDS
- **Database:** MySQL. Use `snake_case` for all table and column names (`@Column(name="...")`).
- **URLs:** Kebab-case for endpoints (e.g., `/api/v1/inspection-records`).
- **Auditing:** All Aggregates MUST include `createdAt` and `updatedAt` using Spring Auditing (`@CreatedDate`, `@LastModifiedDate`).
- **Value Objects:** Business identifiers (like UUIDs/Codes) MUST be implemented as **@Embeddable Value Objects**, not simple Strings.

### 3. CRITICAL IMPLEMENTATION DETAILS
- **Repository:** Extend `JpaRepository`.
- **Controller:** Return `ResponseEntity<ResourceDTO>`. Use `HttpStatus.CREATED` for POST.
- **Events:** If the requirements mention "emitting an event" or "side effects" (like creating a task when a value is out of range), implement a Domain Event using `ApplicationEventPublisher`.

### 4. YOUR TASK
Generate the following files for the requested Bounded Context:
1.  **The Aggregate Root Entity** (including the Embedded ID class and Audit fields).
2.  **The Repository Interface**.
3.  **The Command Service / Interface** (Business Logic & Event Publishing).
4.  **The Controller** (REST Endpoint).
5.  **The DTO Records** (CreateResource, ResourceResponse).

---
### 5. INPUT DATA (FROM EXAM)

**Project Name:** [NOMBRE DEL PROYECTO, EJ: si729ebu20211000]
**Root Package:** [PAQUETE RAIZ, EJ: com.plassertheurer.platform.u20211000]

**SCENARIO & RULES:**
[!!! PEGA AQUÍ TODO EL TEXTO DEL PDF DEL ENUNCIADO, REGLAS DE NEGOCIO Y CAMPOS !!!]
```

now u are god, or something similar. Any question about DDD check the brunch App Web, its better because i need my final exam to approve XD
