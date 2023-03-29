#!/bin/bash

echo Discovery Service: BUILDING
cd discovery-service
mvn clean install -U
cd ..
clear

echo Discovery Service: BUILD DONE
echo API Gateway: BUILDING
cd api-gateway
mvn clean install -U
cd ..
clear

echo Discovery Service: BUILD DONE
echo API Gateway: BUILD DONE
echo Products Service: BUILDING
cd product-service
mvn clean install -U
cd ..
clear

echo Discovery Service: BUILD DONE
echo API Gateway: BUILD DONE
echo Products Service: BUILD DONE
echo Category Service: BUILDING
cd category-service
mvn clean install -U
cd ..
clear

echo Discovery Service: BUILD DONE
echo API Gateway: BUILD DONE
echo Products Service: BUILD DONE
echo Category Service: BUILD DONE
echo Theme Service: BUILDING
cd theme-service
mvn clean install -U
cd ..
clear

echo Discovery Service: BUILD DONE
echo API Gateway: BUILD DONE
echo Products Service: BUILD DONE
echo Category Service: BUILD DONE
echo Theme Service: BUILD DONE
echo ---
echo Starting Application...
docker-compose up --build
