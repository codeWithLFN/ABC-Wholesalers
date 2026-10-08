# ABC Wholesalers

ABC Wholesalers is a Jakarta EE enterprise web application built as a learning project. It demonstrates a typical enterprise application structure using EJB session beans, JPA entities, servlets, JSP pages, interceptors, and JMS messaging.

The application supports customer login, item retrieval, shopping-cart operations, checkout processing, interceptor-based price adjustments, and publishing recently bought items to a JMS topic.

## Technologies

- Java
- Jakarta EE
- Enterprise JavaBeans (EJB)
- Jakarta Persistence (JPA)
- Jakarta Servlets
- JSP
- Jakarta Messaging (JMS)
- Message-Driven Beans (MDB)
- GlassFish Server
- Apache NetBeans
- Java DB / Derby database

## Project Structure

```text
ABC-Wholesalers
│
├── ABCWholesalers-ejb
│   └── src/java/za/tut
│       ├── Entity
│       │   ├── Customer.java
│       │   └── Item.java
│       │
│       ├── Interceptor
│       │   ├── ShoppingInterceptor.java
│       │   └── ReplaceItemInterceptor.java
│       │
│       └── session
│           ├── CustomerService.java
│           ├── CustomerBean.java
│           ├── ShoppingCartService.java
│           ├── ShoppingCartBean.java
│           └── RecentlyBoughtItemMDB.java
│
├── ABCWholesalers-war
│   └── src/java/za/tut
│       ├── CustomerServlet.java
│       └── ShoppingServlet.java
│
├── build.xml
└── README.md
```

## Main Features

- Customer login validation using email and password
- Customer storage through a stateless session bean
- Item lookup and item listing
- Stateful shopping cart management
- Add items to a cart
- Remove or replace items in a cart
- Checkout cart items
- Interceptor-based item-price changes
- JMS topic messaging for recently bought items
- Message-driven bean that displays recently bought item messages in the GlassFish server console

## Application Architecture

```text
JSP Page
   ↓
Servlet
   ↓
EJB Session Bean
   ↓
JPA Entity / Database
```

Shopping checkout messaging flow:

```text
ShoppingServlet
   ↓
ShoppingCartBean
   ↓
JMS Topic: Jms/recentBoughtItems
   ↓
RecentlyBoughtItemMDB
   ↓
GlassFish Server Console
```

## EJB Module

The EJB module contains the business logic of the application.

### Customer Service

`CustomerService` is a local EJB business interface.

Main operations include:

```java
void storeCustomer(Customer customer);

Customer validateLogon(String email, String password);

Item findItem(int itemID);

List<Item> getAllItems();
```

`CustomerBean` is a stateless session bean responsible for customer and item-related operations.

### Shopping Cart Service

`ShoppingCartService` is a local EJB business interface used for shopping-cart operations.

Main operations include:

```java
void addToCart(Item item);

List<Item> checkout();

void replaceItem(Item item);
```

`ShoppingCartBean` is a stateful session bean because a shopping cart needs to keep its state while a customer adds, removes, or checks out items.

## Entities

### Customer

The `Customer` entity represents a customer in the database.

Typical fields include:

- Customer ID
- Email
- Password
- User type

### Item

The `Item` entity represents a product sold by ABC Wholesalers.

Typical fields include:

- Item ID
- Item name
- Item type
- Quantity
- Price

## Interceptors

The application includes reusable interceptors for shopping-cart business rules.

### ShoppingInterceptor

`ShoppingInterceptor` intercepts the `addToCart(Item item)` method.

It adds a levy to the item price before the item is added to the cart.

```text
Item price
   ↓
ShoppingInterceptor adds levy
   ↓
Item is added to cart
```

### ReplaceItemInterceptor

`ReplaceItemInterceptor` intercepts the `replaceItem(Item item)` method.

It subtracts R1.14 from the item price before the replacement logic continues.

```text
Item price
   ↓
ReplaceItemInterceptor subtracts R1.14
   ↓
Shopping cart replacement logic runs
```

## JMS and Message-Driven Bean

The application uses a JMS topic named:

```text
Jms/recentBoughtItems
```

`RecentlyBoughtItemMDB` subscribes to this topic.

When checkout publishes a recently bought item message:

1. The item message is sent to `Jms/recentBoughtItems`.
2. `RecentlyBoughtItemMDB` receives the message.
3. The MDB displays the bought item in the GlassFish server console.

Example console output:

```text
Recently bought item: Laptop
```

## Required GlassFish JMS Resource

Create a JMS Topic destination in the GlassFish Admin Console before deploying the application.

```text
Resources
→ JMS Resources
→ Destination Resources
→ New
```

Use the following settings:

| Setting | Value |
|---|---|
| JNDI Name | `Jms/recentBoughtItems` |
| Resource Type | `jakarta.jms.Topic` |
| Physical Destination Name | `recentBoughtItems` |
| Status | Enabled |

Make sure the JNDI name matches the MDB configuration exactly.

## Prerequisites

Install and configure:

- JDK compatible with your NetBeans and GlassFish installation
- Apache NetBeans
- GlassFish Server 7 or another Jakarta EE-compatible server
- Derby / Java DB, or another supported database
- A configured GlassFish JDBC connection pool and JDBC resource

## Database Setup

Configure your database connection in GlassFish:

```text
Resources
→ JDBC
→ JDBC Connection Pools
```

Create a JDBC connection pool using your database details.

Then create a JDBC resource and ensure that its JNDI name matches the datasource configured in your `persistence.xml`.

Example values:

```text
Database: ABCWholesalersDb
Server: localhost
Port: 1527
Driver: org.apache.derby.jdbc.ClientDataSource
```

The exact database name, username, password, and JDBC resource name must match your local GlassFish and Derby configuration.

## Build and Run

1. Clone the repository:

```bash
git clone [https://github.com/codeWithLFN/ABC-Wholesalers.git](https://github.com/codeWithLFN/ABC-Wholesalers.git)
```

2. Open the project in Apache NetBeans.

3. Configure GlassFish as the application server.

4. Create and test the JDBC connection pool and JDBC resource.

5. Create the JMS topic:

```text
Jms/recentBoughtItems
```

6. Clean and Build the enterprise application.

7. Deploy the application to GlassFish.

8. Open the deployed web application from the NetBeans Services tab or through the GlassFish application URL.

## Important Notes

- Use `jakarta.*` imports, not old `javax.*` imports, when using GlassFish 7 and Jakarta EE.
- The servlet should read and validate form values, then call EJB methods.
- EJB beans should contain business logic and persistence logic.
- JSP pages should display data and messages passed from servlets.
- Do not trust item prices submitted by an HTML form. Look up the real item and price from the database using its item ID.
- For a real production system, passwords must be stored as secure password hashes, not plain text.

## Author

**Lufuno Nemudzivhadi**

GitHub: [codeWithLFN](https://github.com/codeWithLFN)