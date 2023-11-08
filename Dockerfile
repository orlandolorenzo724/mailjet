# Use a base image with Java pre-installed
FROM openjdk:17-jdk-slim-buster

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your project directory to the container
COPY target/mailjet-0.0.1-SNAPSHOT.jar /app/mailjet-0.0.1-SNAPSHOT.jar

# Expose the port your application will run on (if applicable)
EXPOSE 9098

# Define the command to run your application
CMD ["java", "-jar", "mailjet-0.0.1-SNAPSHOT.jar"]
