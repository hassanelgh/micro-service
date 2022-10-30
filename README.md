# micro-service spring boot

Mise en oeuvre d'une architecture micro-services en utilisant les bonnes pratiques  :
-	Couches DA0, Service, Web, DTO
-	Utilisation de MapStruct pour le mapping entre les objet Entities et DTO
-	Génération des API-DOCS en utilisant SWAGGER3 (Open API)
-	Communication entre micro-services en utilisant OpenFeign
-	Spring Cloud Gateway
-	Eureka Discovery Service
Après conteneuriser ces microservices à l'aide de Docker (en utilisant un Dockerfile), puis les déployer et les orchestrer à l'aide de Docker Compose ou Kubernetes


#### Use Case


<img src="images/img.png" alt="">


- [x] [Eureka Server](./eureka-service)
- [x] [Gateway](./gateway)
- [x] [Customer-Service](./customer-service)
- [x] [billing-Service](./billing-service)