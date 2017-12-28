# SpringTutorial – Spring Transaction Management
A transaction manager is the part of an application that is responsible for coordinating transactions across one or more resources. In the Spring framework, the transaction manager is effectively the root of the transaction system. Hence, if you want to enable transactions on a component in Spring, you typically create a transaction manager bean and pass it to the  Component.

![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/12/spring-transaction-management.png?resize=530%2C182&ssl=1)

It is critical in any form of applications that will interact with the database.
A database transaction is a sequence of actions that are treated as a single unit of work. These actions should either complete entirely or take no effect at all. Transaction management is an important part of and RDBMS oriented enterprise applications to ensure data integrity and consistency. The concept of transactions can be described with following four key properties described as ACID:

* **Atomicity**: A transaction should be treated as a single unit of operation which means either the entire sequence of operations is successful or unsuccessful.<br />
* **Consistency**: This represents the consistency of the referential integrity of the database, unique primary keys in tables etc.<br />
* **Isolation**: There may be many transactions processing with the same data set at the same time, each transaction should be isolated from others to prevent data corruption.<br />
* **Durability**: Once a transaction has completed, the results of this transaction have to be made permanent and cannot be erased from the database due to system failure.<br />

A simple transaction is usually issued to the database system in a language like SQL in this form:

* Begin the transaction
* Execute several queries (although any updates to the database aren’t actually visible to the outside world yet)
* Commit the transaction (updates become visible if the transaction is successful)

Spring framework provides an abstract layer on top of different underlying transaction management APIs. The Spring’s transaction support aims to provide an alternative to EJB transactions by adding transaction capabilities to POJOs. **Spring supports both programmatic and declarative transaction management**. EJBs requires an application server, but **Spring transaction management can be implemented without a need of application server**.

### Local transaction managers:

A local transaction manager is a transaction manager that can coordinate transactions over a single resource only. In this case, the implementation of the transaction manager is typically embedded in the resource itself and the Spring transaction manager is just a thin wrapper around this built-in transaction manager.

Local transaction management can be useful in a centralized computing environment where application components and resources are located at a single site, and transaction management only involves a local data manager running on a single machine. Local transactions are easier to be implemented.

### Global transaction managers:

A global transaction manager is a transaction manager that can coordinate transactions over multiple resources. In this case, you cannot rely on the transaction manager built into the resource itself. Instead, you require an external system, sometimes called a transaction processing monitor (TP monitor), that is capable of coordinating transactions across different resources.

Global transaction management is required in a distributed computing environment where all the resources are distributed across multiple systems. In such a case transaction management needs to be done both at local and global levels. A distributed or a global transaction is executed across multiple systems, and its execution requires coordination between the global transaction management system and all the local data managers of all the involved systems.

### Programmatic vs. Declarative:

Spring supports two types of transaction management:

#### Programmatic transaction management:

This means that you have manage the transaction with the help of programming. That gives you extreme flexibility, but it is difficult to maintain.

#### Declarative transaction management:

This means you separate transaction management from the business code. You only use annotations or XML based configuration to manage the transactions.

**Declarative transaction management** is preferable over **programmatic transaction management** though it is less flexible than programmatic transaction management, which allows you to control transactions through your code. But **as a kind of crosscutting concern, declarative transaction management can be modularized with the AOP approach**. Spring supports declarative transaction management through the Spring AOP framework.

The key to the Spring transaction abstraction is the notion of a transaction strategy. A **transaction strategy** is defined by the **org.springframework.transaction.PlatformTransactionManager** interface, shown below:

```
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(TransactionDefinition definition)
        throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
```

<table class="src" border="1">
<tbody>
<tr>
<th class="fivepct">S.N.</th>
<th>Method &amp; Description</th>
</tr>
<tr>
<td>1</td>
<td><b>TransactionStatus getTransaction(TransactionDefinition definition)</b><br>
This method returns a currently active transaction or create a new one, according to the specified propagation behavior.</td>
</tr>
<tr>
<td>2</td>
<td><b>void commit(TransactionStatus status)</b><br>
This method commits the given transaction, with regard to its status.</td>
</tr>
<tr>
<td>3</td>
<td><b>void rollback(TransactionStatus status)</b><br>
This method performs a rollback of the given transaction.</td>
</tr>
</tbody>
</table>

**org.springframework.transaction.TransactionDefinition** interface specifies:

