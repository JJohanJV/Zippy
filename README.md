Para ejecutar los micro servicios se deben seguir los siguientes pasos:
  1. Se debe abrir un gitbash en el carpeta de cada micro-servicio.
  2. Se deben ejecutar el siguiente comando: ./mvnw -D clean install && ./mvnw spring-boot:run
  3. Luego se deben ejecutar cada uno en el siguiente orden://
       1.Eureka.//
       2.Users.//
       3.Security.//
       4.Vehicles.//
       5.Stations.//
       6.Trips.//
Para ejecutar el frontend, unicamente es necesario ejecutar los siguientes comandos: npm install && npm install -g @ionic/cli
  
