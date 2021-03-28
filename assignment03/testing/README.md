#### PolicyTester
```
Policy: 0001 amount: $320.00
DepreciatingPolicy: 0002 amount: $500.10 rate: 10.0%
DepreciatingPolicy: 0002 amount: $450.09 rate: 10.0%
ExpiringPolicy: 0003 amount: $1000.00 expires: March 28, 2022 (04:53 P.M.)
ExpiringPolicy: 0004 amount: $2000.00 expires: April 02, 2021 (11:59 P.M.)
false
ExpiringPolicy: 0005 amount: $2000.00 expired on: April 01, 2013 (11:59 P.M.)
true
```

#### ClientTester
```
Here are the Individual Client's policies:
  Policy: 0001 amount: $100.00
  DepreciatingPolicy: 0002 amount: $200.00 rate: 10.0%
  ExpiringPolicy: 0003 amount: $300.00 expires: April 02, 2021 (11:59 P.M.)
  ExpiringPolicy: 0004 amount: $400.00 expired on: June 04, 2009 (12:00 P.M.)
Making claims:
  Claim for policy 0001: $100.00
  Claim for policy 0002: $180.00
  Claim for policy 0003: $300.00
  Claim for policy 0004: $  0.00
  Claim for policy 0005: $  0.00
Here are the Individual Client's policies after claims:
  Policy: 0001 amount: $100.00
  DepreciatingPolicy: 0002 amount: $180.00 rate: 10.0%
  ExpiringPolicy: 0003 amount: $300.00 expires: April 02, 2021 (11:59 P.M.)
  ExpiringPolicy: 0004 amount: $400.00 expired on: June 04, 2009 (12:00 P.M.)
The total policy coverage for this client: $980.00

Here are the Company Client's policies:
  Policy: 0006 amount: $1000.00
  DepreciatingPolicy: 0007 amount: $2000.00 rate: 10.0%
  ExpiringPolicy: 0008 amount: $3000.00 expires: April 02, 2021 (11:59 P.M.)
  ExpiringPolicy: 0009 amount: $4000.00 expired on: June 04, 2009 (12:00 P.M.)
Making claims:
  Claim for policy 0006: $1000.00
  Claim for policy 0007: $2000.00
  Claim for policy 0008: $3000.00
  Claim for policy 0009: $   0.00
  Claim for policy 0005: $   0.00
Here are the Company Client's policies after claims:
  Policy: 0006 amount: $1000.00
  DepreciatingPolicy: 0007 amount: $1800.00 rate: 10.0%
  ExpiringPolicy: 0008 amount: $3000.00 expires: April 02, 2021 (11:59 P.M.)
  ExpiringPolicy: 0009 amount: $4000.00 expired on: June 04, 2009 (12:00 P.M.)
The total policy coverage for this client: $9800.00
Cancelling policy #12 ... did it work: false
Cancelling policy #8 ... did it work: true
The total policy coverage for this client: $6800.00
```
## 
