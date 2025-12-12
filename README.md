# The Ultimate Survival Guide: UPC Web Apps & DDD Architecture

Yo, welcome to the **Backend Bible** for the *Desarrollo de Aplicaciones Web* course.

This isn't your average spaghetti code. We are building a **Modular Monolith** using **Domain-Driven Design (DDD)**.

Why?

Because we want that **20/20 on the final exam**, and because in the real world (and in this course), we don't just *"make it work"*—we make it **solid**. JAAAA, Facil es hacerlo, dificil es hacerlo bien. or not python user? /jk

This architecture splits our brain into specific **Bounded Contexts** (like Assets, Operations, etc.), but they all rely on one master toolkit: **The Shared Kernel**. Thanks for everything Github.

---

## 🧠 Backend vs. Frontend (The Philosophy)

Based on our handwritten notes, don't get it twisted.

DDD looks different depending on where you stand.

This diagram summarizes our entire mental model:

Important, take it easy with the Diagrams, use the damn diagram **trust me**

### Backend (The Source of Truth)

This is where we live. Here, **state is persistent**.

If the data says a bus has 40 seats, it has 40 seats, obviously.

We have strict logic and validation.

As the notes say: *"The brain is Shared"*.

### Frontend (The Projection)

This is just a reflection.

It handles **UI state** (spinners, colors) and *"pre-validations"* to be nice to the user, but the chicha drops in the Backend.

### Practical differences in DDD (Thanks for nothing Ernesto):

|Layer|In Backend (Node/Java/C#)|In Frontend (Vue/React/Angular)|
|---|---|---|
|Domain|Rich entities. Lots of logic and strict validations. State persists in DB.|Lightweight models. "Pre-validations" (for quick user feedback). State lives in memory/browser.|
|Application|Use Cases. Orchestrate DB transactions. (Ex: CreateOrder).|State Managers (Stores). (Ex: Pinia/Redux). Manage reactivity and call the API.|
|Infrastructure|ORM / DB Drivers. Connects with MySQL, Postgres, Redis.|HTTP/API Clients. Connects with Backend (Axios, Fetch) or LocalStorage.|
|Presentation (Important)|REST/GQL Controllers. Receive JSON and return JSON.|UI Components (.vue, .jsx files). Handle user events and **render** data.|


---

## 🌳 The Starter Pack (File Treeeee)

Before we start coding like maniacs, this is what the base project looks like.

These are the files you **do not touch** (unless you want to break everything) and the ones you **must configure**.

```Html
📂 ACME.LearningCenterPlatform.API
├── 📂 Properties
│   └── 📄 launchSettings.json <-- 🚦 Localhost & Ports live here [cite: 1]
├── 📂 Shared <-- 🧠 The Brain (Do not touch!) [cite: 2]
│   ├── 📂 Domain
│   ├── 📂 Application
│   └── 📂 Infrastructure
├── 📄 appsettings.json <-- 🔑 Secrets & DB Connection [cite: 2]
└── 📄 Program.cs <-- 🔌 The Main Switch (Wiring) [cite: 2]

```

---

## 🛠️ The Shared Kernel: "The Toolkit"

The **Shared** folder is the backbone. It is divided into **3 layers**.

Here is how they actually work.

---

### 1. Domain Layer ("Da rules")

```Html
📂 Shared
└── 📂 Domain
    └── 📂 Repositories
        └── 📄 IBaseRepository.cs

```

**Concept:**

*"Operations without logic"*.

This is where we define **Contracts (Interfaces)**.

We say *"We will save a bus,"* but we don't say **how**.

It's pure abstraction, yes. Like rules. geez...

**Key Files:**

`IBaseRepository`, `IUnitOfWork`.

**Note:**

It's just `void Validate()`. Empty contracts. Interfaz? method with nothin??? Search in google please.

---

### 2. Application Layer ("The Bureaucracy")

```Html
📂 Shared
└── 📂 Application
    └── 📂 Internal
        └── 📂 EventHandlers

```

**Concept:**

*"Da Rules"*.

This is an empty context used mainly for **reactions (Events)**.

It's the bouncer.

It doesn't hold business logic (that's inside the Entities), but it handles notifications and activity.

**Role:**

Apply direct in BD... Inject. 

---

### 3. Infrastructure Layer ("The Logic")

```Html
📂 Shared
└── 📂 Infrastructure
    ├── 📂 Persistence
    ├── 📂 Interfaces (ASP)
    └── 📂 Mediator

```

**Concept:**

*"We have to do... Ok, the correct way is..."*.

