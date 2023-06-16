
# News App Backend with AWS

Welcome to the News App Backend repository! This repository contains the backend implementation of a News fetching application, developed using Java 17 and Spring Boot. The application is deployed on AWS, leveraging various AWS services for efficient autoscaling and traffic handling..


Note : This application service is stopped due to aws service are not free. Please contact me for the demo or swagger document.

## Technology Used
The following technologies were used in the development of this project:

- Java 17
- Springboot 3.0.x
- AWS(Code Pipeline, Code Build, Elastic Container Registory(ECR), Elastic Container Service(ECS), Fargate)
- Github

## Backend Architecture Explination

The backend architecture of the News App is designed to leverage the scalability and flexibility provided by AWS services. The architecture is built on the AWS cloud region AP-South-1 (Mumbai). Let's take a closer look at the components and their interactions:

- Code Pipeline and CodeBuild: AWS CodePipeline is set up to monitor the repository for changes. Whenever code is merged into the master branch, a trigger event is sent to AWS CodePipeline. CodePipeline, in turn, triggers AWS CodeBuild.
- Docker Image Build: AWS CodeBuild, upon receiving the trigger, looks for the buildspec.yml file in the master branch's source code. The buildspec.yml file contains the specifications required to build the Docker image.
- Elastic Container Registry (ECR): Once the Docker image is built by CodeBuild, it is pushed to the Elastic Container Registry. The image is tagged as "latest" to indicate that it is the most up-to-date version.
- Fargate Cluster: A Fargate cluster is configured to run the Docker containers. Fargate handles the orchestration and autoscaling of the containers based on the workload. This ensures that the application can handle varying levels of traffic efficiently.
- Task Definition and Deployment: To deploy the application, a task is created in the Fargate cluster, specifying the image URL of the latest Docker image. The task runs the containers, and once they are up and running, you can obtain the public IP of the task.
- Accessing the Application: To access the News App, simply paste the public IP of the Fargate task into your browser, appending the port number (8080). For example: http://<publicIP>:8080/.
- Traffic Handling: A security group is configured to accept requests from anywhere. This allows the application to handle incoming traffic from various sources effectively.

## Architecture Diagram
    
![App Screenshot](https://github.com/nischal-jadhav123/readme_screenshots_global/blob/main/news-api-app-architecture.jpg?raw=true)
    
The above image illustrates the architecture of the News App Backend, showcasing the flow of code from the repository to deployment on AWS.

Thank you for your interest in the News App Backend! If you have any further questions, please feel free to reach out.

    
