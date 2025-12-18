# Freelance Job Portal

A Java console application demonstrating Object-Oriented Programming principles.

## Project Structure

```
src/
├── User.java        # Base class for all users
├── Freelancer.java  # Freelancer (extends User)
├── Client.java      # Client/Employer (extends User)
├── Job.java         # Job posting entity
├── JobPortal.java   # Platform managing jobs and users
└── Main.java        # Application entry point
```

## Class Diagram

```
                    ┌─────────────┐
                    │    User     │
                    ├─────────────┤
                    │ - id        │
                    │ - name      │
                    │ - email     │
                    ├─────────────┤
                    │ + getId()   │
                    │ + getName() │
                    │ + getRole() │
                    │ + displayInfo() │
                    └──────┬──────┘
                           │
            ┌──────────────┴──────────────┐
            │                             │
            ▼                             ▼
    ┌───────────────┐             ┌───────────────┐
    │  Freelancer   │             │    Client     │
    ├───────────────┤             ├───────────────┤
    │ - skills      │             │ - company     │
    │ - salary      │             │ - postedJobs  │
    ├───────────────┤             ├───────────────┤
    │ + hasAnySkills()│           │ + postJob()   │
    │ + displayInfo() │           │ + displayInfo() │
    └───────────────┘             └───────────────┘
```

## OOP Principles Implemented

### 1. Encapsulation

All class fields are `private` with `public` getters and setters for controlled access.

```java
public class User {
    private String id;      // Hidden from outside
    private String name;
    private String email;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
```

**Validation example in Job.java:**
```java
public void setBudget(double budget) {
    if (budget < 0) {
        System.out.println("Budget cannot be negative");
        return;
    }
    this.budget = budget;
}
```

### 2. Inheritance

`Freelancer` and `Client` inherit common properties from `User` base class.

```java
public class Freelancer extends User {
    private String[] skills;

    public Freelancer(String id, String name, String email, String[] skills, double salary) {
        super(id, name, email);  // Call parent constructor
        this.skills = skills;
    }
}
```

### 3. Polymorphism

Same method call produces different behavior based on object type.

```java
List<User> users = new ArrayList<>();
users.add(new Freelancer(...));
users.add(new Client(...));

for (User user : users) {
    user.displayInfo();  // Different output for each type
    user.getRole();      // "Freelancer" or "Client"
}
```

**Method overriding:**
```java
// Freelancer.java
@Override
public void displayInfo() {
    System.out.println("Freelancer: " + getName());
    System.out.println("Skills: " + Arrays.toString(skills));
}

// Client.java
@Override
public void displayInfo() {
    System.out.println("Client: " + getName());
    System.out.println("Company: " + company);
}
```

### 4. Abstraction

Complex logic is hidden behind simple method interfaces.

```java
freelancer.hasAnySkills("React");   // returns true/false
client.postJob("j1", "Landing", 500);  // creates and returns Job
```

## Additional Features

| Feature | Description |
|---------|-------------|
| `equals()` / `hashCode()` | Object comparison by ID |
| `toString()` | Formatted object output |
| Null checks | Protection against null values |
| Validation | Data validation in setters |
| Composition | JobPortal contains List of Job and Freelancer |

## How to Run

```bash
javac -d out src/*.java
java -cp out Main
```

## Technologies

- Java 17+
- IntelliJ IDEA

## Author

Yernur Nassipkali