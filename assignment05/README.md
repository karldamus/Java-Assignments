# Assignment #3
## Policies
This set of classes follows the following hierarchy:

![Policies Hierarchy](https://github.com/karldamus/java-assignments/blob/main/assignment03/github/policies.jpg)

### Policy
This class is simple, it creates a policy with a dedicated policy number and assigned $ amount.

### DepreciatingPolicy
This is a subclass of Policy which depreciates at a given `float rate`.

### ExpiringPolicy
This is a subclass of Policy which expires on a given `Date date`

## Clients
This set of classes follows the following hierarchy:

![Clients Hierarchy](https://github.com/karldamus/java-assignments/blob/main/assignment03/github/clients.jpg)

### Client
This class is used to hold policies for a single client. The most policies on client can have is 10.

### IndividualClient
This class creates claims for individuals.
### CompanyClient
This class creates claims for companies.
- - -
Testing files can be found [here](https://github.com/karldamus/java-assignments/tree/main/assignment03/testing).

- - -
```
Grade: 82%
```
