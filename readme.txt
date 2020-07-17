
1. Creating a docker image

  docker build -t pensioncalculationapi:1.0.0 .

2. Running in docker-compose

    docker-compose up

Test Link:

http://localhost:8080/api/pension?age=42&grossYearlySalary=50000&workStart=2008-01-01