* **Isolation**: the degree of isolation this transaction has from the work of other transactions. For example, can this transaction see uncommitted writes from other transactions?
* **Propagation**: normally all code executed within a transaction scope will run in that transaction. However, there are several options specifying behavior if a transactional method is executed when a transaction context already exists: for example, simply continue running in the existing transaction (the common case); or suspending the existing transaction and creating a new transaction. Spring offers all of the transaction options familiar from EJB CMT.
* **Timeout**: how long this transaction may run before timing out (and automatically being rolled back by the underlying transaction infrastructure).
* **Read-only status**: a read-only transaction does not modify any data. Read-only transactions can be a useful optimization in some cases (such as when using Hibernate).
    
```
public interface TransactionDefinition {
   int getPropagationBehavior();
   int getIsolationLevel();
   String getName();
   int getTimeout();
   boolean isReadOnly();
}
```

<table class="src" border="1">
<tbody>
<tr>
<th class="fivepct">S.N.</th>
<th>Method &amp; Description</th>
</tr>
<tr>
<td>1</td>
<td><b>int getPropagationBehavior()</b><br>
This method returns the propagation behavior. Spring offers all of the transaction propagation options familiar from EJB CMT.</td>
</tr>
<tr>
<td>2</td>
<td><b>int getIsolationLevel()</b><br>
This method returns the degree to which this transaction is isolated from the work of other transactions.</td>
</tr>
<tr>
<td>3</td>
<td><b>String getName()</b><br>
This method returns the name of this transaction.</td>
</tr>
<tr>
<td>4</td>
<td><b>int getTimeout()</b><br>
This method returns the time in seconds in which the transaction must complete.</td>
</tr>
<tr>
<td>5</td>
<td><b>boolean isReadOnly()</b><br>
This method returns whether the transaction is read-only.</td>
</tr>
</tbody>
</table>

Following are the possible values for isolation level:

<table class="src" border="1">
<tbody>
<tr>
<th class="fivepct">S.N.</th>
<th>Propagation &amp; Description</th>
</tr>
<tr>
<td>1</td>
<td><b>TransactionDefinition.PROPAGATION_MANDATORY</b><br>
Support a current transaction; throw an exception if no current transaction exists.</td>
</tr>
<tr>
<td>2</td>
<td><b>TransactionDefinition.PROPAGATION_NESTED </b><br>
Execute within a nested transaction if a current transaction exists.</td>
</tr>
<tr>
<td>3</td>
<td><b>TransactionDefinition.PROPAGATION_NEVER </b><br>
Do not support a current transaction; throw an exception if a current transaction exists.</td>
</tr>
<tr>
<td>4</td>
<td><b>TransactionDefinition.PROPAGATION_NOT_SUPPORTED </b><br>
Do not support a current transaction; rather always execute non-transactionally.</td>
</tr>
<tr>
<td>5</td>
<td><b>TransactionDefinition.PROPAGATION_REQUIRED </b><br>
Support a current transaction; create a new one if none exists.</td>
</tr>
<tr>
<td>6</td>
<td><b>TransactionDefinition.PROPAGATION_REQUIRES_NEW </b><br>
Create a new transaction, suspending the current transaction if one exists.</td>
</tr>
<tr>
<td>7</td>
<td><b>TransactionDefinition.PROPAGATION_SUPPORTS </b><br>
Support a current transaction; execute non-transactionally if none exists.</td>
</tr>
<tr>
<td>8</td>
<td><b>TransactionDefinition.TIMEOUT_DEFAULT </b><br>
Use the default timeout of the underlying transaction system, or none if timeouts are not supported.</td>
</tr>
</tbody>
</table>

The **org.springframework.transaction.TransactionStatus** interface provides a simple way for transactional code to control transaction execution and query transaction status. The concepts should be familiar, as they are common to all transaction APIs:

```
public interface TransactionStatus extends SavepointManager {

    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    void flush();

    boolean isCompleted();

}
```

<table class="src" border="1">
<tbody>
<tr>
<th class="fivepct">S.N.</th>
<th>Method &amp; Description</th>
</tr>
<tr>
<td>1</td>
<td><b>boolean hasSavepoint()</b><br>
This method returns whether this transaction internally carries a savepoint, that is, has been created as nested transaction based on a savepoint.</td>
</tr>
<tr>
<td>2</td>
<td><b>boolean isCompleted()</b><br>
This method returns whether this transaction is completed, that is, whether it has already been committed or rolled back.</td>
</tr>
<tr>
<td>3</td>
<td><b>boolean isNewTransaction()</b><br>
This method returns true in case the present transaction is new.</td>
</tr>
<tr>
<td>4</td>
<td><b>boolean isRollbackOnly()</b><br>
This method returns whether the transaction has been marked as rollback-only.</td>
</tr>
<tr>
<td>5</td>
<td><b>void setRollbackOnly() </b><br>
This method sets the transaction rollback-only.</td>
</tr>
</tbody>
</table>




