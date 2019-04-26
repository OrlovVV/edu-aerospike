# Spring Boot Aerospike Example

This example covers the Aerospike + Spring Boot Integration with necessary Configurations

Examlpe MAP key-value
- `GET - /deposit/delete/{id}` delete Deposit in the Aerospike by id   
- `GET - /deposit/get/{id}` return Deposit from the Aerospike Instance by id
- `GET - /deposit/get/all` return list of Deosit from the Aerospike Instance
- `POST - /deposit/add` -  add new Deposit in the Aerospike. Sample data: `{"id" : 100431, "clientId" : 3000891, "name" : "Deposit" }` 


Example SET key-bins
- `GET - /account/delete/{id}` delete Account in the Aerospike by id
- `GET - /account/get/{id}` return Account from the Aerospike Instance by id
- `GET - /account/get/byDepositId/{id}` return list Account from the Aerospike Instance by deposit id
- `GET - /account/range/byDepositId/{begin}/{end}` return list Account from Aerospike Instance by range deposit id 
- `POST - /account/add` add new Account in the Aerospike. Sample data: `{"id" : 19, "depositId" : 4, "accounts" : "4178101234565"}`  