This is where the magic (and the dirty work) happens.

It has three heads:

- **A. Persistence (The Bookkeeper):**

Handles the Database Entity Framework. Does the SQL work.

Magic: It automatically converts our C# code (`StudentId`) to MySQL format (`student_id`) using snake_case extensions.

- **B. ASP (The Configuration):**

Handles the "Interreg" / Configuration.

Forces our URLs to look professional (**kebab-case**) so we don't get points deducted.

- **C. Mediator (The Switchboard):**

Pattern: `Class A -> Mediator -> Class B`.

It is a typical software pattern. Take it easy, search in google.

It decouples everything. Instead of classes talking directly, they send messages through here.

---

## ⚙️ Configuration: Where to Click

Okay, so where do we actually set up the project to run on our machine?

---

### 1. MySQL Connection 🛢️ Becouse they want, I personally prefered PostgreSQL, because...it is open source, and free. I like it.

You define where the database lives here.

**Location:**

`appsettings.json`

```Html
📂 ACME.LearningCenterPlatform.API
└── 📄 appsettings.json <-- EDIT THIS

```

**What to do:**

Change `ConnectionStrings:DefaultConnection`.

Make sure `user=root` and password match your MySQL Workbench.

---

### 2. Localhost & Ports 🚦

This controls the "Play" button in Visual Studio/Rider.

**Location:**

`Properties/launchSettings.json`

```Html
📂 Properties
└── 📄 launchSettings.json <-- HERE

```

**What to do:**

Look for `applicationUrl`.

This is where `localhost:5000` or `localhost:8080` is defined.

This is also where the browser knows to launch **Swagger** automatically (`"launchUrl": "swagger"`).

---

### 3. The Endpoints (Where API meets World) 🔌

You might be asking, *"Where are the endpoints generated?"*

They are defined in your **Controllers** (inside your specific Bounded Contexts, e.g., `Assets/Interfaces/REST`), but they are **Registered and Mapped here**:

**Location:**

`Program.cs`

```Html
📂 ACME.LearningCenterPlatform.API
└── 📄 Program.cs <-- THE BOSS

```

**What happens here:**

- **Wiring:**

We perform Dependency Injection (`builder.Services.AddScoped...`).

- **Mapping:**

`app.MapControllers()` tells the app to scan our code and find all the `[HttpPost]` and `[HttpGet]` tags.

- **Swagger:**

`builder.Services.AddSwaggerGen()` builds the UI documentation so we can test without a Frontend.

- **DB Creation:**

`context.Database.EnsureCreated()` creates the tables for us automatically (**lazy dev life hack**).

---



## 🚨 Emergency Kit JAJAJAJ, maybe?

Use this with responsability.

If you need to create a new Bounded Context (like `Inventory`, `Sales`, `Booking`) from scratch based on a PDF requirement, just **Copy & Paste** this prompt into your favorite AI (Copilot/ChatGPT), paste your exam constraints below it, and watch the magic happen.

---

### 📜 The Prompt to Rule Them All

```plaintext
Act like a Senior .NET Software Architect expert in Domain-Driven Design (DDD) and ASP.NET Core.

CONTEXT & ARCHITECTURE:
- Project: ASP.NET Core Web API (.NET 9).
- Architecture: Modular Monolith with DDD Layers (Domain, Application, Infrastructure).
- Base Logic: I have a 'Shared Kernel' that provides IBaseRepository, IUnitOfWork.

REQUIREMENTS FOR YOU TO IMPLEMENT:
1. **Structure:** Create the folder structure: Domain/Model/Aggregates, Domain/Repositories, Application/Services, Infrastructure/Persistence.
2. **Domain:** Create the Entities (Aggregates) with strict validations in the constructor.
3. **Infrastructure:** Use Fluent API (IEntityTypeConfiguration) for DB mapping.
4. **Interfaces:** Use REST Controllers with 'kebab-case' routes. Use the 'Assets' pattern.
5. **Naming:** PascalCase for C# names, snake_case for Database (handled by Shared).
6. **Inputs:** I will provide the list of Attributes, Business Rules, and Relationships.

MY SPECIFIC EXAM REQUIREMENTS (DATA):
[!!! PASTE YOUR EXAM TEXT / PDF CONTENT HERE !!!]
[Example: Create a 'Bus' entity with Plate, Seats... Rules: Plate must be unique.]

OUTPUT:
Generate the full C# code for the Files required to implement this Bounded Context.
```

---

**Now you are invincible. Go get that 20. 👌**
