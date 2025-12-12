# README: DDD for Frontend & Backend

## 🧠 Quick DDD for Frontend
Domain-Driven Design (DDD) was taught terribly and way too early at UPC (at least in my case), rushed and using Java which I didn’t even know. In these courses we use TypeScript, JavaScript, frameworks, GitHub, APIs, deployments, Java and C#, yet we only knew C++ and PSeInt XD. So this will be a general guide for the backend — if you don’t get the frontend, you might want to drop the course. The short explanation of DDD is: explain it the way it’s written in the main text.

- **Backend:** Rich entities, strict rules, persistent state in DB.
- **Frontend:** Lightweight models, quick validations for UX, state in memory (Pinia, Redux).

**Layer Comparison:**
| Layer          | Backend (.NET, Node)           | Frontend (Vue, Angular) |
|---------------|--------------------------------|---------------------------|
| Domain        | Entities + logic + DB         | Simple models            |
| Application   | Use cases                     | State managers (Pinia)   |
| Infrastructure| ORM, DB drivers               | Axios, Fetch             |
| Presentation  | REST Controllers              | UI Components            |

If you’re wondering what something is, those names, whether they’re folders or not — google it LOL. I’m not writing this guide for people to blindly copy-paste.

---

## 🔍 Angular vs Vue
Angular (the GOAT): Full framework, TypeScript mandatory (because of the rubric), rigid architecture (great for big projects). Vue (the little GOAT): Flexible, easy learning curve, perfect for medium or fast projects. Both use **components**, but Angular feels “corporate,” while Vue is “minimalist.”

In Angular we use **TypeScript** (zzz), and in Vue we use **JavaScript** (classic). If you hate TypeScript or JS, sorry — rubric wins.

---

## 🌐 API Concepts
- **API:** The bridge between frontend and backend, exchanging JSON.
- **Fake API:** Local simulation (e.g., `json-server`) for testing without a real backend.
- **RESTful API:** Follows REST principles: resources, HTTP methods (`GET`, `POST`), clean URLs.

**Endpoints:** Backend “doors”:
```
GET /api/buses
POST /api/buses
```
Fake API equivalent:
```
http://localhost:3000/buses
```

---

## 🏗️ Frameworks & Patterns
- **Backend (.NET and Spring boot):** Mediator, Repository, Unit of Work → decoupling and order.
- **Frontend (.Net):** State Management (Redux, Pinia), Component Pattern → reactivity and modularity.

---

## ⚙️ Backend DDD Essentials
Here we’re using a system called a **Modular Monolith with layered DDD**. It’s a middle ground and the best practical approach at UPC — stick with it. It’s not ideal for tiny projects, but it gives a solid foundation lol; it’s excellent. Also, it isn’t that complicated once you understand the basics. Just remember, Swagger is God.

- **Architecture:** Modular Monolith with layers:
  - **Domain:** Interfaces (`IBaseRepository`, `IUnitOfWork`).
  - **Application:** Orchestration (events, use cases).
  - **Infrastructure:** Persistence (Entity Framework), ASP configuration, Mediator.
- **Localhost:** Configured in `launchSettings.json` (e.g., `localhost:5000`).
- **Swagger:** Generates interactive documentation to test endpoints without frontend.
  - Easy: `AddSwaggerGen()` and open `/swagger`.

---

## 🚀 Deploy to Web with MySQL
For now we’ll work backend and database together because we haven’t been taught about servers yet — just understand these concepts. That’s all you need to say.

1. **Set DB connection:** In `appsettings.json` → `ConnectionStrings:DefaultConnection`.
2. **Publish project:**
   ```bash
   # Corrected command for publishing
   dotnet publish -c Release
   ```
   Deploy to IIS, Azure, or Docker (or pray to the DevOps gods).
3. **Database:** Use `EnsureCreated()` for auto table generation.
4. **Frontend:** Build with `npm run build` and serve via Nginx or similar idk.

---

Oh — and yes, macOS sucks. 
